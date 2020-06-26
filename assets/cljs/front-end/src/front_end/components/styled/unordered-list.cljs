(ns front-end.components.styled.unordered-list
  (:require [reagent.core :as r]
            [reagent.dom :as d]
            [front-end.components.styled.list-item :refer [list-item]]
            ))

(defn as-list [child]
  [list-item {} child]
  )

(defn unordered-list [{:keys [class] :or {class ""}}]
  (let [children 
        (map as-list (r/children (r/current-component)))
        ]
    (into [:ul {:class "UnorderedList"}] children)
    )
  )


