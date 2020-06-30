(ns front-end.components.styled.layout
  (:require [reagent.core :as r]
            [reagent.dom :as d]
            [front-end.components.styled.header :refer [header]]
            [front-end.components.styled.footer :refer [footer]]
            ))

(defn layout [{:keys [label]} child]
  (into [:div {:class "Body"} [header {:label label}] ]
        (into (r/children (r/current-component))
              [[footer]])
      )
  )
