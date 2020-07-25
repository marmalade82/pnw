(ns admin.global-state.posts
  (:require
   [reagent.core :as r]
   [reagent.dom :as d]
   [cljs.core.async :refer [go]]
   )
  )

(defn get-grouped-blog-data []
  [
   {:month "October 2020"
    :posts [
            {:title "Title 1"
             :created_at "Oct 1"
             :updated_at "Oct 3"
             :views 30
             :id 7
             :edit-href "#/edit"
             }
            {:title "Title 1"
             :created_at "Oct 1"
             :updated_at "Oct 3"
             :views 30
             :id 7
             :edit-href "#/edit"
             }
            {:title "Title 1"
             :created_at "Oct 1"
             :updated_at "Oct 3"
             :views 30
             :id 7
             :edit-href "#/edit"
             }
            ]
    }
   {:month "August 2020"
    :posts [
            {:title "Title 1"
             :created_at "Aug 1"
             :updated_at "Aug 3"
             :views 30
             :id 3
             :edit-href "#/edit"
             }
            {:title "Title 1"
             :created_at "Aug 1"
             :updated_at "Aug 3"
             :views 30
             :id 3
             :edit-href "#/edit"
             }
            {:title "Title 1"
             :created_at "Aug 1"
             :updated_at "Aug 3"
             :views 30
             :id 3
             :edit-href "#/edit"
             }
            {:title "Title 1"
             :created_at "Aug 1"
             :updated_at "Aug 3"
             :views 30
             :id 3
             :edit-href "#/edit"
             }
            ]
    }
   ]
  )

(def posts (r/atom nil))

(defn posts-loading? []
  (= @posts nil)
  )

(defn get-posts []
  (do
    (if (posts-loading?)
      (go
        (js/setTimeout #(reset! posts (get-grouped-blog-data)) 1000)
        )
      )
    )
    posts
  )
