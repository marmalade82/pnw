(ns admin.forms.edit-form
  (:require
   [component-lib.core :as c]
   [fork.reagent :as f]
   [clojure.string :as str]
   ))


(defn blog-form [{:keys [values
                         set-values
                         handle-change
                         handle-submit
                         submitting?
                         form-id]}]
    [:form { :id form-id
            :class "Blog-Form"
            :on-submit handle-submit
            }
     [c/input-group {:label "Title",
                     :type "text"
                     :id "title"
                     :on-change handle-change
                     :value (values "title")
                     }]
     [c/input-group {:label "Subtitle",
                     :type "text"
                     :id "subtitle"
                     :on-change handle-change
                     :value (values "subtitle")
                     }]
     [c/input-group {:label "Date",
                     :type "date"
                     :id "edit-date"
                     :on-change handle-change
                     :value (values "edit-date")
                     }]
     [c/markdown-editor {:id "blog-markdown-editor"
                       :on-change #(set-values %)
                       }]

     [c/submit-button {
                      :disabled submitting?
                      :class "Blog-Submit"
                       } "submit"]
     ]
  )

(defn render-blog-form []
  [f/form {:prevent-default? true
           :clean-on-unmount? true
           :initial-values
           { "edit-date" (-> (js/Date.) (.toISOString) (str/split #"T") (first))
            
            }
           :path :login-form 
           :on-submit #(identity %)
           } blog-form
   ]
  )


(defn render-new-form []
  [f/form {:prevent-default? true
           :clean-on-unmount? true
           :initial-values
           { "edit-date" (-> (js/Date.) (.toISOString) (str/split #"T") (first))
            
            }
           :path :login-form 
           :on-submit #(identity %)
           } blog-form
   ]
  )
