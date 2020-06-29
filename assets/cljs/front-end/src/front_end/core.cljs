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
      [front-end.routes :refer [render-chan root-path contact-path blog-path]]
      [secretary.core :as secretary]
      [cljs.core.async :as core]
      [front-end.screens.home-page :refer [home-page]]
      [front-end.screens.admin-login :refer [admin-login]]))

;; -------------------------
;; Views

(defn test-page []
  [:div 
   [admin-login []]
   [admin-posts]
   [admin-posts-create]
   [testing]
   [admin-posts-view]
   [projects-view]
   ])

(defn root-page [page]
  [:div { :class "root"
         }
   [page]
   ]
  )

(defn mount-root [page]
  (d/render [root-page page] (.getElementById js/document "app")))

;; -------------------------
;; Initialize app

(defn render-loop []
  (core/take! render-chan
              (fn [target]
                (js/console.log target)
                (case target
                  "home" (mount-root home-page)
                  "blog" (mount-root home-page)
                  "contact" (mount-root home-page)
                  (mount-root home-page))
                  
                (trampoline render-loop)
                ))
  )

(defn init-client-routing []
  (secretary/set-config! :prefix "#")
  (render-loop)
  (doto (History.)
    (events/listen EventType.NAVIGATE #(secretary/dispatch! (.-token %)))
    (.setEnabled true))
  )



(defn init! []
  (init-client-routing))


