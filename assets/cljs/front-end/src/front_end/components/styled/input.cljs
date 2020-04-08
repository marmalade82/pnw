(ns front-end.components.styled.input
  (:require [reagent.core :as r]
            [front-end.components.styled.my-text-input :refer [my-text-input]]
            [front-end.components.styled.my-password-input :refer [my-password-input]]
            [front-end.components.styled.my-multiline-text-input :refer [my-multiline-text-input]]
            [reagent.dom :as d]))



(defn input [type value on-change]
  (case type
    "text" (my-text-input value on-change)
    "password" (my-password-input value on-change)
    "multi-text" (my-multiline-text-input value on-change)))

