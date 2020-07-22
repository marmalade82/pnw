(ns admin.core
    (:require
      [reagent.core :as r]
      [reagent.dom :as d]
      [admin.screens.home-page :refer [home-page]]
      [admin.screens.edit-page :refer [edit-page]]
      [admin.screens.timeline-page :refer [timeline-page]]
      [admin.screens.project-edit-page :refer [project-edit-page]]
      [admin.screens.project-timeline-page :refer [project-timeline-page]]
      [admin.screens.new-page :refer [new-page]]
      [admin.screens.project-new-page :refer [project-new-page]]
      [admin.screens.skills-page :refer [skills-page]]
      [admin.page-controller :refer [update-page! page-controller]]
      [admin.routes :refer
       [init-client-routing root-path edit-path timeline-path
        retry-route-or project-edit-path project-timeline-path
        skills-path project-add-path add-post-path
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
                            ["Blog Add" (add-post-path)]
                            ["Blog Timeline" (timeline-path)]
                            ["Project Editor" (project-edit-path)]
                            ["Project Add" (project-add-path)]
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
  (update-page! target)
  )

(defn init! []
  (do
    (init-client-routing mount-root)
    (d/render [page-controller] (.getElementById js/document "app"))
    )
  )
