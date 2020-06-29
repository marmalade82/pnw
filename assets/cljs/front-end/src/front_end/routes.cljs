(ns front-end.routes
  (:require
   [reagent.dom :as d]
   [secretary.core :as secretary :refer-macros [defroute]]
   [cljs.core.async :as core]
   )
  )

(def render-chan (core/chan))


(defroute root-path "/" {}
  (core/put! render-chan "home"))

(defroute contact-path "/contact" {}
  (core/put! render-chan "contact"))

(defroute blog-path "/blog" {}
  (core/put! render-chan "blog"))
