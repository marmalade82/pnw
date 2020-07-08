(ns front-end.screens.contactpage.form-macros)


(defmacro validate "Transforms a validation spec into a data structure"
  [fields body]
  `{ :fields ~(into [] (map keyword fields))
     :validate (fn [~'errors ~'store] 
                ~(let [
                       l (into []
                               (mapcat #(identity [% (list (keyword %) 'store)]) fields))]
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
     (defn ~(symbol (str "mk-" name))
       (let [values (r/atom {})
             errors (r/atom {})
             ]
         (~(symbol (str name ".")) values errors)
         )
       )
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
        `(extend-type ~name
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
            ))
    )
  )
