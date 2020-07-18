(ns admin.screens.skills-page.skills-form
  (:require
   [reagent.core :as r]
   [reagent.dom :as d]
   [component-lib.core :as c]
   [component-lib.buttons :as b]
   [fork.reagent :as f]
   ))

(defn skill-form [{:keys [values
                          set-values
                          handle-change
                          handle-submit
                          submitting?
                          form-id
                          props
                          ] :as all}]
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
                      :class "SkillsPage-Modal-Submit"
                     }
    (if (:edit? props) "save" "add")]
   ]
  )

(defn render-edit-form []
  [f/form {:prevent-default? true
           :clean-on-unmount? true
           :path :edit-form 
           :on-submit #(identity %)
           :props {:edit? true}
           
           }
   skill-form]
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
