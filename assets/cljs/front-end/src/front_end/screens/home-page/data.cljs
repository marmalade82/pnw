(ns front-end.screens.home-page.data
  (:require
     [front-end.routes :refer [work-experience-path clean-code-path project-path]]
   ))


(defn work-experience []
  [
   {:title "ServiceNow Technical Lead"
    :company "Cognizant"
    :description "Responsible for communicating technical requirements to development teams while also providing operational support in the form of hotfixes, development, and debugging."
    :achievements []
    :start "Aug 2019"
    :end "Dec 2019"
    :href (work-experience-path {:topic "cognizant"}) 
    }
   {:title "ServiceNow Developer"
    :company "Revature"
    :description "Responsible for business analysis, form development, workflow implementation, database configuration, and testing"
    :achievements []
    :start "Jun 2018"
    :end "Jul 2019"
    :href (work-experience-path {:topic "revature"})
    }
   ]
  )

(defn clean-code []
  [
   {:text "Literate/Declarative programming"
    :href (clean-code-path {:topic "declarative"})
   }
   {:text "Organizing code as service layers"
    :href (clean-code-path {:topic "layers"})
    }
   {:text "Using ADTs judiciously"
    :href (clean-code-path {:topic "adts"})
    }
   ]
  )

(defn projects []
  [
   {:title "lexer-gen"
    :description "An lexer generator written in PureScript that uses LL1 parsing internally"
    :href (project-path {:topic "lexer"})
    }
   {:title "spice"
    :description "A task management application written in TypeScript/React Native for Android"
    :href (project-path {:topic "spice"})
    }
   {:title "dev-hub"
    :description "A portfolio web application written with React and Ruby on Rails as part of a Chingu Voyage 19. A fun experience collaborating with fellow learners in a collaborative environment"
    :href (project-path {:topic "dev-hub"})
    }
   {:title "e-calendar"
    :description "A simple appointment e-calendar application written in pure JavaScript. The first application where was forced to ignore my distaste for weakly typed languages"
    :href (project-path {:topic "calendar"})
    }
   ]
  )
