(ns front-end.screens.testing
  (:require  [reagent.core :as r]
             [front-end.components.styled.form :refer [form]]
             [reagent.dom :as d]))


(def state (r/atom {"user" "hi",
                    "password" "bye"})) 

(def specs
  [ {:label "Username"
     :name "user"
     :type "text"
     },
    {:label "Password",
     :name "password",
     :type "password",
     },
   {:label "My History",
    :name "history",
    :type "multi-text"
    
    }
   ])

(def f (form specs))

(defn testing []
  [:div [f state]])
