(ns admin.global-state.posts
  (:require
   [reagent.core :as r]
   [reagent.dom :as d]
   )
  )

(def posts (r/atom nil))

(defn posts-loading? []
  (= @posts nil)
  )

(defn get-posts []
  (do
    (if (posts-loading?)
        nil ; get all the posts asynchronously here
      )
    )
    posts
  )
