(ns front-end.components.styled.my-password-input
  (:require [reagent.core :as c]
            [reagent.dom :as d]))

(defn my-password-input
  [current on-change]
  [:div
   [:input {:type "password"
            :value current
            :on-change #(-> % .-target .-value on-change) }]])
