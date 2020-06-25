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
      "Work experience"
    ]
   [:ol {:class "HomePage-List"}
    [:li {:class "HomePage-ListItem"}
     [:span {:class "HomePage-Text"}
       "ServiceNow Technical Lead @Cognizant"
      ]
     [:span {:class "HomePage-Text"}
      ]
     [:ul {:class "HomePage-List"}
      [:li {:class "HomePage-ListItem"}
       [:span {:class "HomePage-Text"}
         "Responsible for communicating technical requirements to development teams while also providing operational support in the form of hotfixes, development, and debugging."
        ]
       ]
      ]
     ]
    [:li {:class "HomePage-ListItem"}
     [:span {:class "HomePage-Text"}
      "ServiceNow Developer @Revature"
      ]
     [:ul {:class "HomePage-List"}
      [:li {:class "HomePage-ListItem"}
       [:span {:class "HomePage-Text"}
        "Responsible for business analysis, form development, workflow implementation, database configuration, and testing"
        ]
       ]
      ]
     ]
    ]
   [:h3 {:class "HomePage-CleanCodeHeader"}
      "Approach to clean code" 
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
    "Personal projects"
    ]
   [:ol {:class "HomePage-List"}
     [:li {:class "HomePage-ListItem"}
       [:span "lexer-gen"]
     ]
     [:li {:class "HomePage-ListItem"}
       [:span "spice"]
     ]
    [:li {:class "HomePage-ListItem"}
       [:span "dev-hub"]
     ]
    [:li {:class "HomePage-ListItem"}
     [:span "e-calendar"]
     ]
   ]

  ])
