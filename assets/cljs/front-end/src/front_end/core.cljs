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
      [front-end.screens.clean-code-page :refer [clean-code-page]]
      [front-end.screens.admin-login :refer [admin-login]]
      [front-end.screens.project-page :refer [project-page]]
      ))

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

(def history
  (r/atom (History.)))

(def unknown-route
  (r/atom false))

(defn mount-root [target]
  (let [node (.getElementById js/document "app")]
    (js/console.log "mounting root")
    (match target
          "home" (d/render [home-page {:label "Home"}] node)
          "blog" (d/render [construction-page {:label "Blog"}] node)
          "contact" (d/render [construction-page {:label "Contact"}] node )
          ["work" topic] (d/render [work-page {:label "Blog", :topic topic}] node)
          ["clean-code" topic] (d/render [clean-code-page {:label "Blog", :topic topic}] node)
          ["projects" topic] (d/render [project-page {:label "Blog", :topic topic}] node)
          :else ; If the route is unknown, we redirect to home. Otherwise
                ; we retry the route
              (if @unknown-route
                  (do
                    (d/render [home-page {:label "Home"}] node)
                    (reset! unknown-route false))
                  (do 
                    (reset! unknown-route true)
                    (secretary/dispatch! (.getToken @history))))
          )
    ))

;; -------------------------
;; Initialize app

(defn render-loop []
  (core/take! render-chan
              (fn [target]
                (mount-root target)
                (trampoline render-loop)
                )))


(defn init-client-routing []
  (render-loop)
  (doto @history
    (events/listen EventType.NAVIGATE
                   #(do
                      (js/console.log "navigating")
                      (secretary/dispatch! (.-token %))))
    (.setEnabled true))
  )

(defn init! []
  (init-client-routing))
