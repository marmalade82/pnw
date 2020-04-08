(ns front-end.components.styled.my-text-input
    (:require 
        [reagent.core :as r]
        [reagent.dom :as d]))

(defn my-text-input
    [current on-change]
    [:div
        [:input {:type "text"
                 :value current
                 :on-change #(-> % .-target .-value on-change) }]])
