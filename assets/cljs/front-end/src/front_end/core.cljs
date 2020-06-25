(ns front-end.core
    (:require
      [reagent.core :as r]
      [reagent.dom :as d]
      [front-end.screens.admin-posts-view :refer [admin-posts-view]]
      [front-end.screens.admin-posts :refer [admin-posts]]
      [front-end.screens.admin-posts-create :refer [admin-posts-create]]
      [front-end.screens.testing :refer [testing]]
      [front-end.screens.projects-view :refer [projects-view]]
      [front-end.screens.home-page :refer [home-page]]
      [front-end.screens.admin-login :refer [admin-login]]))

;; -------------------------
;; Views

(defn root-page []
  [:div { :class "root"
         }
   [home-page]
   ]
  )

(defn test-page []
  [:div 
   [admin-login []]
   [admin-posts]
   [admin-posts-create]
   [testing]
   [admin-posts-view]
   [projects-view]
   ])

;; -------------------------
;; Initialize app

(defn mount-root []
  (d/render [root-page] (.getElementById js/document "app")))

(defn init! []
  (mount-root))


