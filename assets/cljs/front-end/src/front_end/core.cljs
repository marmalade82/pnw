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
      [cljs.core.match :refer-macros [match]]
      [front-end.screens.home-page :refer [home-page]]
      [front-end.screens.construction-page :refer [construction-page]]
      [front-end.screens.work-page :refer [work-page]]
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

(defn mount-root [target]
  (js/console.log "Mounting new root")
  (let [node (.getElementById js/document "app")]
    (match target
      "home" (d/render [home-page {:label "Home"}] node)
      "blog" (d/render [construction-page {:label "Blog"}] node)
      "contact" (d/render [construction-page {:label "Contact"}] node )
      ["work" topic] (d/render [work-page {:label "Work", :topic topic}] node)
      ["clean-code" topic] (d/render [construction-page {:label "Clean Code", :topic topic}] node)
      ["projects" topic] (d/render [construction-page {:label "Projects", :topic topic}] node)
      (d/render [home-page] node)
      )
    ))

;; -------------------------
;; Initialize app

(defn render-loop []
  (core/take! render-chan
              (fn [target]
                (js/console.log target)
                (mount-root target)
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


