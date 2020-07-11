(ns front-end.screens.contact-page
  (:require
   [reagent.core :as r]
   [reagent.dom :as d]
   [front-end.components.styled.layout :refer [layout]]
   [front-end.components.styled.article :refer [article]]
   [front-end.components.styled.text :refer [my-text]]
   [fork.reagent :as f]
   [front-end.requests.requests :as request]
   [cljs.core.async :as core :refer [<! go]]
   ))

(defn contact-form
  [{:keys [values
           handle-change
           handle-submit
           submitting?
           form-id
           ]}]
  (let [name "name"
        email "email"
        message "message"
        ]
    [:form { :on-submit handle-submit
             :id form-id
            }
     
     [:div {:class "ContactPage-NameGroup"}
      [:label {:for name} "Name"]
      [:input {:type "text", :id name, :name name
               :on-change handle-change
               :value (values name)
               }]
      ]
     [:div {:class "ContactPage-EmailGroup"}
      [:label {:for email} "Email"]
      [:input {:type "text", :id email, :name email
               :on-change handle-change
               :value (values email)
               }]
      ]
     [:div {:class "ContactPage-MessageGroup"}
      [:label {:for message} "Message"]
      [:input {:type "text", :id message, :name message
               :on-change handle-change
               :value (values message)
               }]
      ]
     [:button {:type "submit"
               :disabled submitting?
               }
       "Submit!"
      ]
     ])
  )


(defn contact-page []
  [layout {:label "Contact"}
   [:div]
   [article {:class "ContactPage-Container"}
      [my-text {:class "ContactPage-Title", :type "3", :text "Contact Me"}]
    [f/form { :prevent-default? true
              :clean-on-unmount? true
              :path :contact-form
              :on-submit (fn [{:keys [state path values dirty reset]}]
                           (swap! state #(assoc-in % [path :submitting?] true))
                           (go
                             (let [r-chan (request/send-contact values)
                                   resp (<! r-chan)
                                   ]
                                (swap! state #(assoc-in % [path :submitting?] false))
                               )
                             )
                          )
             }
      contact-form
     ]
    ]
   ]
  )

