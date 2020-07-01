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

(defroute work-experience-path "/work/:topic" [topic]
  (core/put! render-chan ["work" topic]))

(defroute clean-code-path "/clean-code/:topic" [topic]
  (core/put! render-chan ["clean-code" topic]))

(defroute project-path "/projects/:topic" [topic]
  (core/put! render-chan ["projects" topic]))


(defroute "*" {}
  (core/put! render-chan nil))
