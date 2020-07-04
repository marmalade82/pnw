(ns front-end.components.styled.link-text
  (:require
   [reagent.core :as r]
   [reagent.dom :as d]
   )
  )

(defn link-text [{:keys [class href] :or {class ""}}]
  (into [:a {:class ["LinkText" class], :href href}]
        (r/children (r/current-component)))
  )

