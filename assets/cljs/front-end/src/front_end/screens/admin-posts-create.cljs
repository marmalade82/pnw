(ns front-end.screens.admin-posts-create
  (:require [reagent.core :as r]
            [reagent.dom :as d]
            [front-end.components.styled.input :refer [input]]))


(def text (r/atom "I would put text here"))


(defn admin-posts-create []
  [:div { :class ["screen"]
         
         }
   [input "multi-text" @text #(reset! text %)]])


;; -------------------------
;; Initialize app

(defn mount-root []
  (d/render [admin-posts-create] (.getElementById js/document "app")))

(defn ^:export init! []
  (mount-root))
