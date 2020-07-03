(ns front-end.screens.clean-code.data
  (:require
   [front-end.routes :refer [clean-code-path]]
   [cljs.core.match :refer-macros [match]]
   ))

; Must declare this constant at compile-time
(def ^:const declarative-topic "declarative")

(def declarative 
  {:text "Literate/Declarative programming"
   :paragraphs
   ["One of the challenges with working with large code bases is simply understanding them. Code bases are text, so they come with all the accompanying difficulties of reading comprehension. Literate/Declarative programming plays a large role in making code self-documenting and easy to read."

    "'Literate' means that, where possible, the code reads like well-written English. Variable and function names are words, not mysterious acronyms. Related statements are grouped together, and conceptually sequential operations are executed in that sequence."

    "'Declarative' means that the code describes *what* is done, but not *how* it is done, at the appropriate level of abstraction. This often means refactoring a set of related statements into a separate function or procedure with a declarative name. The trick, of course, is knowing the appropriate level of abstraction."

    "But literate/declarative programming also extends to code organization. When directories and subdirectories are given appropriate names and nesting, viewing the directory structure makes it easy to understand how the code base is organized, which is another instance of self-documentation."
      ]
   :href (clean-code-path {:topic declarative-topic})
   }
  )

(def ^:const service-topic "layers")

(def service
  {:text "Organizing code as service layers"
   :paragraphs
    ["One of the challenges with working with large code bases is keeping a given module's logic understandable. This applies in two ways. First, in looking at the code, the reader should find it easy to see that the logic is correct. Second, in modifying the code, the writer should find it easy to know how far the modification might ripple."

     "The concept of organizing code as service layers helps here. The goal is to organize the code base as a series of communicating service layers that each perform transformations on data. This is one way of explaining the meaning of \"Separate your X logic from your Y logic\"."
     "To address the reader concerns, each service layer should have as few responsibilities as possible, to make the layer's logic consistent and easy to understand. Take the Views in MVC for example. The simplest way to place responsibilities for Views is to give them two responsibilities: 1) displaying data, and 2) receiving and forwarding user-inputs. Expressly describing Views as a service layer with two responsibilities makes it clear that other kinds of logic (form logic, data fetching, etc.) belong elsewhere, in other service layers. Adhering to this organizing makes view code as simple to understand as possible, and in fact, lines up nicely with projects like modeling UI's as state machines."
     "To address the writer concerns, each service layer should be careful to have distinct types for its logic. A service layer should never depend on the types of other service layers. Consider the example of Views again. In a framework like React, the View code might have an `afterMount` hook that fetches data via an explicit AJAX call, e.g. fetching an array of objects with an `id` key. If the View code makes an assumption that the fetched data has an `id`, it might map the `id` key of the data to a `title` key in a child component"
     "Unfortunately, this dependency means that if the source of the data changes its schema, each View with this dependency is now broken. The change in the data source layer has rippled out to affect the correctness of a service layer whose logic has *nothing* to do with data provisioning."
     "To remove this dependency, the AJAX call should be moved into a function in its own service layer. This layer essentially acts as an Adapter between the data source and the View code, mapping the export data schemas of the data source to the internal data schemas of the Views. Changes like these ensure that modifications to the data source layer can only ripple outward to affect the correctness of the Adapter layer, whose responsibilities are directly concerned with schema transformations."

     "Structuring the service layers ensures that the logic of one layer cannot become entangled in the alien logic of another layer. If you hide the types, you hide the logic."
      ]

   :href (clean-code-path {:topic service-topic})
   }
  )

(def ^:const adts-topic "adts")

(def adts
  {:text "Using OOP carefully"
   :paragraphs
   [ "A lot has been written in recent years about how OOP is bad, and functional programming is the way forward for better code bases. But Java is still around, and so are OOP's defenders, so the debate will go on amongst the higher authorities. But elsewhere, I discuss how organizing code into simple, partitioned service layers can help with writing maintainable code. Where does OOP fit into that discussion?"

    "OOP has two incompatibilities with service layers that transform data."

    "First, instance variables are global to every instance method. If we think of each instance method as providing a slightly different service, it becomes hard to understand each service. What data does an instance method transform? What service does an instance method perform? It becomes hard to answer these questions when each service has permission to affect any subset of the instance variables. By contrast, it is easy to answer these questions when using pure functions."

    "Second, note the obvious: OOP encourages returning objects. From a service layer perspective, this means that both data and logic are passed from layer to layer. This violates the imperative to partition service layer logic, so that changes to one layer's logic cannot ripple out to require changes in unrelated service layers."

    "From the above two points, it should be clear that, conceptually, at least, I prefer functional programming to OOP. If we consider service layer architecture as a means of avoiding spaghetti code, then clearly OOP has features that make avoiding it difficult. That's not to say, of course, that developers can't write beautiful code with OOP. The `leet` developers can famously write great code in any language or paradigm. But the goal of software engineering research is ultimately to make it easier, not harder, for *all* developers to make good contributions to code bases."

    "However, OOP *can* make it easy to write ADTs. And as far as I've seen, that's a lot easier to do and understand in a typed OOP language like Java than in a typed FP language like Haskell."
      ]
   :href (clean-code-path {:topic adts-topic})
   }
  )

(defn get-clean-code [topic]
      (case topic
            declarative-topic declarative
            service-topic service
            adts-topic adts
            nil
            )
    )
