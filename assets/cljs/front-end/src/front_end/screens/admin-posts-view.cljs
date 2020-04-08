(ns front-end.screens.admin-posts-view
  (:require [reagent.core :as r]
            [reagent.dom :as d]))


(defn get-post! []
  (let [post (r/atom "hi this was how my week went")]
    post))

(defn render-post [post]
  [:div post])


(defn admin-posts-view []
  (let [post (get-post!)]
    (fn []
      [:div
       [render-post @post]])))







