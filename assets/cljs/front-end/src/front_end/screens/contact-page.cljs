(ns front-end.screens.contact-page
  (:require
   [reagent.core :as r]
   [reagent.dom :as d]
   [front-end.components.styled.layout :refer [layout]]
   [front-end.components.styled.article :refer [article]]
   [front-end.components.styled.text :refer [my-text]]
   [front-end.screens.contactpage.interface :refer [IComposableForm my-input my-error my-value all-data revalidate]]
   ))

(defn contact-page []
  [layout {:label "Contact"}
   [:div]
   #_[article {:class "ContactPage-Container"}
      [my-text {:class "ContactPage-Title", :type "3", :text "Contact Me"}]
    (let [name "name"
          email "email"
          message "message"
          ]
      [:form
       [:div {:class "ContactPage-NameGroup"}
        [:label {:for name} "Name"]
        [:input {:type "text", :id name, :name name
                 :onclick (partial my-input name)
                 :value @(my-value name)
                 }]
        ]
        @(my-error name)
       [:div {:class "ContactPage-EmailGroup"}
        [:label {:for email} "Email"]
        [:input {:type "text", :id email, :name email
                 :onclick (partial my-input email)
                 :value @(my-value email)
                 }]
        ]
       [:div {:class "ContactPage-MessageGroup"}
        [:label {:for message} "Message"]
        [:input {:type "text", :id message, :name message
             :onclick (partial my-input message)
             :value @(my-value message)
                 }]
        ]
       [:input {:type "submit"}]
       ])
    ]
   ]
  )

