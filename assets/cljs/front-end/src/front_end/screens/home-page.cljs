(ns front-end.screens.home-page
  (:require [reagent.core :as r]
            [reagent.dom :as d]
            [front-end.components.styled.ordered-list :refer [ordered-list]]
            [front-end.components.styled.unordered-list :refer [unordered-list]]
            [front-end.screens.home-page.data :as data]
            [front-end.components.styled.text :refer [my-text]]
            [front-end.components.styled.footer :refer [footer]]
            [front-end.components.styled.header :refer [header]]
  ))

(defn- body-text
  ([text class]
    [my-text {:class class
              :type "body"
              :text text
              }])
  ([text] (body-text text ""))
  )

(defn- render-work-experience
  [{:keys [title company description achievements]}]

  [:<>
    (body-text title "HomePage-Work-Title")
    (body-text (str " @" company) "HomePage-Work-Company")
    (body-text description "HomePage-Work-Description")
   ]
  )

(defn- render-clean-code [{:keys [text href]}]
  [:<>
    (body-text text "HomePage-Clean-Description")
    [:a {:href href}
     (body-text "here" "HomePage-Clean-Link")
      ]
   ]
  )

(defn- render-project [{:keys [title description href ]}]
  [:<>
    (body-text title "HomePage-Project-Title")
    (body-text description "HomePage-Project-Description")
    [:a {:href href}
     (body-text "here" "HomePage-Project-Link")
      ]
   ]
  )

(defn home-page []
  (let [work-experience (map render-work-experience (data/work-experience))
        clean-code (map render-clean-code (data/clean-code))
        projects (map render-project (data/projects))
        ]
    [:div {:class "Body"}
     [header ]
     [ :article {:class "HomePage-Container" }
      (body-text
       "My name is Howard. I'm a full-stack developer who loves the backend!"
       )

      [:h3 {:class "HomePage-WorkExperienceHeader"}
       "Work experience"
       ]
      (into [ordered-list {:class "HomePage-WorkList"}] work-experience)

      [:h3 {:class "HomePage-CleanCodeHeader"}
       "Approach to clean code" 
       ]
      (body-text
       "To me, clean code has three major components:"
       )
      (into [ordered-list {:class "HomePage-CleanCodeList"}] clean-code)

      [:h3 {:class "HomePage-ProjectsHeader"}
       "Personal projects"
       ]
      (into [unordered-list {:class "HomePage-ProjectsList"}] projects)

      ]
      [footer]
     ]

    ))
