(ns admin.forms.project-edit-form
  (:require
   [fork.reagent :as f]
   [component-lib.core :as c]
   [admin.direction :refer [go-back!]]
   [admin.external.side-effect :refer [side-effect]]
   [admin.external.requests :refer [request-status request!] ]
   )
  )

(defn project-form [{:keys [values
                            set-values
                            handle-change
                            handle-submit
                            submitting?
                            form-id]}]
  (let [form-status request-status]
    (fn []
      [:form {:class "ProjectEditPage-Form"
              :id form-id
              :on-submit handle-submit
              }
       [side-effect {:is-true (= @form-status :finished)
                     :on-true #(go-back!)
                     }]
       [c/input-group {:type "text"
                       :label "Title"
                       :id "title"
                       :on-change handle-change
                       :value (values "title")
                       }]
       [c/input-group {:type "text"
                       :label "Subtitle"
                       :id "subtitle"
                       :on-change handle-change
                       :value (values "subtitle")
                       }]
       [c/input-group {:label "Display Image"
                       :id "display-image"
                       :type "file"
                       :types [:jpg, :png, :jpeg]
                       :on-change handle-change
                       :value (values "display-image")
                       }
        ]
       [c/input-group {:label "Thumbnail"
                       :type "file"
                       :id "thumbnail-image"
                       :types [:jpg, :png, :jpeg]
                       :on-change handle-change
                       :value (values "thumbnail-image")
                       }]
       [c/input-group {:label "Description"
                       :type "text"
                       :id "description"
                       :on-change handle-change
                       :value (values "description")
                       }
        ]
       [:div {:class "ProjectEditPage-Skills"}
        [c/label {:class "ProjectEditPage-Label", :for "skills"} "Skills"]
        [:input {:type "text" :class "TypeAheadSearch" :id "skills"}]
        [:div {:class "TypeaAheadSearch-Display"} "Display selected skills here"]
        ]
       [c/label {:class "ProjectEditPage-Label", :for "thoughts"} "Extended Thoughts"]
       [c/markdown-editor { :on-change identity
                           :id "thoughts" 
                           }]
       [c/submit-button {
                         :disabled (not= :ready @form-status)
                         :class "ProjectEditPage-Form-Submit"
                         } "submit"]
       ]))
  )

(defn render-project-form []
  [f/form {:prevent-default? true
           :clean-on-unmount? true
           :path :login-form 
           :on-submit #(request!)
           } project-form
   ]
  )

(defn render-new-form []
  [f/form {:prevent-default? true
           :clean-on-unmount? true
           :path :login-form 
           :on-submit #(request!)
           } project-form
   ]
  )
