(ns admin.global-state.projects
  (:require
   [reagent.core :as r]
   [reagent.dom :as d]
   [admin.external.utils :refer [pipe]]
   [admin.routes :refer [project-edit-path]]
   [cljs.core.async :refer [go]]
   [cljs.core.async.interop :refer [<p!]]
   [clojure.set :refer [rename-keys]]
   )
  )

(defn get-grouped-projects []
  {:ongoing
   [{ :last_updated "Oct 1, 2020"
      :start "Oct 1, 2019"
      :title "lexer-gen"
      :subtitle "Purescript lexing"
      :skills [{:color "red", :label "CSS"},
               {:color "purple", :label "PS"}
              ]
     :edit-href (project-edit-path)
     }
    { :last_updated "Oct 3, 2020"
     :title "pnw"
     :start "Oct 3, 2018"
     :subtitle "Personal website"
     :skills [{:color "red", :label "CLJS"}
              {:color "green", :label "ELX"}
              ]
     :edit-href (project-edit-path)
     }   

    ]
   :completed
   [{ :last_updated "Oct 3, 2020"
      :title "pnw"
      :start "Oct 3, 2018"
      :subtitle "Personal website"
     :skills [{:color "red", :label "CLJS"}
              {:color "green", :label "ELX"}
              ]
      :edit-href (project-edit-path)
     }
    
    ]
   :abandoned
   [{:last_updated "Oct 14, 2020"
     :title "spice"
     :start "January 2020"
     :subtitle "Task management and learning"
     :skills [{:color "purple", :label "RN"}
              {:color "pink" :label "JV"}
              ]
     :edit-href (project-edit-path)
     
     }
    
    ]
   }
  )

(def global-projects (r/atom nil))

(defn map-for-timeline [projects]
  (let [f (fn [{:keys [updated_at created_at] :as project}]
            (let [renamed (rename-keys project {:created_at :start,
                                                :updated_at :last_updated})]
              (assoc renamed :edit-href (project-edit-path)
                             :skills []
                             :last_updated (.format (js/moment updated_at)
                                                    "MMMM Do, YYYY")
                     )
              )
            )
        ]
    (map f projects)
    )
  )

(defn group-by-status [projects]
  (let [f (fn [acc {:keys [status] :or {status "ongoing"} :as project}]
            (let [key (case status
                        "ongoing" :ongoing
                        "completed" :completed
                        "abandoned" :abandoned
                        :ongoing
                        )
                  ]
              (update acc key (fn [old]
                                 (into [] (conj old project))
                                )
                )
              )
            )
        ]
     (reduce f {} projects)
    )
  )

(defn for-timeline [projects]
  (let [mapped (map-for-timeline projects)
        grouped (group-by-status mapped)
        ]
     grouped
    )
  )

(def timeline-projects (pipe global-projects for-timeline))


(defn fetch-projects []
   (go
     (let [req 
             (js/fetch "http://localhost:4000/admin/api/projects"
                     #js { "method" "GET"
                          
                          })
           resp (<p! req)
           json (<p! (.json resp))
                     ]
          (js/console.log json)
          (reset! global-projects (js->clj json :keywordize-keys :true))
       )
    )
  )

(defn get-projects []
  (do 
    (if (nil? @timeline-projects)
        (fetch-projects)
      )
    timeline-projects)
  )
