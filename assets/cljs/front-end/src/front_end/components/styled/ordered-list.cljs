(ns front-end.components.styled.ordered-list
  (:require [reagent.core :as r]
            [reagent.dom :as d]
            [front-end.components.styled.list-item :refer [list-item]]
            ))

(defn as-list [child]
  [list-item {} child]
  )

(defn ordered-list [{:keys [class] :or {class ""}}]
  (let [children 
        (map as-list (r/children (r/current-component)))
        ]
     (into [:ol {:class "OrderedList"}] children)
    )
  )

