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
        max-length-single 100
        max-length-multi 1000
        ]
    [:form { :on-submit handle-submit
             :id form-id
             :class "ContactPage-Form"
            }
     
     [:div {:class "ContactPage-InputGroup"}
      [:label {:for name} "Name"]
      [:input {:type "text", :id name, :name name
               :on-change handle-change
               :value (values name)
               :max-length max-length-single
               }]
      ]
     [:div {:class "ContactPage-InputGroup"}
      [:label {:for email} "Email"]
      [:input {:type "text", :id email, :name email
               :on-change handle-change
               :value (values email)
               :max-length max-length-single
               }]
      ]
     [:div {:class "ContactPage-InputGroup"}
      [:label {:for message} "Message"]
      [:textarea {:type "text", :id message, :name message
               :on-change handle-change
               :value (values message)
               :max-length max-length-multi
               }]
      ]
     [:button {:type "submit"
               :disabled submitting?
               :class "ContactPage-SubmitButton"
               }
       "Send"
      ]
     ])
  )


(defn render-form [stage]
  [f/form { :prevent-default? true
           :clean-on-unmount? true
           :path :contact-form
           :on-submit (fn [{:keys [state path values dirty reset]}]
                        (js/console.log reset)
                        (js/console.log values)
                        (swap! state #(assoc-in % [path :submitting?] true))
                        (go
                          (let [r-chan (request/send-contact values)
                                resp (<! r-chan)
                                ]
                            (swap! state #(assoc-in % [path :submitting?] false))
                            (reset! stage :thanks)
                            )
                          )
                        )
           }
   contact-form
   ]
  )

(defn render-thanks [stage]
  [my-text {:class "ContactPage-ThanksMessage" :text "Your message has been sent :)"}]
  )

(defn contact-page []
  (let [stage (r/atom :form)]
    (fn [] 
      [layout {:label "Contact"}
       [article {:class "ContactPage-Container"}
        [my-text {:class "ContactPage-Title", :type "3", :text "Contact Me"}]
        (cond
          (= @stage :form) [render-form stage]
          (= @stage :sent) [render-thanks stage]
          :else [render-thanks stage]
          )
        ]
       ])
    )
  )

