(ns front-end.components.styled.my-multiline-text-input
  (:require [reagent.core :as r]
            [reagent.dom :as d]))

(defn my-multiline-text-input [value on-change]
  [:div 
   [:textarea {
               :value value,
               :on-change #(-> % .-target .-value on-change) }]])

