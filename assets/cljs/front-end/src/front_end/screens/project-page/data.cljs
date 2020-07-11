(ns front-end.screens.project-page.data
  (:require
   [front-end.routes :refer [project-path]])
  )

(def ^:const lexer-topic "lexer")

(def lexer
  {:title "lexer-gen"
   :subtitle "Parsing in PureScript"
   :description "An lexer generator written in PureScript (uses LL1 parsing internally)."
   :href (project-path {:topic lexer-topic})
   :thoughts
      ["This is a lexer generator I wrote as part of my continued learning in Computer Science. I wrote it in PureScript as a challenge to myself, as some other projects that I had tried writing in Haskell/PureScript ended up failing before I had become completely comfortable with writing reasonably clean code within the paradigm and type system."

       "This was challenging to write for two reasons."

       "First, it was my first attempt at implementing the LL1 parsing algorithm. A lot of time was taken up with constructing the First and Follow sets for the generator's specification grammar, and then, while unit testing the LL1 parsing algorithm, I had to question whether the tests were failing because of the implementation or because of a mistake in the First/Follow set membership."

       "Second, it was my first time trying to incrementally build an AST outside of recursive-descent parsing. Recursive-descent makes it easy: the call stack helps determine the structure of the AST. But in LL1 parsing, the algorithm performs a left-first, depth-first construction of the AST using a finite state machine, so construction of the AST has to be done separately."

       "After several false-starts (including obscure Haskell topics like Lenses and Prisms), I eventually settled on an algorithm where each transition in the LL1 parsing state machine would issue a command to a Builder module that would place the parsed info on the stack. Once parsing had succeeded, the stack would be merged from top to bottom to yield the final AST. I had thought this was elegant. It was all pure functions, after all."

       "Unfortunately, continued testing showed how wrong I was. There were multiple corner cases in the merging process that I hadn't thought of. Deciphering, debugging, and fixing all these issues was time-consuming and frustrating, especially compared to the simplicity of the task: Just Build a Tree!"

       "It was one of those of cases where commitment is one's undoing. All I had to do was leave the pure functions behind, and build a tree using References (Refs). Despite it being all we ever did in C++ back in university, I didn't think to do it."
       "I suppose it illustrates that working with immutable data does not always yield better code."
              ]
   :github_href "http://github.com"
   :live_href nil
   }
  )

(def ^:const spice-topic "spice")

(def spice
  {:title "spice"
   :subtitle "Task Management?"
   :description "A task management application written in TypeScript/React Native for Android."
   :href (project-path {:topic spice-topic})
   :thoughts []
   :github_href "http://github.com"
   :live_href nil
   }
  )

(def ^:const devhub-topic "dev-hub")

(def devhub
  {:title "dev-hub"
   :subtitle "Team building and Git"
   :description "A portfolio web application written with React and Ruby on Rails as part of a Chingu Voyage 19. A fun experience collaborating with fellow learners in a collaborative environment."
   :href (project-path {:topic devhub-topic})
   :thoughts []
   :github_href "http://github.com"
   :live_href "http://www.google.com"
   }
  )

(def ^:const calendar-topic "calendar")

(def calendar
  {:title "e-calendar"
   :subtitle "Getting comfy with JS"
   :description "A simple appointment e-calendar application written in JavaScript/React."
   :href (project-path {:topic calendar-topic})
   :thoughts [
       "This is a React/JS/CSS calendar web application. It allows the user to perform the CRUD cycle on infinite web calendar (using Moment.js). There is currently no backend for the data."
       "Prior to writing this, I had largely avoided writing projects in pure JavaScript, preferring TypeScript for the compile-time type checks. Writing this in only JavaScript was largely a challenge to myself."
       "While I did miss compile-time type-checking, I found that a project on this small scale was manageable without it, since it's unlikely that types will need to change much while adding features. But I still wouldn't choose to write larger projects in JavaScript. I have too many past experiences at work where the lack of type checking in large codebases has lead to obscure errors."
   ]
   :github_href "http://github.com"
   :live_href "http://www.google.com"
   })

(defn get-project [topic]
  (case topic
    lexer-topic lexer
    spice-topic spice
    devhub-topic devhub
    calendar-topic calendar
    nil
    )
  )

(defn get-all-projects []
  [lexer spice devhub calendar
   ]
  )
