(ns admin.external.utils
  (:require
   [reagent.core :as r]
   [reagent.dom :as d]
   )
  )


(defn form-data [vals]
  (let [form (js/FormData.)]
    (do
      (doseq [[k v] vals]
        (.append form (clj->js k) (clj->js v))
        )
      form
      )
    )
  )

(defn pipe [atom transform]
  (let [new-atom (r/atom @atom)]
    (add-watch atom (random-uuid)
               (fn [_key _ref _old new]
                 (reset! new-atom (transform new))
                 )
               )
    new-atom
    )
  )
