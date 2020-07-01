(ns front-end.screens.work-page.data
  (:require
   [front-end.routes :refer [work-experience-path]]
   [cljs.core.match :refer-macros [match]]

   ))

(def ^:const cognizant-topic
  "cognizant"
  )

(def ^:const revature-topic
  "revature")

(defn cognizant-exp []
  {:title "ServiceNow Technical Lead"
   :company "Cognizant"
   :description "Responsible for communicating technical requirements to development teams while also providing operational support in the form of hotfixes, development, and debugging."
   :achievements
    ["First"
     "Second"
     "Third" 
      ]
   :start "Aug 2019"
   :end "Dec 2019"
   :href (work-experience-path {:topic cognizant-topic}) 
   }
  )

(defn revature-exp []
  {:title "ServiceNow Developer"
   :company "Revature"
   :description "Responsible for business analysis, form development, workflow implementation, database configuration, and testing"
   :achievements
     ["End"]
   :start "Jun 2018"
   :end "Jul 2019"
   :href (work-experience-path {:topic revature-topic})
   }
  )

(defn get-work-experience [topic]
  (case topic
         cognizant-topic (cognizant-exp)
         revature-topic  (revature-exp)
         nil
    )
  )
