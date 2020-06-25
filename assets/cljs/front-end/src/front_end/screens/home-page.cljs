(ns front-end.screens.home-page
  (:require [reagent.core :as r]
            [reagent.dom :as d]
  ))

(defn home-page []
  [ :div {:class "HomePage-Container" }
   [:span {:class "HomePage-Intro"}
      "My name is Howard. I'm a full-stack developer who loves the backend!"
    ]

   [:h3 {:class "HomePage-WorkExperienceHeader"}
      "My work experience"
    ]
   [:h3 {:class "HomePage-CleanCodeHeader"}
      "My approach to clean code" 
    ]
   [:span {:class "HomePage-Text"}
      "To me, clean code means three things:"
    ]
   [:ol {:class "HomePage-List"}
    [:li {:class "HomePage-ListItem"}
     [:span {:class "HomePage-Text"}
        "Literate/Declarative programming"
      ]
     ]
    [:li {:class "HomePage-ListItem"}
     [:span {:class "HomePage-Text"}
        "Organizing code as service layers"
      ]
     ]
    [:li {:class "HomePage-ListItem"}
     [:span {:class "HomePage-Text"}
      "Using object-oriented ADTs only when truly needed"
      ]
     ]
    ]

   [:h3 {:class "HomePage-ProjectsHeader"}
    "My personal projects"] 
   ]
  )
