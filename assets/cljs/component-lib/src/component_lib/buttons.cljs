(ns component-lib.buttons
  (:require-macros
   [component-lib.buttons :refer [defbutton]]
   )
  (:require
   [component-lib.core :as c]
   [component-lib.icons :as i]
   ))

(defbutton edit
    [c/text "Edit"]
    [i/edit]
  )


(defbutton preview
   [c/text "Preview"]
   [i/preview]
  )

(defbutton delete
   [c/text "Delete"]
   [i/edit]
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
