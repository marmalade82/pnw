(ns front-end.screens.home-page.data
  (:require
     [front-end.routes :refer [work-experience-path clean-code-path project-path]]
     [front-end.screens.work-page.data :refer [cognizant-exp revature-exp]]
     [front-end.screens.clean-code.data :refer [declarative service adts]]
     [front-end.screens.project-page.data :as project]
   ))


(defn work-experience []
  [
   (cognizant-exp)
   (revature-exp)
   ]
  )

(defn clean-code []
  [
   declarative
   service
   adts
   ]
  )

(defn projects []
  [
   project/lexer
   project/spice
   project/devhub
   project/calendar
   ]
  )
