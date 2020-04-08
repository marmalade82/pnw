(ns front-end.components.styled.input
  (:require [reagent.core :as r]
            [front-end.components.styled.my-text-input :refer [my-text-input]]
            [front-end.components.styled.my-password-input :refer [my-password-input]]
            [reagent.dom :as d]))



(defn input [type value on-change]
  (case type
    "text" (my-text-input value on-change)
    "password" (my-password-input value on-change)))

(defn test "hi")
