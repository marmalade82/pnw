(ns admin.screens.skills-page.delete-form
  (:require
   [reagent.core :as r]
   [reagent.dom :as d]
   [component-lib.core :as c]
   [component-lib.buttons :as b]
   [fork.reagent :as f]
   [admin.external.side-effect :refer [side-effect]]
   [admin.external.requests :refer [request-status request!] ]
   ))

(defn delete-form [{:keys [values
                          set-values
                          handle-change
                          handle-submit
                          submitting?
                          form-id
                          props
                          ] :as all}]
  (let [form-state request-status
        close! (:close! props)
        ]
    (fn []
      [:form {:class "SkillsPage-DeleteForm"
              :id form-id
              :on-submit handle-submit
              }
       [side-effect {:is-true (= :finished @form-state)
                     :on-true #(close!)
                     }]
       [c/text {:class "SkillsPage-DeleteForm-Text"} (:text props)]
       [:div {:class "SkillsPage-DeleteForm-Footer"}
        [b/cancel {:disabled (not= @form-state :ready)
                   :class "SkillsPage-Modal-Cancel"
                   :on-click #(close!)
                   }]

        [c/submit-button { :disabled (not= @form-state :ready)
                          :class "SkillsPage-Modal-Delete"
                          }
         "yes, delete"]
        ]
       ]))
  )

(defn render-delete-form [{:keys [close! text] :or {text ""} :as props}]
  [f/form {:prevent-default? true
           :clean-on-unmount? true
           :path :delete-form 
           :on-submit #(request!)
           :props props
           }
   delete-form]
   )
