(ns admin.screens.timeline-page
  (:require
   [reagent.core :as r]
   [reagent.dom :as d]
   [component-lib.icons :as i]
   [component-lib.core :as c]
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
            ]
    }
   ]
  )

(defn render-blog [{:keys [title created_at updated_at views edit-href]}]
  (let [stage (r/atom :view)]
    [:div {:class "TimelinePage-Blog"
           }
     [:div {:class "TimelinePage-Blog-Main"}
      [c/text {:class "TimelinePage-Blog-Title"} title]
      [c/text {:class "TimelinePage-Blog-Created"} created_at]
      [c/text {:class "TimelinePage-Blog-Updated"} updated_at]
      [c/text {:class "TimelinePage-Blog-Views"} views]
      ]
     [:div {:class "Timeline-Blog-Side"}
      [c/button {:href edit-href
                 :class "Link"
                 }
       [c/text "Edit"]
       [i/edit]
       ]
      [c/button {:on-click #(reset! stage :preview)
                 ; will popup preview modal
                 }
       [c/text "Preview"]
       [i/preview]
       ]
      [c/button {:on-click #(reset! stage :delete)
                 ; will popup a delete confirmation modal
                 }
       [c/text "Delete"]
       [i/delete]
       ]
      ]
     ])
  )

(defn render-month [{:keys [month posts]}]
  (into [:div {:class "TimelinePage-Month"
               }
         [c/text {:class "TimelinePage-Month-Header"
                  :type 2
                  } month]
         ] (map render-blog posts))
  )

(defn timeline-page []
  (let [blog-data (get-grouped-blog-data)
        ]
    [:div {:class "TimelinePage"}
      [c/text {:type 1} "Posts"]
      (into [:div {:class "TimelinePage-Timeline"}] (map render-month blog-data))
     ]
    )
  )


