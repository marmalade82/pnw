(ns front-end.screens.admin-login
  (:require
    [reagent.core :as r]
    [reagent.dom :as d]
    [front-end.components.styled.button :refer [button]]
    [front-end.components.styled.input :refer [input]]
    [front-end.components.styled.label :refer [label]]))

(def text (r/atom "hello"))
(def password (r/atom "pass"))

(defn admin-login []
  [:div {:class ["screen"]
         }
    [label "Username"]
    [input "text" @text #(reset! text %)]
    [label "Password"]
   [input "password" @password #(reset! password %)]
   [button "submit" "Submit" #(js/console.log "hi") ]])

