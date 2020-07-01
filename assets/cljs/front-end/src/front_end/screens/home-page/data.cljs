(ns front-end.screens.home-page.data
  (:require
     [front-end.routes :refer [work-experience-path clean-code-path project-path]]
     [front-end.screens.work-page.data :refer [cognizant-exp revature-exp]]
     [front-end.screens.clean-code.data :refer [declarative service adts]]
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
