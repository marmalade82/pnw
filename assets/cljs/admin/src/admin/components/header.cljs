(ns admin.components.header
  (:require
   [reagent.core :as r]
   [reagent.dom :as d]
   [component-lib.core :as c]
   [clojure.string :refer [join]]
   [admin.routes :as routes]
   ))

(defn get-menu-items []
  [{:label "home", :href (routes/root-path) :selected? routes/is-root-path}
   {:label "skills", :href (routes/skills-path) :selected? routes/is-skills-path} 
   {:label "projects", :href (routes/project-timeline-path) :selected? routes/is-project-timeline-path}
   {:label "posts", :href (routes/timeline-path) :selected? routes/is-timeline-path}
   {:label "dashboard" :href (routes/admin-dashboard-path) :selected? routes/is-admin-dashboard-path}
   {:label "workbench" :href (routes/admin-dashboard-path) :selected? #(or (routes/is-project-edit-path) (routes/is-project-add-path) (routes/is-edit-path) (routes/is-add-post-path))}
   ]
  )


(defn my-header [{:keys [class] :or {class ""}}]
  (into [c/header {:class (join " " ["My-Header" class])}
         [c/logo {:class "My-Logo"}
          [c/text {:class "My-LogoText"} "admin"]
          ]
          [c/menu {:items (get-menu-items) :class "My-Header-Menu"}]
         ]
        (r/children (r/current-component))
        )
  )

 (defn header [{:keys [class]}]
   (cond
     (routes/is-root-path)
      (into [c/header {:class "LoginPage-Header"}
              [c/logo {:class "LoginPage-Logo", :href (routes/root-path)}
              [c/text {:class "LoginPage-LogoText"} "admin"]
              [c/text {:class "LoginPage-LogoTagline"} "one step at a time."]
              ]
              ]
              (r/children (r/current-component)))
     :else [my-header {:class class}]
     )
      
   )
