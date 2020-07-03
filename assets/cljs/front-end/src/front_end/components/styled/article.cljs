(ns front-end.components.styled.article
  (:require
    [reagent.core :as r]
    [reagent.dom :as d]
   ))

(defn article [{:keys [class] :or {class ""}}]
  [:div {:class ["Article-Container"]}
   (into [:article {:class ["Article" class]}]
         (r/children (r/current-component))
         )]
  )
