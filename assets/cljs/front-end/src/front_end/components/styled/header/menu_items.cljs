(ns front-end.components.styled.header.menu-items
  (:require
   [front-end.routes :refer [root-path blog-path contact-path] ]
   [front-end.screens.project-page.data :refer [get-all-projects]]
   ))

(defn project->menu-item [project]
  { :label (:title project)
    :href (:href project)
   }
  )

(defn menu-items []
  [{:label "Home", :href (root-path)}
   {:label "Blog", :href (blog-path)}
   { :label "Projects"
    :children
    (into [] (map project->menu-item)
          (get-all-projects)
           )
    }
   {:label "Contact", :href (contact-path)}
   ]
  )
