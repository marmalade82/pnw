(ns front-end.screens.contact-page
  (:require
   [reagent.core :as r]
   [reagent.dom :as d]
   [front-end.components.styled.layout :refer [layout]]
   [front-end.components.styled.article :refer [article]]
   [front-end.components.styled.text :refer [my-text]]
   [front-end.screens.contact-page.contact-form :refer [input error-message get-value]]
   ))

(defn contact-page []
  [layout {:label "Contact"}
   [article {:class "ContactPage-Container"}
      [my-text {:class "ContactPage-Title", :type "3", :text "Contact Me"}]
    (let [name "name"
          email "email"
          message "message"
          ]
      [:form
       [:div {:class "ContactPage-NameGroup"}
        [:label {:for name} "Name"]
        [:input {:type "text", :id name, :name name
                 :onclick (partial input name)
                 :value @(get-value name)
                 }]
        ]
        @(error-message name)
       [:div {:class "ContactPage-EmailGroup"}
        [:label {:for email} "Email"]
        [:input {:type "text", :id email, :name email
                 :onclick (partial input email)
                 :value @(get-value email)
                 }]
        ]
       [:div {:class "ContactPage-MessageGroup"}
        [:label {:for message} "Message"]
        [:input {:type "text", :id message, :name message
             :onclick (partial input message)
             :value @(get-value message)
                 }]
        ]
       [:input {:type "submit"}]
       ])
    ]
   ]
  )

