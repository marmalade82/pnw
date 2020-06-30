(ns front-end.screens.construction-page
  (:require [reagent.core :as r]
            [reagent.dom :as d]
            [front-end.components.styled.layout :refer [layout]]
            ))

(defn construction-page [{:keys [label]}]
  [layout {:label label}
   [:div {:class "Construction-Container"
          }
    [:span {:class "Construction-text"}
     "This page is currently under construction."]
    ]
   ]
)
