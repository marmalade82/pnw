(ns component-lib.icons
  (:require-macros
   [component-lib.icons :refer [deficon]]
   )
  (:require
  [reagent.core :as r]
  [reagent.dom :as d]
  [hiccup-icons.octicons :as octicons]
  ))

(deficon github octicons/mark-github)

(deficon website octicons/device-desktop)

(deficon edit octicons/pencil)

(deficon preview octicons/eye)

(deficon check octicons/check)

(deficon plus octicons/plus)

(deficon email octicons/mail)

(deficon more-info octicons/info)

(deficon question octicons/question)

(deficon x octicons/x)

(deficon sync octicons/sync)

(deficon reply octicons/reply)

(deficon like octicons/thumbsup)

(deficon dislike octicons/thumbsdown)

(deficon expand octicons/unfold)

(deficon contract octicons/fold)

(deficon discussion octicons/comment-discussion)

(deficon chat-comment octicons/comment)

(deficon settings octicons/gear)

(deficon location octicons/location)

(deficon adjustments octicons/settings)

(deficon search octicons/search)

(deficon report octicons/report)

(deficon alert octicons/stop)

(deficon pin octicons/pin)

(deficon graph octicons/graph)

(deficon unpermitted octicons/circle-slash)

(deficon hot octicons/flame)

(deficon top octicons/triangle-up)

(deficon right octicons/triangle-right)

(deficon down octicons/triangle-down)

(deficon left octicons/triangle-left)

(deficon delete octicons/trashcan)
