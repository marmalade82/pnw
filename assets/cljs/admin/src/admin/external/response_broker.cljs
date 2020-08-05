(ns admin.external.response-broker
  (:require
   [reagent.core :as r]
   [reagent.dom :as d]
   [cljs.core.async :refer [chan put! take!]]
   )
  )

#_(this module takes response data and routes them to the correct global state)


(def broker-chan (chan))

; Allows external processes to send data on a topic
(defn send [topic data]
     (put! broker-chan [topic data])
  )

