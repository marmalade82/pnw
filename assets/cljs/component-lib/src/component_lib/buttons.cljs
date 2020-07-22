(ns component-lib.buttons
  (:require-macros
   [component-lib.buttons :refer [defbutton]]
   )
  (:require
   [component-lib.core :as c]
   [component-lib.icons :as i]
   ))


(defn sep []
  [:span {:class "Separator"}]
  )

(defbutton go-back
  [i/go-back]
  )

(defbutton edit
    [c/text "edit"]
    [sep]
    [i/edit]
  )


(defbutton preview
   [c/text "preview"]
   [sep]
   [i/preview]
  )

(defbutton delete
   [c/text "delete"]
   [sep]
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

(defbutton cancel
  [c/text "cancel"]
  )
