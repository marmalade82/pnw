(ns admin.external.requests
  (:require
   [reagent.core :as r]
   [environ.core :refer [env]]
   )
  )

(defn form-data [vals]
  (let [form (js/FormData.)]
    (do
      (doseq [[k v] vals]
        (.append form (clj->js k) (clj->js v))
        )
      form
      )
    )
  )

(defn pipe [atom transform]
  (let [new-atom (r/atom @atom)]
    (add-watch atom (random-uuid)
               (fn [_key _ref _old new]
                 (reset! new-atom (transform new))
                 )
               )
      new-atom
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
  (js/setTimeout (fn []
                   (reset! request-status :finished)
                   (change request-status :ready 300)
                   )
         2000)
  )


(defn request! []
  (reset! request-status :submitting)
  (timeout-then-succeed)
  )

(def server (env :server))


(defn do-get []
  (if (nil? server)
    (timeout-then-succeed))
  )

(defn do-post []
  (if (nil? server)
    (timeout-then-succeed))
  )

(defn do-delete []
  (if (nil? server)
    (timeout-then-succeed))
  )

(defn do-patch []
  (if (nil? server)
    (timeout-then-succeed))
  )
