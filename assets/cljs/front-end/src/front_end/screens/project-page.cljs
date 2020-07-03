(ns front-end.screens.project-page
  (:require
    [reagent.core :as r]
    [reagent.dom :as d]
    [front-end.components.styled.layout :refer [layout]]
    [front-end.screens.project-page.data :refer [get-project]]
    [front-end.components.styled.text :refer [my-text]]
    [front-end.components.styled.article :refer [article]]
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
    [:a {:href github_href} "github"]
    [:a {:href live_href} "live"]]
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

