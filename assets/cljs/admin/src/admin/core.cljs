(ns admin.core
    (:require
      [reagent.core :as r]
      [reagent.dom :as d]
      [admin.screens.home-page :refer [home-page]]
      [admin.screens.edit-page :refer [edit-page]]
      [admin.routes :refer
       [init-client-routing root-path edit-path
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
                           ]))
   [:div
    [target]
    "hellos"
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
    :else (retry-route-or (fn [] (render home-page)))
    )
  )

(defn init! []
  (init-client-routing mount-root))
