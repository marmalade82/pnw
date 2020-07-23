(ns component-lib.logic.requests
  (:require
   [reagent.core :as r]
   [cljs.core.async :refer [go chan put!]]
   [cljs.core.async.interop :refer-macros [<p!]]
   [component-lib.logic.subscribe :refer [mk-subscribable]]
   ))


(defonce fetch-errors (chan))

(defonce fetch-success (chan))

(defonce subscribable-errors (mk-subscribable fetch-errors))

(defonce subscribable-success (mk-subscribable fetch-success))

(defn subscribe-errors []
  (let [subscribe (:subscribe subscribable-errors)]
    (subscribe)
    )
  )

(defn fetch-json [url opts]
  (try 
      (go
        (let [promise (js/fetch url opts)
              res (.json (<p! promise))
              ]
          (put! fetch-success (js->clj res, :keywordize-keys true))
          )
        )
    (catch :default e
      (put! fetch-errors e)
      )
    )
 )

(defn do-post [url, body]
  (let [form (js/FormData.)
        ]
    (do
      (for [[k v] body]
        (.append form k v)
        )
      (fetch-json url #js { :method "POST"
                            :body form
                                    })
      )
   )
  )

(defn do-get [url]
  (fetch-json url #js { :method "GET"
                     })
  )

(defn do-delete [url]
  (fetch-json url #js { :method "DELETE"}
            )
  )

(defn do-patch [url]
  (fetch-json url #js { :method "PATCH" })
  )
