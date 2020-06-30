(ns front-end.screens.work-page
  (:require
   [reagent.core :as r]
   [reagent.dom :as d]
   [front-end.components.styled.layout :refer [layout]]
   ))

(defn get-work-experience [topic]
  {}
  )

(defn render-work-experience [{:keys [topic]}]
    [:div]
  )

(defn work-page [{:keys [label topic]}]
  (let [work-experience (get-work-experience topic)]
    [layout {:label label}
     [:article {:class "WorkPage-Container"}
      [render-work-experience {:item work-experience}]
      ]
     ])
  )
