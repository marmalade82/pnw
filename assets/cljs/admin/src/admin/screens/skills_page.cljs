(ns admin.screens.skills-page
  (:require
   [reagent.core :as r]
   [reagent.dom :as d]
   [component-lib.core :as c]
   [component-lib.buttons :as b]
   [component-lib.icons :as i]
   [component-lib.containers :refer [mk-modal]]
   [admin.components.header :refer [header]]
   [admin.screens.skills-page.delete-form :refer [render-delete-form]]
   [admin.screens.skills-page.skills-form
       :refer [render-edit-form render-skill-form]]
   [admin.components.body :refer [body]]
   [admin.components.motion.motion :as m]
   [admin.global-state.skills :refer [get-skills]]
   )
  )





(defn render-skill [{:keys [id name abbr color]
                     :or {name "", abbr "", color "white"} :as skill}]
  (let [{:keys [toggle! close! stage modal disable-unless?]} (mk-modal)
        ]
    (fn []  
       [:div {:class "SkillsPage-Skill"}
        [:div {:class "SkillsPage-Skill-Main"}
         [c/badge {:class "SkillsPage-Skill-Badge"
                   :color color
                   } [c/text {:class "SkillsPage-Skill-Abbr"} abbr]]
         [c/text {:class "SkillsPage-Skill-Name"} name]
         ]
        [:div {:class "SkillsPage-Skill-Side"}
         [b/edit {:on-click #(toggle! :edit)
                  :disabled (disable-unless? :edit)
                  :class "SkillsPage-Edit"
                  }]
         [b/delete {:on-click #(toggle! :delete)
                    :disabled (disable-unless? :delete)
                    :class "SkillsPage-Delete"
                    }]
         ]
        [modal {:title {:edit "Edit", :delete "Are you sure?"}}
         {:edit [render-edit-form {:close! close!
                                   :id id
                                   :initial skill
                                   }]
             :delete [render-delete-form
                      {:close! close!
                       :text (str "Please confirm that you want to delete " name ".")
                       }]
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




(defn skills-page [{:keys [class] :or {class ""}}]
  (let [skills (get-skills)
        {:keys [modal open! close! toggle!]} (mk-modal)
        ]
    (fn []
      [c/surface {:class (str "SkillsPage-Main" " " class)}
         [c/surface-nav-header {
                                :left-hiccup [:<>
                                               [c/text {:type 1} "Skills"]
                                              ]
                                :right-hiccup
                                [:<>
                                 [b/add {:class "SkillsPage-Header-Add"
                                         :on-click #(toggle! :add)
                                         }]
                                 ]
                                }]
         [c/surface-body
          [modal {:title {:add "Add Skill"}} {:add [render-skill-form {:close! close!}]}]
          [search-bar]
          (render-skills @skills)
          ]
         ]
      )
    )
  )
