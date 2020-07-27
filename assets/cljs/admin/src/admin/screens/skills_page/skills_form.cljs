(ns admin.screens.skills-page.skills-form
  (:require
   [reagent.core :as r]
   [reagent.dom :as d]
   [component-lib.core :as c]
   [component-lib.buttons :as b]
   [fork.reagent :as f]
   [component-lib.logic.requests :as req]
   [admin.external.side-effect :refer [side-effect]]
   [admin.external.requests :refer [request-status request!] ]
   [admin.external.response-broker :as broker]
   [cljs.core.async :refer [go]]
   [cljs.core.async.interop :refer [<p!]]
   ))


(defn skill-form [{:keys [ props
                          ] :as all}]
  (let [form-state request-status
        close! (:close! props)
        ]
    (fn [{:keys [values
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
       [side-effect {:is-true (= :finished @form-state)
                     :on-true #(close!)
                     }]
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
       [c/submit-button { :disabled (not= :ready @request-status)
                         :class "SkillsPage-Modal-Submit"
                         }
        (if (:edit? props) "save" "add")
        ]
       ]))
  )

(defn edit-skill [vals]
  ; this function should submit the values to the server.
  ; when the results return, the returned results should
  ; be passed to the results handler for processing
  (request!)
  
  )

(defn form-data [vals]
  (let [form (js/FormData.)]
    (do
      (doseq [[k v] vals]
          (.append form (clj->js k) (clj->js v))
        )
      form
      )
    )
  )

(defn new-skill [vals]
  (go
    (let [req (js/fetch "http://localhost:4000/admin/api/skills"
                        #js { "method" "POST",
                             "body" (form-data vals)
                             })
          res (<p! req)
          json (js->clj (<p! (.json res)) :keywordize-keys true)
          ]
      (js/console.log json)
      (broker/send :new-skill json)
      )
    )
  #_(request!)
  )

(defn render-edit-form [{:keys [close!] :or {close! #(identity 1)}}]
  [f/form {:prevent-default? true
           :clean-on-unmount? true
           :path :edit-form 
           :on-submit (fn [{:keys [state path values touched errors dirty]}]
                         (edit-skill values)
                         )
           :props {:edit? true
                   :close! close!
                   }
           
           }
   skill-form]
  )

(defn render-skill-form [{:keys [close!] :or {close! #(identity 1)}}]
  (js/console.log "rerendering")
  [f/form {:prevent-default? true
           :clean-on-unmount? true
           :path :skill-form 
           :on-submit (fn [{:keys [state path values touched errors dirty]}]
                        (new-skill values)
                        )
           :props {:close! close!}
           
           }
   skill-form
   ]
  )
