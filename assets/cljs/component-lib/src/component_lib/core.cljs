(ns component-lib.core
  (:require
   [reagent.core :as r]
   [reagent.dom :as d]
   [clojure.string :refer (capitalize)]
   )
  )

(defn input-group [{:keys [label, type, class,
                           value, id, on-change, max-length]
                    :or {type "text", class "", value nil, id "",
                         on-change nil, max-length nil, label ""
                         }}]
  [:div {:class ["InputGroup" class]}
   [:label {:for id, :class "InputGroup-Label"} label]
   [:input {:class (str "InputGroup-" (capitalize type) "Input")
            :type type,
            :value value,
            :id id,
            :name id,
            :on-change on-change,
            :max-length max-length
            }]
   ]
  )

(defn submit-button [{:keys [disabled class]}]
  (into [:button {:type "submit"
                 :disabled disabled
                 :class ["SubmitButton" class]
                 }]
        (r/children (r/current-component))
        )
  )
