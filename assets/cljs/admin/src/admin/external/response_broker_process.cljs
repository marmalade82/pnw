(ns admin.external.response-broker-process
  (:require
   [admin.global-state.skills :refer [global-skills]]
   [admin.global-state.posts :refer [global-posts]]
   [admin.global-state.projects :refer [global-projects]]
   [cljs.core.async :refer [take!]]
   [admin.external.response-broker :refer [broker-chan]]
   )
  )

(defn replace-id [seq search-id replacement]
  (js/console.log "Replacing with")
  (js/console.log replacement)
  (let [f (fn [{:keys [id] :as item}]
            (if (= id search-id)
              replacement
              item
              )
            )
        ]
    (map f seq)
    )
  )

; Loop that takes something off the channel and processes it
(defn- process []
  (js/console.log "processing")
  (take! broker-chan
         (fn [[topic data]]
           (js/console.log (str "topic: " topic))
           (js/console.log data)
           (case topic
             :new-skill (swap! global-skills #(conj % data))
             :edit-skill (swap! global-skills
                                (fn [skills]
                                  (into [] (replace-id skills (:id data) data))
                                  ))
             :new-post (swap! global-posts #(conj % data))
             :new-project (swap! global-projects #(conj % data))
             :error (identity 1)
             (do
               (js/console.log "fail PROCESSING")
               (identity 1) ; this should lead to a custom error toast
               )
             )
           (trampoline process)
           )
         )
  )

; immediately start the process of message processing
(defn start-events []
  (process)
  )
