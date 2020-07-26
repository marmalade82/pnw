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
   [admin.components.spinner-page :refer [spinner-page]]
   [admin.global-state.projects :refer [get-projects]]
   ))


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

(defn project-timeline-page [attrs]
  (let [projects (get-projects)]
    (fn [{:keys [class] :or {class ""}}]
        (let [{:keys [ongoing completed abandoned]
               :or
               {ongoing []
                completed []
                abandoned []}}
              @projects
              ]
          [c/surface {:class (str "ProjectTimelinePage-Surface" " " class)}
           [c/surface-nav-header {:class "ProjectTimelinePage-TopHeader"
                                  :left-hiccup
                                  [:<>
                                   [i/folder]
                                   [c/text {:type 1} "Projects"]
                                   ]
                                  :right-hiccup
                                  [:<>
                                   [b/add {:class "ProjectTimelinePage-Add", :href (project-add-path)}]
                                   ]

                                  }
            ]
           (if (nil? @projects)
             [spinner-page]
             [c/surface-body
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
              ])
           ]
          )))
  )


