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
   :company_href "http://www.cognizant.com"
   :description "Responsible for communicating technical requirements to development teams while also providing operational support in the form of hotfixes, development, and debugging."
   :achievements
    ["Refactored, developed, and maintained an internal prehire hardware procurement application."
     "Fixed multiple gaps and bugs in a project portfolio management application created by another team."
     "Provided debugging and hotfixes for the team's biweekly deployments."
     "Provided updates and tweaks to the AngularJS customer portals."
      ]
   :start "Aug 2019"
   :end "Dec 2019"
   :main_languages ["JavaScript", "HTML", "CSS", "AngularJS"]
   :href (work-experience-path {:topic cognizant-topic}) 
   }
  )

(defn revature-exp []
  {:title "ServiceNow Developer"
   :company "Revature"
   :company_href "http://www.revature.com"
   :description "Responsible for business analysis, form development, workflow implementation, database configuration, and testing."
   :achievements
   ["Worked with clients to translate requirements to technical specs."
    "Performed functional testing and unit testing for deployment."
    "Configured tables in the single table inheritance database."
    ]
   :start "Jun 2018"
   :end "Jul 2019"
   :main_languages ["JavaScript", "HTML", "CSS", "AngularJS"]
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
