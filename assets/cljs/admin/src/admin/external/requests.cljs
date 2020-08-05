(ns admin.external.requests
  (:require
   [reagent.core :as r]
   [cljs.core.async :refer [go]]
   [cljs.core.async.interop :refer [<p!]]
   [admin.external.response-broker :refer [send]]
   [admin.external.env :refer [config]]
   )
  )


; In development mode, these timeout and then succeed.
; In test mode, these make requests to the specified server.

(def request-status (r/atom :ready)) ; ready, submitting finished

(defn change [atom next time]
  (js/setTimeout #(reset! atom next) 200)
  )

(defn submitted []
  (reset! request-status :submitted)
  )

(defn success []
  (do 
    (reset! request-status :finished)
    (change request-status :ready 300))
  )


(defn timeout-then-succeed []
  (js/Promise. (fn [resolve reject]
      (js/setTimeout (fn []
                       (js/console.log "timing out, then succeeding")
                      (reset! request-status :finished)
                      (change request-status :ready 300)
                       (resolve nil)
                      )
                    2000)
                 ))
  )


(defn request! []
  (reset! request-status :submitting)
  (timeout-then-succeed)
  )

(def server (:server config))


(defn fetch-nothing [url opts ]
  (js/Promise. (fn [resolve reject ]
                   (go
                     (try 
                       (let [req (js/fetch url opts)
                             res (<p! req)
                             ]
                         (resolve nil)
                         )
                       (catch :default e
                         (send :error e)
                         (resolve nil)
                         )
                       )
                     )
                 ))
  )

(defn fetch-json [url opts ]
  (js/Promise. (fn [resolve reject]
                   (go
                     (try 
                       (let [req (js/fetch url opts)
                             res (<p! req)
                             json (js->clj (<p! (.json res)) :keywordize-keys true)
                             ]
                         (resolve json)
                         )
                       (catch :default e
                         (send :error e)
                         (resolve nil)
                         )
                       )
                     )
                 ))
  )

(defn dev-server []
  (= server "")
  )

(defn full-url [path]
  (str server path)
  )

(defn do-get [path ]
  (if (dev-server)
    (timeout-then-succeed)
    (fetch-json (full-url path) #js { "method" "GET" } )
    )
  )

(defn do-post [path form-body ]
  (if (dev-server)
    (timeout-then-succeed)
    (fetch-json (full-url path) #js { "method" "POST", "body" form-body} )
    )
  )

(defn do-delete [path ]
  (if (dev-server)
    (timeout-then-succeed)
    (fetch-nothing (full-url path) #js { "method" "DELETE" } )
    )
  )

(defn do-patch [path form-body ]
  (if (dev-server)
    (do
      (timeout-then-succeed))
    (fetch-json (full-url path) #js { "method" "PATCH", "body" form-body} )
    )
  )
