(ns admin.core
    (:require
      [reagent.core :as r]
      [reagent.dom :as d]
      [admin.page-controller :refer [update-page! page-controller]]
      [admin.routes :refer
       [init-client-routing root-path edit-path timeline-path
        retry-route-or project-edit-path project-timeline-path
        skills-path project-add-path add-post-path
                            ]]
      [cljs.core.match :refer-macros [match]]
      [admin.external.response-broker-process :as process]
      [admin.components.toast :refer [toast]]
      [admin.components.header :refer [header]]
      [component-lib.core :as c]
     ))

;; -------------------------
;; Initialize app

(defn render-link [[label href]]
  [:li [:a {:href href} label]]
  )

(defn central-nav []
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
    [c/page {:class "ProjectEditPage"}
     [header]
     [toast]
     [c/body {:class "ProjectEditPage-Body"}
      [page-controller]
      ]
     ]
    ]
   ]
  )

(defn render []
  (d/render [central-nav] (.getElementById js/document "app"))
  )

(defn mount-root [target]
  (update-page! target)
  )

(defn init! []
  (do
    (init-client-routing mount-root)
    (process/start-events)
    (render)
    )
  )
