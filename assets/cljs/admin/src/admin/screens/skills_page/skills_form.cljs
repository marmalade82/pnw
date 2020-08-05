(ns admin.screens.skills-page.skills-form
  (:require
   [reagent.core :as r]
   [reagent.dom :as d]
   [component-lib.core :as c]
   [component-lib.buttons :as b]
   [fork.reagent :as f]
   [component-lib.logic.requests :as req]
   [admin.routes :refer [params]]
   [admin.external.side-effect :refer [side-effect]]
   [admin.external.utils :refer [form-data]]
   [admin.external.requests :as request :refer
      [submitted success request-status request! ] ]
   [admin.external.response-broker :as broker]
   [cljs.core.async :refer [go]]
   [cljs.core.async.interop :refer [<p!]]
   [clojure.walk :as w]
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

(defn edit-skill [vals id]
  ; this function should submit the values to the server.
  ; when the results return, the returned results should
  ; be passed to the results handler for processing
  (go
    (submitted)
    (broker/send :edit-skill 
                 (<p! 
                  (request/do-patch (str "/admin/api/skills/" id)
                                    (form-data vals)
                                    )))
    (success)
    )
  )


(defn new-skill [vals]
  (go
    (submitted)
    (let [req (js/fetch "http://localhost:4000/admin/api/skills"
                        #js { "method" "POST",
                             "body" (form-data vals)
                             })
          res (<p! req)
          json (js->clj (<p! (.json res)) :keywordize-keys true)
          ]
      (broker/send :new-skill json)
      (success)
      )
    )
  #_(request!)
  )

(defn render-edit-form [{:keys [close! id initial] :or {close! #(identity 1)}}]
    [f/form {:prevent-default? true
             :clean-on-unmount? true
             :path :edit-form 
             :on-submit (fn [{:keys [state path values touched errors dirty]}]
                          (edit-skill values id)
                          )
             :initial-values (w/stringify-keys initial)
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
           :initial-values
           { "name" "a"
             "abbr" "b"
             "color" "#000000"
            }
           :on-submit (fn [{:keys [state path values touched errors dirty]}]
                        (new-skill values)
                        )
           :props {:close! close!}
           
           }
   skill-form
   ]
  )
