(ns admin.screens.skills-page
  (:require
   [reagent.core :as r]
   [reagent.dom :as d]
   [component-lib.core :as c]
   [component-lib.buttons :as b]
   [component-lib.containers :refer [mk-modal]]
   [fork.reagent :as f]
   )
  )

(defn get-skills []
  [{:name "JavaScript"
    :abbr "JS"
    :color "pink"
    }
   {:name "TypeScript"
    :abbr "TS"
    :color "blue"
    }
   ]
  )

(defn render-skill [{:keys [name abbr color]
                     :or {name "", abbr "", color "white"}}]
  (let [{:keys [toggle! close! stage modal disable-unless?]} (mk-modal)
        ]
    (fn []  
       [:div {:class "SkillsPage-Skill"}
        [:div {:class "SkillsPage-Skill-Main"}
         [c/text {:class "SkillsPage-Skill-Name"} name]
         [c/badge {:class "SkillsPage-Skill-Badge"
                   :color color
                   } [c/text {:class "SkillsPage-Skill-Abbr"} abbr]]
         ]
        [:div {:class "SkillsPage-Skill-Side"}
         [b/edit {:on-click #(toggle! :edit)
                  :disabled (disable-unless? :edit)
                  }]
         [b/delete {:on-click #(toggle! :delete)
                    :disabled (disable-unless? :delete)
                    }]
         ]
        [modal {:title {:edit "Edit", :delete "Delete"}}
            {:edit [:div "edit form"]
                   :delete [:div "delete form"]
                   }]
        ]))
  )

(defn render-skills [skills]
  (into [:div {:class "SkillsPage-Skills"}]
        (map #(into [] [(render-skill %)]) skills)
        )
  )

(defn search-bar []
  [:div {:class "SkillsPage-Search"}
   [:input {:type "text"}]
   [b/search]
   ]
  )

(defn skill-form [{:keys [values
                          set-values
                          handle-change
                          handle-submit
                          submitting?
                          form-id]}]
  [:form {:class "SkillsPage-Form"
          :id form-id
          :on-submit handle-submit
          }
   [c/input-group {:label "Name"
                   :id "name"
                   :on-change handle-change
                   :value (values "name")
                   }]
   [c/input-group {:label "Abbreviation"
                   :id "abbr"
                   :value (values "abbr")
                   :on-change handle-change
                   }]
   [c/input-group {:label "Color"
                   :type "color"
                   :id "color"
                   :value (values "color")
                   :on-change handle-change
                   }]
   [c/submit-button { :disabled submitting?
                     }
     "Add"]
   ]
  )

(defn render-skill-form []
  [f/form {:prevent-default? true
           :clean-on-unmount? true
           :path :skill-form 
           :on-submit #(identity %)
           
           }
          skill-form
          ]
  )


(defn skills-page []
  (let [skills (get-skills)
        {:keys [modal open! close! toggle!]} (mk-modal)
        ]
    (fn []
      [:div {:class "SkillsPage"}
       [:div {:class "SkillsPage-Header"}
        [c/text {:type 1} "Skills"]
        [b/add {:class "SkillsPage-Header-Add"
                :on-click #(toggle! :add)
                }]
        ]
       [modal {:title {:add "Add Skill"}} {:add [render-skill-form]}]
       [search-bar]
       (render-skills skills)
       ])
    )
  )
