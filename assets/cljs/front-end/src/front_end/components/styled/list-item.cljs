(ns front-end.components.styled.list-item
  (:require [reagent.core :as r]
            [reagent.dom :as d]
            ))

(defn list-item [{:keys [class] :or {class ""} }]
  [:li {:class "ListItem"}
   (into [:div {:class ["ListItemContainer" class]}]
               (r/children (r/current-component)))]
   
  )

