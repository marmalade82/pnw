(ns admin.screens.skills-page.delete-form
  (:require
   [reagent.core :as r]
   [reagent.dom :as d]
   [component-lib.core :as c]
   [component-lib.buttons :as b]
   [fork.reagent :as f]
   ))

(defn delete-form [{:keys [values
                          set-values
                          handle-change
                          handle-submit
                          submitting?
                          form-id
                          props
                          ] :as all}]
  [:form {:class "SkillsPage-DeleteForm"
          :id form-id
          :on-submit handle-submit
          }
   [c/text {:class "SkillsPage-DeleteForm-Text"} (:text props)]
   [:div {:class "SkillsPage-DeleteForm-Footer"}
    [b/cancel {:disabled submitting?
               :class "SkillsPage-Modal-Cancel"
               :on-click (:close! props)
               }]

    [c/submit-button { :disabled submitting?
                      :class "SkillsPage-Modal-Delete"
                      }
     "yes, delete"]
    ]
   ]
  )

(defn render-delete-form [{:keys [close! text] :or {text ""} :as props}]
  [f/form {:prevent-default? true
           :clean-on-unmount? true
           :path :delete-form 
           :on-submit #(identity %)
           :props props
           }
   delete-form]
   )
