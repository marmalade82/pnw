(ns admin.screens.project-timeline-page
  (:require
   [reagent.core :as r]
   [reagent.dom :as d]
   [component-lib.core :as c]
   [component-lib.buttons :as b]
   [component-lib.containers :refer [mk-modal]]
   [component-lib.icons :as i]
   [admin.routes :refer [project-edit-path project-add-path]]
   [admin.components.header :refer [header]]
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
     :edit-href (project-edit-path)
     }
    { :last_updated "Oct 3, 2020"
     :title "pnw"
     :start "Oct 3, 2018"
     :subtitle "Personal website"
     :skills [{:color "red", :label "CLJS"}
              {:color "green", :label "ELX"}
              ]
     :edit-href (project-edit-path)
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
      :edit-href (project-edit-path)
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
     :edit-href (project-edit-path)
     
     }
    
    ]
   }
  )

(defn render-project [{:keys [title subtitle skills last_updated
                              edit-href
                              ]}]
  (let [{:keys [modal toggle! disable-unless?] } (mk-modal)]
    (fn [] 
      [:div {:class "ProjectTimelinePage-Project"}
       [:div {:class "ProjectTimelinePage-Content"}
        [:div {:class "ProjectTimelinePage-Main"}
         [:div {:class "ProjectTimelinePage-Details"}
          [c/text {:class "ProjectTimelinePage-Project-Title"} title]
          ]
         (into [:div {:class "ProjectTimelinePage-Project-Skills"}
                [c/text {:class "ProjectTimelinePage-Project-Updated"}
                 (str "Last updated: " last_updated)]
                ]
               #_(map #(into [] [c/badge {:color "pink"
                                        :class "ProjectTimelinePage-Project-SkillBadge"
                                        } [c/text (:label %)]]) skills)
               )
         ]
        [:div {:class "ProjectTimelinePage-Side"}
         [b/edit {:href edit-href}]
         [b/preview {:on-click #(toggle! :preview)
                     :disabled (disable-unless? :preview)
                     }]
         [b/delete {:on-click #(toggle! :delete)
                    :disabled (disable-unless? :preview)
                    }]
         ]]
       [modal {:title {:preview "Preview" :delete "Delete"}}
        {:preview [:div "Preview"], :delete [:div "Delete"]}]
       ]))
  )

(defn render-projects [projects]
  (into [:div {:class "ProjectTimelinePage-Projects"}]
        (map #(into [] [(render-project %)]) projects))
  )

(defn project-timeline-page []
  (let [{:keys [ongoing completed abandoned]
         :or
            {ongoing []
              completed []
              abandoned []}}
            (get-grouped-projects) 
        ]
    [c/page {:class "ProjectTimelinePage"}
     [header]
     [c/body
      [c/surface {:class "ProjectTimelinePage-Surface"}
       [:div {:class "ProjectTimelinePage-TopHeader"}
        [:div {:class "ProjectTimelinePage-TopHeader-First"}
         [i/folder]
         [c/text {:type 1} "Projects"]
         ]
        [b/add {:class "ProjectTimelinePage-Add", :href (project-add-path)}]
        ]
       [c/text {:class "ProjectTimelinePage-Summary"} "A timeline of everything you've done so far."]

       [:section
        [c/text {:type 2 } "Ongoing"]
        (render-projects ongoing)
        ]

       [:section
        [c/text {:type 2} "Completed"]
        (render-projects completed)
        ]

       [:section
        [c/text {:type 2} "Abandoned"]
        (render-projects abandoned)
        ]
       ]
      ]
     ]
    )
  )


