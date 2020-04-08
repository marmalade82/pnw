(ns front-end.screens.admin-posts-create
  (:require [reagent.core :as r]
            [reagent.dom :as d]
            [front-end.components.styled.input :refer [input]]))


(def text (r/atom "I would put text here"))


(defn admin-posts-create []
  [:div
   [input "multi-text" @text #(reset! text %)]])

