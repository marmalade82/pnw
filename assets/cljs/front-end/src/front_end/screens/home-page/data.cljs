(ns front-end.screens.home-page.data)


(defn work-experience []
  [
   {:title "ServiceNow Technical Lead"
    :company "Cognizant"
    :description "Responsible for communicating technical requirements to development teams while also providing operational support in the form of hotfixes, development, and debugging."
    :achievements []
    }
   {:title "ServiceNow Developer"
    :company "Revature"
    :description "Responsible for business analysis, form development, workflow implementation, database configuration, and testing"
    :achievements []
    }
   ]
  )

(defn clean-code []
  [
   {:text "Literate/Declarative programming"
    :href "http://www.google.com"
   }
   {:text "Organizing code as service layers"
    :href "http://www.google.com"
    }
   {:text "Using ADTs judiciously"
    :href "http://www.google.com"
    }
   ]
  )

(defn projects []
  [
   {:title "lexer-gen"
    :description "An lexer generator written in PureScript that uses LL1 parsing internally"
    :href "http://www.google.com"
    }
   {:title "spice"
    :description "A task management application written in TypeScript/React Native for Android"
    :href "http://www.google.com"
    }
   {:title "dev-hub"
    :description "A portfolio web application written with React and Ruby on Rails as part of a Chingu Voyage 19. A fun experience collaborating with fellow learners in a collaborative environment"
    :href "http://www.google.com"
    }
   {:title "e-calendar"
    :description "A simple appointment e-calendar application written in pure JavaScript. The first application where was forced to ignore my distaste for weakly typed languages"
    :href "http://www.google.com"
    }
   ]
  )
