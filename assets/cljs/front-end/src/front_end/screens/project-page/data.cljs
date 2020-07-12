(ns front-end.screens.project-page.data
  (:require
   [front-end.routes :refer [project-path]])
  )

(def ^:const lexer-topic "lexer")

(def lexer
  {:title "lexer-gen"
   :subtitle "Parsing for fun"
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
   :skills ["PureScript"]
   }
  )

(def ^:const spice-topic "spice")

(def spice
  {:title "spice"
   :subtitle "Almost task management"
   :description "A task management application written in TypeScript/React Native for Android."
   :href (project-path {:topic spice-topic})
   :thoughts
   [ "This is a mobile app for task management. During the development process, I encountered problems in three areas."

    "The first was in feature selection: I didn't establish what the MVP feature set was. I knew that I wanted to write a task-management app with some sort of twist, but the many of the features I originally chose to implement were either too complex to implement in React Native (physics-based animations) or out-of-place in a task management app (an arcade within the app). I wasted time trying to implement these things before eventually cutting them out."

    "The second was in selecting implementation complexity. In the process of implementing recurring tasks, I had to decide what to do if the phone was off for weeks at a time. When the application was started again, what would happen to all the recurring tasks that never had a chance to recur, since the phone was off? I decided on a solution that had a lot of corner cases. I wanted complete correctness: No matter how long or how frequently the phone was off, I wanted to app to repair all missing recurring tasks."

    "But this was difficult to do correctly, and to test for correctness. I wrote tests that mocked the passing of days and weeks, and still I found bugs. Debugging was also painful, as the tooling for JavaScript debugging made it hard to follow the asynchronous execution of promises. But from a user perspective, the feature I was trying to implement didn't make sense: if a user had the phone off for weeks, would they want to come back to 200 tasks? Of course not. A far better solution was to only ensure the generation of recurring tasks for the past two days at most. This had far fewer corner cases, and was ultimately what I went with."

    "This is something I still struggle with: You can know that you can implement a feature that is both correct and efficient, but how do you know whether implementing it is worth it? How do you know when a feature is better-off left for consumer (whether User or Code) to deal with?"

    "The third area was in separation of responsibilities. I won't mince words: I wrote spaghetti code. Sure, I had separate TypeScript classes for database access and application logic, but one of the code smells for spaghetti code is that code changes ripple across the codebase"

    "My codebase had this problem. My View code knew about the types and implementations of the core Database and Application logic. When it came time to make changes to either the interfaces or types of the Database or the Application logic, I had to make adjustments *everywhere*. Yep. Top-of-the-line, type-checked spaghetti code. It was this experience that taught me that, despite all the warnings about 'loose coupling' and 'encapsulation', I had yet to understand how to implement both properly. Thus, it was this experience that led me to think about organizing codebases as share-nothing service layers that transform data."
    ]
   :github_href "http://github.com"
   :live_href nil
   :skills ["React Native", "TypeScript", "Mobile", "RxJS"]
   }
  )

(def ^:const devhub-topic "dev-hub")

(def devhub
  {:title "dev-hub"
   :subtitle "Collaboration"
   :description "A portfolio web application written with React and Ruby on Rails as part of a Chingu Voyage 19. A fun experience collaborating with fellow learners in a collaborative environment."
   :href (project-path {:topic devhub-topic})
   :thoughts
   [ "In this project, I worked with developers I met through Chingu on a six-week set of sprints, where we'd build an MVP for an online app-sharing platform for devs."
    "It was challenging, but not overly so. I worked with some technologies for the first time, like Ruby (on Rails), ActiveRecord, and GatsbyJS, but that was very manageable. If you understand the principles, learning a new technology is just a matter of remembering the details, and I'd worked with servers, object-oriented databases, and Rails-like frameworks before."
    "It was a good experience, although in hindsight, I wish we'd had a team lead who could set the team strategies. Although we had sprint-planning sessions and code reviews to keep things moving forward, we never really established quality processes for testing and maintaining development momentum. Some team members that were struggling with the code base didn't speak up, because they felt that they were at fault for not having enough time to work through and understand the code base. That's never the fault of the developer; it's the code readability and documentation, which needs to be good enough for *everyone* on the team."
    ]
   :github_href "http://github.com"
   :live_href "http://www.google.com"
   :skills ["Ruby on Rails", "React", "GatsbyJS"]
   }
  )

(def ^:const calendar-topic "calendar")

(def calendar
  {:title "e-calendar"
   :subtitle "Getting comfy with JS"
   :description "A simple appointment e-calendar application written in JavaScript/React."
   :href (project-path {:topic calendar-topic})
   :thoughts [
       "This is a React/JS/CSS frontend appointment application. It allows the user to manage their appointments in a web calendar (using Moment.js). There is currently no backend for the data."
       "Prior to writing this, I had largely avoided writing projects in pure JavaScript, preferring TypeScript for the compile-time type checks. Writing this in only JavaScript was largely a challenge to myself."
       "While I did miss compile-time type-checking, I found that a project on this small scale was manageable without it, since it's unlikely that types will need to change much while adding features. But I still wouldn't choose to write larger projects in JavaScript. I have too many past experiences at work where the lack of type checking in large codebases has lead to obscure errors."
   ]
   :github_href "http://github.com"
   :live_href "http://www.google.com"
   :skills ["JavaScript", "React", "RxJS"]
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
