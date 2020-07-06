(ns front-end.screens.home-page
  (:require
            [reagent.core :as r]
            [reagent.dom :as d]
            [front-end.components.styled.ordered-list :refer [ordered-list]]
            [front-end.components.styled.unordered-list :refer [unordered-list]]
            [front-end.components.styled.layout :refer [layout]]
            [front-end.screens.home-page.data :as data]
            [front-end.components.styled.text :refer [my-text]]
            [front-end.components.styled.article :refer [article]]
            [front-end.components.styled.link-text :refer [link-text]]
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
  [{:keys [title company description achievements start end href]}]

  [:<>
   [link-text {:href href}
    (body-text title "HomePage-Work-Title")
    ]
    [:div {:class "HomePage-Work-Subtitle"}
     (body-text company "HomePage-Work-Company")
     (body-text (str start " \u2013 " end) "HomePage-Work-Dates")
      ]
    (body-text description "HomePage-Work-Description")
   ]
  )


(defn- render-clean-code [{:keys [text href]}]
  [:<>
   [link-text {:href href}
    (body-text text "HomePage-Clean-Description")
    ]
   ]
  )

(defn- render-project [{:keys [title description href ]}]
  [:<>
   [link-text {:href href, :class "HomePage-Project-Link"}
    (body-text title "HomePage-Project-Title")
    ]
    (body-text "\u2022" "HomePage-Work-Separator")
    (body-text description "HomePage-Project-Description")
   ]
  )

(defn home-page [{:keys [label]}]
  (let [work-experience (map render-work-experience (data/work-experience))
        clean-code (map render-clean-code (data/clean-code))
        projects (map render-project (data/projects))
        ]
    [layout {:label label}
      [ article {:class "HomePage-Container" }

        [my-text {:class "HomePage-WorkExperienceHeader", 
                :type "3", :text "Work Experience"}
        ]
        (into [ordered-list {:class "HomePage-WorkList"}] work-experience)

       [my-text {:class "HomePage-CleanCodeHeader" :type "3" 
                 :text "Approach to clean code"} 
        ]
        (body-text
        "To me, clean code has three major components:"
        )
        (into [ordered-list {:class "HomePage-CleanCodeList"}] clean-code)

       [my-text {:class "HomePage-ProjectsHeader", :type "3",
                 :text "Personal projects"}
        ]
        (into [unordered-list {:class "HomePage-ProjectsList"}] projects)

        ]
     ]

    ))
