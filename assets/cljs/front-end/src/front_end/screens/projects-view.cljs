(ns front-end.screens.projects-view
  (:require [reagent.core :as r]
            [reagent.dom :as d]
            [front-end.components.styled.gallery :refer [gallery]]))


(defn projects []
  (let [projects (r/atom
                  [ {:title "Lexical Analysis", :body "A lexical analysis generator written in purescript", :link "https:www.google.com"}
                    {:title "Personal Website", :body "The source code for the this site. Written with an Elixir/Phoenix backend and ClojureScript front-end. After all, why not?" :link "https://www.github.com"}
                   ])]

       projects))

(defn projects-view []
  (let [projects (projects)]
    
    (fn []
      [:div {:class ["screen"]
             }
       [gallery @projects]]))
  )


;; -------------------------
;; Initialize app

(defn mount-root []
  (d/render [projects-view] (.getElementById js/document "app")))

(defn ^:export init! []
  (mount-root))
