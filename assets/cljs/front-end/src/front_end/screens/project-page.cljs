(ns front-end.screens.project-page
  (:require
    [reagent.core :as r]
    [reagent.dom :as d]
    [front-end.components.styled.layout :refer [layout]]
    [front-end.screens.project-page.data :refer [get-project]]
    [front-end.components.styled.text :refer [my-text]]
    [front-end.components.styled.article :refer [article]]
    [front-end.components.styled.icons :as icons]
   ))

(defn render-thought [thought]
  [:p {:class "Project-Thought"} thought]
  )

(defn render-project [
                      {:keys [title subtitle thoughts
                              github_href live_href]}
                      ]
  [:div {:class "Project"}
   [:div {:class "Project-Header"}
    [:h2 {:class "Project-Title"} title]
    [:a {:href github_href
         :target "_blank"
         }
      [icons/github {:class "Project-Github"}]
     ]
    (if live_href 
      [:a {:href live_href, :target "_blank"
           }
       [:div {:class "Project-Live-Container"}
        [my-text {:class "Project-Live", :text "Live"}
         ]
        [icons/website]
        ]
       ])
    ]
   [:h3 {:class "Project-Description"} subtitle]
   (let [rendered (map render-thought thoughts)]
     (into [:div {:class "Project-Thoughts"}] rendered)
     )
   ]
  )

(defn project-page [{:keys [label topic]}]
  (let [project (get-project topic)]
    (fn []
      [layout {:label label}
       [article {:class "ProjectPage-Article"
                  }
          (render-project project)
        ]
       ]
      )
    )
  )

