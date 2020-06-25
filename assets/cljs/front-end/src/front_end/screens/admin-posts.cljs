(ns front-end.screens.admin-posts
  (:require [reagent.core :as r]
            [reagent.dom :as d]
            [front-end.components.styled.link :refer [link]]
            [front-end.components.styled.text :refer [text]]))

(comment
  This screen is for listing the posts of an admin)

; Temp function that mocks an atom (promise) that is waiting for the posts to be passed to it.
; In actual implementation we would comfortable rendering an empty screen while we wait for the posts
; to load 
(defn get-posts! []
  (let [posts (r/atom [{:title "post 1", :body "some stuff about"}, {:title "post 2", :body "more stuff about"}])]
    posts ))

(defn render-post [post]
  [:li {:class ["header-li"]
        }
   [:div [text "3" (:title post)]]
   [:div [text "body" (:body post)]]
   [:div [link "http://www.google.com" [text "body" "Read more"]]]])

(defn admin-posts []
  ; We do an initial fetch of the posts and then list them here
  (let [posts (get-posts!)]
    (fn []
      [:div { :class ["screen"]
             
             }
       [:ul {:class ["header-list"]
             
             }
        (for [item @posts]
          ^{:key item} [render-post item])]])))


;; -------------------------
;; Initialize app

(defn mount-root []
  (d/render [admin-posts] (.getElementById js/document "app")))

(defn ^:export init! []
  (mount-root))
