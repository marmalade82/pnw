(ns front-end.routes
  (:require
   [reagent.dom :as d]
   [secretary.core :as secretary :refer-macros [defroute]]
   [front-end.screens.home-page :refer [home-page]]
   )
  )

(defn root-page [page]
  [:div { :class "root"
         }
   [page]
   ]
  )

(defn mount-root [page]
  (d/render [root-page page] (.getElementById js/document "app")))

(defroute root-path "/" {}
  (mount-root home-page))

(defroute contact-path "/contact" {}
  (mount-root home-page))

(defroute blog-path "/blog" {}
  (mount-root home-page))
