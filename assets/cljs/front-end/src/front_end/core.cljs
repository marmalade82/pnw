(ns front-end.core
    (:require
      [reagent.core :as r]
      [reagent.dom :as d]
      [front-end.screens.admin-posts-view :refer [admin-posts-view]]
      [front-end.screens.admin-posts :refer [admin-posts]]
      [front-end.screens.admin-posts-create :refer [admin-posts-create]]
      [front-end.screens.admin-login :refer [admin-login]]))

;; -------------------------
;; Views


(defn home-page []
  [:div 
   [:h2 "Welcome to Reagent"]
   [admin-login []]
   [admin-posts]
   [admin-posts-create]
   [admin-posts-view]
   ])

;; -------------------------
;; Initialize app

(defn mount-root []
  (d/render [home-page] (.getElementById js/document "app")))

(defn init! []
  (mount-root))
