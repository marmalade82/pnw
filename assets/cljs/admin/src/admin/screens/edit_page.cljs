(ns admin.screens.edit-page
  (:require
   [reagent.core :as r]
   [reagent.dom :as d]
   [component-lib.core :as c]
   [fork.reagent :as f]
   [mde]
   )
  )


(defn markdown-editor [{:keys [on-change id]
                        :or {on-change #(js/console.log %)
                             id "md-editor"
                             }}]
  (r/create-class
   {:display-name "markdown-editor"
    :component-did-mount
    (fn [this]
      (let [editor (js/SimpleMDE. (clj->js { :element (.getElementById js/document id)}))
            ]
        (doto editor
          (-> (.-codemirror) (.on "change" #(on-change (.value editor))))
          ))
      )
    :component-will-unmount (fn [] 1)
    :reagent-render
      (fn []

        [:textarea {:id id}]
        )
    })
  )

(defn blog-form [{:keys [values
                         set-values
                         handle-change
                         handle-submit
                         submitting?
                         form-id]}]
    [:form { :id form-id
            :class "Login-Form"
            :on-submit handle-submit
            }
     [markdown-editor {:id "blog-markdown-editor"
                       :on-change #(set-values %)
                       }]

     [c/submit-button {
                      :disabled submitting?
                      :class "Login-Submit"
                       } "Submit"]
     ]
  )

(defn render-blog-form []
  [f/form {:prevent-default? true
           :clean-on-unmount? true
           :path :login-form 
           :on-submit #(identity %)
           } blog-form
   ]
  )


(defn edit-page []
  [:div {:class "EditPage"}
   [:h2 "Edit Post"]
   [render-blog-form]
   ]
  )
