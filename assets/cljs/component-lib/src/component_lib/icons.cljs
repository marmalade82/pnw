(ns component-lib.icons
  (:require-macros
   [component-lib.icons :refer [deficon]]
   )
  (:require
  [reagent.core :as r]
  [reagent.dom :as d]
  [hiccup-icons.octicons :as octicons]
  ))


(deficon adjustments octicons/settings)

(deficon alert octicons/stop)

(deficon check octicons/check)

(deficon contract octicons/fold)

(deficon chat-comment octicons/comment)

(deficon delete octicons/trashcan)

(deficon discussion octicons/comment-discussion)

(deficon dislike octicons/thumbsdown)

(deficon down octicons/triangle-down)

(deficon edit octicons/pencil)

(deficon email octicons/mail)

(deficon expand octicons/unfold)

(deficon folder octicons/file-directory)

(deficon github octicons/mark-github)

(deficon graph octicons/graph)

(deficon hot octicons/flame)

(deficon left octicons/triangle-left)

(deficon like octicons/thumbsup)

(deficon location octicons/location)

(deficon plus octicons/plus)

(deficon pin octicons/pin)

(deficon preview octicons/eye)

(deficon more-info octicons/info)

(deficon question octicons/question)

(deficon reply octicons/reply)

(deficon report octicons/report)

(deficon right octicons/triangle-right)

(deficon search octicons/search)

(deficon settings octicons/gear)

(deficon sync octicons/sync)

(deficon top octicons/triangle-up)

(deficon unpermitted octicons/circle-slash)

(deficon website octicons/device-desktop)

(deficon x octicons/x)

