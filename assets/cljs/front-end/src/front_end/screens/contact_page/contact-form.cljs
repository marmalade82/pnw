(ns front-end.screens.contact-page.contact-form
  (:require
   [reagent.core :as r]
   [reagent.dom :as d]
   [frontend.screens.contact-page.interface :refer [IComposableForm]]
   )
  )




(defrecord ContactForm [values errors])


(extend-type ContactForm
  IComposableForm
  (my-input [this field new-val]
    (let [values (:values this)]
      (if (contains? @values field) (swap! values #(assoc % field new-val)))
    ))
  (my-error [this field]
    (let [errors (:errors this)]
      (if (contains? @errors field) (field @errors))
      )
    )
  (my-value [this field]
    (let [values (:values this)]
      (field @values))
    )
  (all-data [this field]
    (let [values (:values this)]
      values)
    )
  )

(defn mk-contact-form [{:keys [name email message] :or {name "", email "", message ""}}]
  (let [values {:name name, :email email, :message message}
        errors {}
        ]
    (ContactForm. (r/atom values) (r/atom errors)))
  )

(defn input [name val]
  '()
  )

(def error
  (r/atom "error")
  )

(defn error-message [name]
  error
  )

(def value
  (r/atom "value")
  )

(defn get-value [name]
  value
  )
