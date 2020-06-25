(ns front-end.screens.admin-login
  (:require-macros [cljs.core.async.macros :refer [go]])
  (:require
    [reagent.core :as r]
    [reagent.dom :as d]
    [front-end.components.styled.button :refer [button]]
    [front-end.components.styled.input :refer [input]]
    [front-end.components.styled.label :refer [label]]
    [cljs-http.client :as http]
    [cljs.core.async :refer [<!]]
    ))

(def username (r/atom "hello"))
(def password (r/atom "pass"))

(defn try-login [username password]
  (go (let [request (http/post "http://localhost:4000/auth"
                               {:json-params
                                {:username username
                                 :password password}})
            response (<! request)]
        (js/console.log response) ))
  
  )

(defn admin-login []
  [:div {:class ["screen"]
         }
    [label "Username"]
    [input "text" @username #(reset! username %)]
    [label "Password"]
   [input "password" @password #(reset! password %)]
   [button "submit" "Submit" #(try-login @username @password) ]])


;; -------------------------
;; Initialize app

(defn mount-root []
  (d/render [admin-login] (.getElementById js/document "app")))

(defn ^:export init! []
  (mount-root))
