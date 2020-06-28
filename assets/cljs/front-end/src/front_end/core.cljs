(ns front-end.core
  (:import [goog History]
           [goog.history EventType])
    (:require
      [goog.events :as events]
      [reagent.core :as r]
      [reagent.dom :as d]
      [front-end.screens.admin-posts-view :refer [admin-posts-view]]
      [front-end.screens.admin-posts :refer [admin-posts]]
      [front-end.screens.admin-posts-create :refer [admin-posts-create]]
      [front-end.screens.testing :refer [testing]]
      [front-end.screens.projects-view :refer [projects-view]]
      [front-end.screens.home-page :refer [home-page]]
      [secretary.core :as secretary :refer-macros [defroute]]
      [front-end.screens.admin-login :refer [admin-login]]))

;; -------------------------
;; Views

(defn root-page [page]
  [:div { :class "root"
         }
   [page]
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

(defn mount-root [page]
  (d/render [root-page page] (.getElementById js/document "app")))

(defroute root "/" {}
  (mount-root home-page))

(defroute contact "/contact" {}
  (mount-root home-page))

(defroute blog "/blog" {}
  (mount-root home-page))

(defn init-client-routing []
  (secretary/set-config! :prefix "#")
  (doto (History.)
    (events/listen EventType.NAVIGATE #(secretary/dispatch! (.-token %)))
    (.setEnabled true))
  )

(defn init! []
  (init-client-routing))


