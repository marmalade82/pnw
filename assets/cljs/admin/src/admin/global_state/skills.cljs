(ns admin.global-state.skills
  (:require
   [reagent.core :as r]
   [reagent.dom :as d]
   [admin.external.utils :refer [pipe]]
   [cljs.core.async :refer [go]]
   [cljs.core.async.interop :refer [<p!]]
   [admin.external.requests :as req]
   )
  )

(defn test-skills []
  [{:name "JavaScript"
    :abbr "JS"
    :color "pink"
    }
   {:name "TypeScript"
    :abbr "TS"
    :color "blue"
    }
   ]
  )

(def global-skills (r/atom nil) )

(def timeline-skills
  (pipe global-skills
          #(into [] (sort-by (fn [skill] (:name skill)) %))
          )
  )

(defn fetch-skills []
  (go
    (reset! global-skills (into [] 
                                (<p! 
                                 (req/do-get "/admin/api/skills"))))
    )
  )


(defn get-skills []
  (do
    (if (nil? @timeline-skills)
      (fetch-skills)
      )
     timeline-skills
    )
 )
