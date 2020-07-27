(ns admin.global-state.skills
  (:require
   [reagent.core :as r]
   [reagent.dom :as d]
   [admin.external.requests :as req]
   [cljs.core.async :refer [go]]
   [cljs.core.async.interop :refer [<p!]]
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

(def global-skills (r/atom nil))

(def timeline-skills
  (req/pipe global-skills
          #(into [] (sort-by (fn [skill] (:name skill)) %))
          )
  )

(defn fetch-skills []
  (go
    (let [req (js/fetch "http://localhost:4000/admin/api/skills"
                        #js {"method" "GET"})
          resp (<p! req)
          json (js->clj (<p! (.json resp)) :keywordize-keys true)
          ]
      (reset! global-skills (into [] json))
      )
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
