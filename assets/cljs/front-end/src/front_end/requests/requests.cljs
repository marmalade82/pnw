(ns front-end.requests.requests
  (:require
   [cljs.core.async :as core :refer [>! go]]
   [front-end.requests.target :refer [target]]
   )
  )


(defn send-server-request
  "Base function for sending a request to the server. Returns a chan"
  [req]
  (let [res-chan (core/chan)]
    (go
      (cond
        (nil? target) (js/setTimeout (fn []
                                       (go (>! res-chan {:tag :ignore}))
                                       ) 1000)
        :else (>! res-chan {:tag :ignore})
        ))
      res-chan
    )
  )

(defn send-contact [vals]
  (let [json (-> vals (clj->js) (js/JSON.stringify))
        request nil
        resp-chan (send-server-request request)
        ]
    resp-chan
    )
  )

