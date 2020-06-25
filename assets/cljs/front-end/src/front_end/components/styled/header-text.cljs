(ns front-end.components.styled.header-text
  (:require [reagent.core :as r]
            [reagent.dom :as d]))




(defn header-text [level & text]
  (case level
    "1" [:h1 {:class ["header-1"]} text]
    "2" [:h2 {:class ["header-2"]} text]
    "3" [:h3 {:class ["header-3"]} text]
    "4" [:h4 {:class ["header-4"]} text]
    "5" [:h5 {:class ["header-5"]} text]
    ))

