(ns front-end.screens.home-page
  (:require [reagent.core :as r]
            [reagent.dom :as d]
            [front-end.components.styled.ordered-list :refer [ordered-list]]
            [front-end.components.styled.unordered-list :refer [unordered-list]]
            [front-end.screens.home-page.data :as data]

  ))

(defn render-work-experience
  [{:keys [title company description achievements]}]

  [:<>
    [:span {:class "HomePage-Text"} title] 
    [:span {:class "HomePage-Text"} (str " @" company)]
    [:span {:class "HomePage-Text"} description]
   ]
  )

(defn render-clean-code [{:keys [text href]}]
  [:<>
    [:span {:class "HomePage-Text"} text]
    [:a {:href href}
     [:span {:class "HomePage-Text"}
        "here"
      ]
      ]
   ]
  )

(defn render-project [{:keys [title description href ]}]
  [:<>
    [:span {:class "HomePage-Text"} title]
    [:span {:class "HomePage-Text"} description]
    [:a {:href href}
     [:span {:class "HomePage-Text"} "here"]
      ]
   ]
  )

(defn home-page []
  (let [work-experience (map render-work-experience (data/work-experience))
        clean-code (map render-clean-code (data/clean-code))
        projects (map render-project (data/projects))
        ]
    [ :article {:class "HomePage-Container" }
     [:span {:class "HomePage-Intro"}
      "My name is Howard. I'm a full-stack developer who loves the backend!"
      ]

     [:h3 {:class "HomePage-WorkExperienceHeader"}
      "Work experience"
      ]
     (into [ordered-list {:class "HomePage-List"}] work-experience)

     [:h3 {:class "HomePage-CleanCodeHeader"}
      "Approach to clean code" 
      ]
     [:span {:class "HomePage-Text"}
      "To me, clean code has three major components:"
      ]
     (into [ordered-list {:class "HomePage-List"}] clean-code)

     [:h3 {:class "HomePage-ProjectsHeader"}
      "Personal projects"
      ]
     (into [unordered-list {:class "HomePage-List"}] projects)
    ]))
