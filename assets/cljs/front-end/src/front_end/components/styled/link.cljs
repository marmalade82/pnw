(ns front-end.components.styled.link
  (:require [reagent.core :as r]
            [reagent.dom :as d]))

(defn link [url text-component]
  [:a {:href url
       :class ["link-text"]
       }
   text-component])

