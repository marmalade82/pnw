(ns admin.screens.project-timeline-page
  (:require
   [reagent.core :as r]
   [reagent.dom :as d]
   [component-lib.core :as c]
   ))

(defn get-grouped-projects []
  {:ongoing
   [{ :last_updated "Oct 1, 2020"
      :start "Oct 1, 2019"
      :title "lexer-gen"
      :subtitle "Purescript lexing"
      :skills [{:color "red", :label "CSS"},
               {:color "purple", :label "PS"}
              ]
     }
    
    ]
   :completed
   [{ :last_updated "Oct 3, 2020"
      :title "pnw"
      :start "Oct 3, 2018"
      :subtitle "Personal website"
     :skills [{:color "red", :label "CLJS"}
              {:color "green", :label "ELX"}
              ]
     }
    
    ]
   :abandoned
   [{:last_updated "Oct 14, 2020"
     :title "spice"
     :start "January 2020"
     :subtitle "Task management and learning"
     :skills [{:color "purple", :label "RN"}
              {:color "pink" :label "JV"}
              ]
     
     }
    
    ]
   }
  )

(defn render-project [{:keys [title subtitle skills last_updated]}]
  [:div {:class "ProjectTimelinePage-Project"}
     [c/text {:class "ProjectTimelinePage-Project-Title"} title]
     [c/text {:class "ProjectTimelinePage-Project-Subtitle"} subtitle]
     [c/text {:class "ProjectTimelinePage-Project-Updated"}
         (str "Last updated: " last_updated)]
     (into [:div {:class "ProjectTimelinePage-Project-Skills"}]
           (map #(into [] [c/badge {:color "pink"
                                  :class "ProjectTimelinePage-Project-SkillBadge"
                                  } (:label %)]) skills)
         )
   ]
  )

(defn render-projects [projects]
  (into [:div {:class "ProjectTimelinePage-Projects"}]
        (map render-project projects))
  )

(defn project-timeline-page []
  (let [{:keys [ongoing completed abandoned]
         :or
            {ongoing []
              completed []
              abandoned []}}
            (get-grouped-projects) 
        ]
    [:div {:class "ProjectTimelinePage"}
     [c/text {:type 1} "Projects"]
     [c/text {:type 2 } "Ongoing"]
     (render-projects ongoing)
     [c/text {:type 2} "Completed"]
     (render-projects completed)
     [c/text {:type 2} "Abandoned"]
     (render-projects abandoned)
     ]
    )
  )


