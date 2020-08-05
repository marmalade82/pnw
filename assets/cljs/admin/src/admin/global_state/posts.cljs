(ns admin.global-state.posts
  (:require
   [reagent.core :as r]
   [reagent.dom :as d]
   [cljs.core.async :refer [go]]
   [cljs.core.async.interop :refer [<p!]]
   [admin.external.utils :refer [pipe]]
   [admin.routes :as routes]
   [admin.external.requests :as req]
   [clojure.set :refer [rename-keys]]
   [moment]
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

(defn by-date [{:keys [inserted_by]}]
  inserted_by
  )

(defn get-date-string [post]
  (let [date (new js/Date. (get post :inserted_at))]
    (.format (js/moment date) "MMMM YYYY")
    )
  )


(defn map-for-timeline [posts]
  (let [mapper
        (fn [{:keys [id created_at updated_at] :as post}]
          (let [renamed (rename-keys post {:inserted_at :created_at})
                mapped (assoc renamed :views 30,
                              :edit-href (routes/edit-path)
                              :created_at (.format (js/moment) "MMMM Do, YYYY")
                              :updated_at (.format (js/moment) "MMMM Do, YYYY")
                              )
                ]
            mapped
            )
          )
        ]
       (map mapper posts)
    )
  )

(defn as-timeline [posts]
  (let [group-by-month (fn [acc post]
                (let [date (get-date-string post)
                      ]
                  (update acc date (fn [old]
                                     (into [] (conj old post))
                                     ))
                 )
              )
         grouped (reduce group-by-month {} posts)
         ]
    (for [[month posts] grouped] {:month month
                                  :posts (into [] (map-for-timeline (sort-by by-date posts)))
                                  }))
  )

(def global-posts (r/atom nil))

(def posts (pipe global-posts as-timeline))

(defn posts-loading? []
  (= @posts nil)
  )

(defn fetch-posts []
  (go
    (reset! global-posts
            (<p!  
             (req/do-get "/admin/api/posts")))
    )
  )

(defn get-posts []
  (do
    (if (posts-loading?)
      (fetch-posts)
      )
    )
    posts
  )
