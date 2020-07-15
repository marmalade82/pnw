(ns admin.screens.timeline-page
  (:require
   [reagent.core :as r]
   [reagent.dom :as d]
   [component-lib.icons :as i]
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
             :edit-href "#/edit/7"
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
             :edit-href "#/edit/3"
             }
            ]
    }
   ]
  )

(defn render-blog [{:keys [title created_at updated_at views]}]
  [:div {:class "TimelinePage-Blog"
         }
    [:span title]
    [:span created_at]
    [:span updated_at]
    [:span views]
    [i/preview]
   ]
  )

(defn render-month [{:keys [month posts]}]
  (into [:div {:class "TimelinePage-Month"
               }
         [:h2 month]
         ] (map render-blog posts))
  )

(defn timeline-page []
  (let [blog-data (get-grouped-blog-data)
        ]
    [:div {:class "TimelinePage"}
      (into [:div {:class "TimelinePage-Timeline"}] (map render-month blog-data))
     ]
    )
  )


