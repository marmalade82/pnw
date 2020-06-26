(ns front-end.components.styled.list-item
  (:require [reagent.core :as r]
            [reagent.dom :as d]
            ))

(defn list-item [{:keys [class] :or {class ""} }]
  (into [:li ] (r/children (r/current-component)))
   
  )

