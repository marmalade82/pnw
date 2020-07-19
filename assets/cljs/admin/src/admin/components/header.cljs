(ns admin.components.header
  (:require
   [reagent.core :as r]
   [reagent.dom :as d]
   [component-lib.core :as c]
   [clojure.string :refer [join]]
   [admin.routes :as routes]
   ))

(defn get-menu-items []
  [{:label "home", :href (routes/root-path)}
   {:label "skills", :href (routes/skills-path)} 
   {:label "projects", :href (routes/project-timeline-path)}
   {:label "posts", :href (routes/timeline-path)}
   {:label "dashboard" :href (routes/admin-dashboard-path)}
   ]
  )


(defn header [{:keys [class] :or {class ""}}]
  (into [c/header {:class (join " " ["My-Header" class])}
         [c/logo {:class "My-Logo"}
          [c/text {:class "My-LogoText"} "admin"]
          ]
          [c/menu {:items (get-menu-items) :class "My-Header-Menu"}]
         ]
        (r/children (r/current-component))
        )
  )
