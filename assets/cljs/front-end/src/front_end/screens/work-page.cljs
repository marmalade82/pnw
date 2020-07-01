(ns front-end.screens.work-page
  (:require
   [reagent.core :as r]
   [reagent.dom :as d]
   [front-end.components.styled.layout :refer [layout]]
   [front-end.screens.work-page.data :refer [get-work-experience]]
   [front-end.components.styled.unordered-list :refer [unordered-list]]
   [front-end.components.styled.text :refer [my-text]]
   ))

(defn render-achievement [achievement]
  [my-text {:class "WorkPage-Experience-Achievement"
            :text achievement}
   ]

  )

(defn render-work-experience [{:keys [title company description achievements start end href]}]
  [:div {:class "WorkPage-Experience"}
   [:h2 {:class "WorkPage-Experience-Title"} title]
   [:h3 {:class "WorkPage-Experience-Company"} company]
   [my-text {:class "WorkPage-Experience-Description",
             :text  description}]
   [my-text {:class "WorkPage-Experience-Dates",
             :text  (str start " \u2013 " end)}]
   (let [rendered (map render-achievement achievements)]
     (into [unordered-list {:class "WorkPage-Experience-Achievements"}]
           rendered)
     )
   ]
  )


(defn work-page [{:keys [label topic]}]
  (let [work-experience (get-work-experience topic)]
    (fn [] 
      [layout {:label label}
       [:article {:class "WorkPage-Container"}
        [render-work-experience work-experience ]
        ]
       ]))
  )
