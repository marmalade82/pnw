(ns admin.screens.timeline-page
  (:require
   [reagent.core :as r]
   [reagent.dom :as d]
   [component-lib.icons :as i]
   [component-lib.core :as c]
   [component-lib.buttons :as b]
   [component-lib.containers :refer [mk-modal]]
   [admin.components.header :refer [header]]
   [admin.routes :refer [add-post-path]]
   [admin.global-state.posts :as p]
   ))

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

(defn render-blog [{:keys [title created_at updated_at views edit-href]}]
  (let [stage (r/atom :view)
        {:keys [modal toggle! disable-unless?]} (mk-modal)
        ]
    (fn [] 
      [:div {:class "TimelinePage-Blog"
             }
       [:div {:class "TimelinePage-Blog-Content"}
        [:div {:class "TimelinePage-Blog-Main"}
         [c/text {:class "TimelinePage-Blog-Title"} title]
         [c/text {:class "TimelinePage-Blog-Created"} created_at]
         [c/text {:class "TimelinePage-Blog-Updated"} updated_at]
         [c/text {:class "TimelinePage-Blog-Views"} views]
         ]
        [:div {:class "Timeline-Blog-Side"}
         [b/edit {:href edit-href
                  :class "Link"
                  }
          ]
         [b/preview {:on-click #(toggle! :preview)
                     :disabled (disable-unless? :preview)
                     }
          ]
         [b/delete {:on-click #(toggle! :delete)
                    :disabled (disable-unless? :delete)
                    }
          ]
         ]
        ]
       [modal {:title {:preview "Preview" :delete "Delete"}} {:preview [:div "Preview"]
                   :delete [:div "Delete"]
                    }]
       ]))
  )

(defn render-month [{:keys [month posts]}]
  (into [:section {:class "TimelinePage-Month"
               }
         [c/text {:class "TimelinePage-Month-Header"
                  :type 2
                  } month]
         ] (map #(into [] [(render-blog %)]) posts))
  )

(defn timeline-page [{:keys [class] :or {class ""}}]
  (let [blog-data (p/get-posts)
        ]
    [c/surface {:class (str "TimelinePage-Surface" " " class)}
       [c/surface-nav-header {:class "TimelinePage-Header"
                              :left-hiccup
                              [:<>
                               [c/text {:type 1} "Posts"]
                               ]
                              :right-hiccup
                              [:<>
                               [b/add {:href (add-post-path)}]
                               ]
                              }
        ]
       [c/surface-body {:class "TimelinePage-Body"}
        (if (p/posts-loading?)
          [:div {:class "LoadingSpinner"}
              [:div {:class "LoadingSpinner-Rotate"}]
           ]
          (into [:div {:class "TimelinePage-Timeline"}]
                (map render-month blog-data))
          )
        ]
       ]
    )
  )


