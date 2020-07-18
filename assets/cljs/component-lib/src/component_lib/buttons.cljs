(ns component-lib.buttons
  (:require-macros
   [component-lib.buttons :refer [defbutton]]
   )
  (:require
   [component-lib.core :as c]
   [component-lib.icons :as i]
   ))

(defbutton edit
    [c/text "edit"]
    [i/edit]
  )


(defbutton preview
   [c/text "preview"]
   [i/preview]
  )

(defbutton delete
   [c/text "delete"]
   [i/delete]
  )

(defbutton add
   [i/plus]
  )

(defbutton search
    [i/search]
  )

(defbutton close
   [i/x]
  )

