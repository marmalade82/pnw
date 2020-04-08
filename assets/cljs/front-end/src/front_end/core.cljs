(ns front-end.core
    (:require
      [reagent.core :as r]
      [reagent.dom :as d]
      [front-end.components.styled.input :refer [input]]
      [front-end.components.styled.label :refer [label]]))

;; -------------------------
;; Views

(def text (r/atom "hello"))
(def password (r/atom "pass"))

(defn home-page []
  [:div 
    [:h2 "Welcome to Reagent"] 
    [label "Username"]
    [input "text" @text #(reset! text %)]
    [label "Password"]
    [input "password" @password #(reset! password %)]])

;; -------------------------
;; Initialize app

(defn mount-root []
  (d/render [home-page] (.getElementById js/document "app")))

(defn init! []
  (mount-root))
