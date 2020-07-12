(ns front-end.screens.work-page
  (:require
   [reagent.core :as r]
   [reagent.dom :as d]
   [front-end.components.styled.layout :refer [layout]]
   [front-end.screens.work-page.data :refer [get-work-experience]]
   [front-end.components.styled.unordered-list :refer [unordered-list]]
   [front-end.components.styled.text :refer [my-text]]
   [front-end.components.styled.article :refer [article]]
   ))

(defn render-achievement [achievement]
  [my-text {:class "WorkPage-Experience-Achievement"
            :text achievement}
   ]

  )

(defn render-main-language [lang]
  [my-text {:class "WorkPage-Experience-Language"
            :text lang}]
  )

(defn render-work-experience [{:keys [title company company_href description achievements start end main_languages]}]
  [:div {:class "WorkPage-Experience"}
   [my-text {:class "WorkPage-Experience-Title", :type "2", :text title}]
   [:div {:class "WorkPage-Experience-Subheader"}
    [:a {:href company_href
         :target "_blank"
         }
     [my-text {:class "WorkPage-Experience-Company", :type "3", :text  company}]]
    [my-text {:class "WorkPage-Experience-Dates",
              :text  (str start " \u2013 " end)}]]
   [my-text {:class "WorkPage-Experience-Description",
             :text  description}]
   (let [rendered (map render-achievement achievements)]
     (into [unordered-list {:class "WorkPage-Experience-Achievements"}]
           rendered)
     )
   (let [rendered (map render-main-language main_languages)]
     (into [:div {:class "WorkPage-Experience-Languages"}] rendered)
     )
   ]
  )


(defn work-page [{:keys [label topic]}]
  (let [work-experience (get-work-experience topic)]
    (fn [] 
      [layout {:label label}
       [article {:class "WorkPage-Container"}
        [render-work-experience work-experience ]
        ]
       ]))
  )
