(ns front-end.screens.contactpage.contact-form
  #_(:require
   [reagent.core :as r]
   [reagent.dom :as d]
   [front-end.screens.contactpage.forms :as f ]
   )
  (:require-macros
   [front-end.screens.contactpage.form-macros :refer [validate scope] ])
  )

; Test that macros compile correctly
(validate [fourth second] '(1))


(scope test id-test
    (validate [fourth second] '(1) )
       )

