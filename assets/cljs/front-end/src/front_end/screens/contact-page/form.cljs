(ns front-end.screens.contact-page.form
  (:require
   [front-end.screens.contact-page.interface :refer [IComposableForm]]
            )
  )


(defmacro validate "Transforms a validation spec into a data structure"
  [fields body]
  `{ :fields ~(into [] (map keyword fields))
     :validate (fn [errors store]
                ~(let [
                       l (into []
                               (mapcat #(identity [% (list (keyword %) `store)]) fields))]
                   `(let ~l
                      ~body
                      )
                   ))
     :tag :validate
    }
  )

(defn get-validators [rest]
  (filter #(= :validate (:tag %)) rest)
  )

(defn get-child-scopes [rest]
  (let [scopes 
        (filter #(= :scope (:tag %)) rest)]
    ; perform uniqueness check here on scope names
    (if (distinct (map #(:name %) scopes))
        scopes
        (throw (ex-info "Scope names are not distinct at same level" {}))
      )
    )
  )

(defn get-fields [validators]
  (let [all-fields (reduce #(into %1 (:fields %2)) [] validators)]
    (into [] (distinct all-fields))
    )
  )

(defn build-validator-map "Takes validators and builds a map from keywords to validator functions"
  [validators]
  (let [acc (fn [old-map validator]
              (let [new-map (reduce #(assoc %1 (keyword %2) [(:validate validator)]) {} (:fields validator))]
                (merge-with into old-map new-map)
                )
              )
        ]
    (reduce acc {} validators))
  )

(defn build-scope-map "Takes scopes and recursively builds on them"
  [scopes] ; scopes are the output of the other macro calls to scope
  (let [acc (fn [old-map scope]
              (assoc old-map (:name scope) scope)
              )]
    (reduce acc {} scopes)
    )
  )

(defmacro scope "Declares a scope for a given set of fields, for access purposes"
  [name id & rest]
  `{ :tag :scope
    :name ~(keyword name)
    :id ~(keyword id) 
     ; map from keywords to validator functions that must run
    :triggers ~(build-validator-map (get-validators rest))
    :fields ~(get-fields rest)
    :scopes ~(build-scope-map (get-child-scopes rest))
    }
  )



; This macro should generate a class and implementation of 
; the form protocol
(defmacro form "Defines a form and the hooks into it"
  [name & rest] ; rest are a list of scopes and validations
  `(do
     (defrecord ~name [values errors])
     ; When we initialize the form, we use the scopes and validations 
     ; to build a top level scope and then use that to generate the rest
     ; of the functions on the record
     ~(let [global-scope-tree
            { :tag :scope
             :name "global"
             :id "global"
             :triggers (build-validator-map (get-validators rest))
             :fields ~(get-fields rest)
             :scopes ~(build-scope-map (get-child-scopes rest))
             }
            ]
        `(do
           (defn ~(symbol (str "mk-" name)) [{:keys [initial] :or {initial {}}}]
             (let [values (r/atom initial)
                   errors (r/atom {})
                   ]
               (~(symbol (str name ".")) values errors)
               )
             )
           (extend-type ~name
              IComposableForm
              (my-input [this field new-val]
                (let [values (:values this)]
                  (if (contains? @values field) (swap! values #(assoc % field new-val)))
                  ))
              (my-error [this field]
                (let [errors (:errors this)]
                  (if (contains? @errors field) (field @errors))
                  )
                )
              (my-value [this field]
                (let [values (:values this)]
                  (field @values))
                )
              (all-data [this field]
                (let [values (:values this)]
                  values)
                )
              )))
    )
  )


(comment "What we want is the ability to specify form logic in the way of trees, while also allowing custom behavior, the ability to validate, etc."
         "We want to avoid the issue that Formik has, where Formik is responsible for BOTH rendering and form logic, which really need to be separate services if we want this to be flexible."
         "Form logic should also be composable at run-time AND compile-time")

(def BaseExample
  #_(fn [values errors] ; each is a map to values
      ; This is the formik approach. There is a top-level validation function
    ; The function doesn't known what fields it is validating, 
    ; or what the dependencies are
    errors
    )
  )

(def BaseExampleWithHooks
  #_(validate [name age]
            (fn [values errors]
              errors
              ; Here we specify the fields being validated. When
              ; validation is rerun on name or age, this function
              ; will be run
                         ))
  #_(validate (fn [values errors]
                errors
                ; Here we can leave out the dependencies, and the macro 
                ; will still do the work
                ))
  )

(def HooksWithDeclarations
  #_(validate [name age] :against [height]
              (fn [errors]
                errors
                ; Here, name and age are available for validation,
                ; as is height, the dependency. This allows 
                ; the logic to say that when [height] changes, 
                ; the validation should be rerun as well
                ))
  )


; Perhaps if there's no ., then it is relative to the current scope.
; But if there is a chain, then it is relative to root.
(def HooksWithNested
  #_(validate [name.first name.last] :against [physical.height])
  )

; Notice that if we do it this way, we don't need to define fields,
; since the validation declarations do this implicitly
; But if we allow fields to be omitted in the validate functions, then 
; we don't know what fields are in the form.
(def HooksWithScope
  #_(scope name
           #_(validate [first last] :against [physical.height :as h]
                       (fn [errors]
                         errors )
                       )
         ))
#_(let ph (scope physical)
       (input :name ph)
       )
#_(input :name, :scope :physical)

(def HooksWithScopeWithId
  #_(scope name :id id-name
           #_(validate [first last] :against [height]
                       (fn [errors] errors)
                       )
           )
  )
