(ns front-end.components.styled.button
  (:require [reagent.core :as r]
            [reagent.dom :as d]))

(defn button [type label on-click]
  [:div {:class ["submit-button" "clickable"]
         }
   [:span {:class ["submit-button-text"]
           :on-click #(-> % on-click)
           } label]])
