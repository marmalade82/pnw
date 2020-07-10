(ns front-end.screens.contactpage.form-macros
  (:require
   [clojure.set :refer [difference]]
   [front-end.screens.contactpage.interface :refer [IComposableForm]]
   )
  )


(defmacro validate "Transforms a validation spec into a data structure"
  [fields body]
  `{ :fields ~(into [] (map keyword fields))
     :validate (fn [~'errors ~'store] 
                ~(let [l (into []
                               (mapcat #(identity [% (list (keyword %) 'store)]) fields))]
                   `(let ~l
                      ~body
                      )
                   ))
     :tag :validate
    }
  )

(defmacro fields "Declares a set of fields in the form or scope macros"
  [fields]
  `{ :tag :fields
    :fields ~(into [] (map #(keyword %) fields))
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

(defn get-validator-fields
  [rest]
  (let [validators (get-validators rest)
        fields (reduce #(into %1 (:fields %2)) [] validators)
        ]
    (distinct fields)
    )
  )

(defn get-declared-fields
  [rest]
  (let [declarations (filter #(= :fields (:tag %)) rest)
        fields (reduce #(into %1 (:fields %2)) [] declarations)
        ]
    (distinct fields)
    )
  )

(defn resty [a & rest]
  `~rest
  )

(defn get-fields "Gets set of fields in the top level of the scope. Throws error if one is not declared"
  [rest]
  (let [
        validator-fields (set (get-validator-fields rest))
        declared-fields (set (get-declared-fields rest))
        undeclared-fields (difference validator-fields declared-fields)
        ]
    (if (empty? undeclared-fields)
        (into [] declared-fields)
        (throw
         (ex-info (str "Undeclared fields in scope: " undeclared-fields) {:tag :undeclared-fields
                                                :fields undeclared-fields
                                                })
         )
      )
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
  [name id & forms]
  (let [rest (map #(macroexpand %) forms)]
    `{ :tag :scope
      :name ~(keyword name)
      :id ~(keyword id) 
                                        ; map from keywords to validator functions that must run
      :triggers ~(build-validator-map (get-validators rest))
      :fields ~(get-fields rest)
      :scopes ~(build-scope-map (get-child-scopes rest))
      }
    )
  )

(defn errors-map [scope-tree]
  (into {} (for [[field values] scope-tree] [field (:error values)]))
  )

(defn values-map [scope-tree]
  (into {} (for [[field values] scope-tree] [field (:value values)]))
  )

(defn build-state* [scope-tree state-tree]
  ;TODO need to check that scopes don't share names as fields or other scopes
  (if (and (nil? (:fields scope-tree)) (nil? (:scopes scope-tree)))
    state-tree
    (let [
          add-field (fn [acc field]
                        (assoc acc field { :value nil
                                          :error nil 
                                          :triggers (field (:triggers scope-tree))
                                          })
                      )
          new-tree (reduce add-field {} (:fields scope-tree))]
       ; TODO This also needs to merge in the scopes
       (assoc state-tree (:name scope-tree) new-tree )
      )
    )
  
  )


(defn build-state [scope-tree]
  (let [initial-build (build-state* scope-tree {})
        final-build (get initial-build "global" {})
        ]
       final-build
    )
  )


; This macro should generate a class and implementation of 
; the form protocol
(defmacro form "Defines a form and the hooks into it"
  [name ratom & forms] ; rest are a list of scopes and validations
  (let [rest (map #(macroexpand %) forms)
         global-scope-tree
         { :tag :scope
          :name "global"
          :id "global"
          :triggers (build-validator-map (get-validators rest))
          :fields (get-fields rest)
          :scopes (build-scope-map (get-child-scopes rest))
          }
         initial_state (build-state global-scope-tree)
         ]
     `(do
        (defrecord ~name [~'values ~'errors])
        (defn ~(symbol (str "mk-" name)) [{:keys [~'initial] :or {~'initial {}}}]
          (let [~'values (~ratom (merge-with #(assoc %1 :value %2)
                                  ~initial_state ~'initial))
                ~'errors (~ratom {})
                ~'form (~(symbol (str name ".")) ~'values ~'errors)
                ]
                ~'form
            )
          )
        (defn ~'refresh-validate [~'values]
          ~'values
          )
          (extend-type ~name
              ~'IComposableForm
              (my-input [~'this ~'field ~'new-val]
                (let [~'values (:values ~'this)]
                  (if (contains? @~'values ~'field) (swap! ~'values #(assoc % ~'field ~'new-val)))
                  ))
              (my-error [~'this ~'field]
                (let [~'values @(:values ~'this)]
                  (if (contains? ~'values ~'field)
                    (:error (~'field ~'values))
                    "banana"
                    )
                  )
                )
              (my-value ;TODO: Would like to add field name checking here
                [~'this ~'field ~'default]
                (let [~'values (:values ~'this)
                      ~'val (:value (~'field @~'values))
                      ]
                  (if (nil? ~'val)
                     ~'default 
                     ~'val
                    )
                ))
              (all-data [~'this]
                (let [~'values @(:values ~'this)]
                  (into {} (for [[~'k ~'v] ~'values] [~'k (:value ~'v)]))
                  )
                )
              (revalidate [~'this ~'field]
                ~'nil
                )
              (refresh [~'this]
                (let [~'values-atom (:values ~'this)
                      ~'values @~'values-atom
                      ]
                   (reset! ~'values-atom (~'refresh-validate ~'values))
                  )
                )
              )
        ))
  )
