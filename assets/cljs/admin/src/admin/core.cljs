(ns admin.core
    (:require
      [reagent.core :as r]
      [reagent.dom :as d]
      [admin.screens.home-page :refer [home-page]]
      [admin.screens.edit-page :refer [edit-page]]
      [admin.screens.timeline-page :refer [timeline-page]]
      [admin.routes :refer
       [init-client-routing root-path edit-path timeline-path
        retry-route-or
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
    :else (retry-route-or (fn [] (render home-page)))
    )
  )

(defn init! []
  (init-client-routing mount-root))
