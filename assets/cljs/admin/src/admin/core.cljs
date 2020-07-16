(ns admin.core
    (:require
      [reagent.core :as r]
      [reagent.dom :as d]
      [admin.screens.home-page :refer [home-page]]
      [admin.screens.edit-page :refer [edit-page]]
      [admin.screens.timeline-page :refer [timeline-page]]
      [admin.screens.project-edit-page :refer [project-edit-page]]
      [admin.screens.project-timeline-page :refer [project-timeline-page]]
      [admin.screens.skills-page :refer [skills-page]]
      [admin.routes :refer
       [init-client-routing root-path edit-path timeline-path
        retry-route-or project-edit-path project-timeline-path
        skills-path
                            ]]
      [cljs.core.match :refer-macros [match]]
     ))

;; -------------------------
;; Initialize app

(defn render-link [[label href]]
  [:li [:a {:href href} label]]
  )

(defn central-nav [target]
  [:div
   (into [:ul]
         (map render-link [ ["Login" (root-path)]
                            ["Blog Editor" (edit-path)]
                            ["Blog Timeline" (timeline-path)]
                            ["Project Editor" (project-edit-path)]
                            ["Project Timeline" (project-timeline-path)]
                            ["Skills" (skills-path)]
                           ]))
   [:div
    [target]
    ]
   ]
  )

(defn render [target]
  (d/render [central-nav target] (.getElementById js/document "app"))
  )

(defn mount-root [target]
  (match target
    "root" (render home-page)
    "edit" (render edit-page)
    "timeline" (render timeline-page)
    "project-edit" (render project-edit-page)
    "project-timeline" (render project-timeline-page)
    "skills" (render skills-page)
    :else (retry-route-or (fn [] (render home-page)))
    )
  )

(defn init! []
  (init-client-routing mount-root))
