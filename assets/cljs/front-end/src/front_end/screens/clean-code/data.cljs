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
    ["One of the challenges with working with large code bases is simply understanding them. Fundamentally, code bases are text, so they come with all the accompanying difficulties of reading comprehension, ranging from pulp fiction to 'classics' to philosophical tomes."

     "Literate/Declarative programming plays a large role in making code self-documenting and easy to read."

      "Of course, one can go too far in making code *too* literate:"

      "Luckily, I've yet to come across any examples where declarative programming can be taken too far"
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
     "To address the reader concerns, each service layer should have as few responsibilities as possible, to make the layer's logic consistent and easy to understand. Take the Views in MVC for example. The simplest way to place responsibilities for Views is to give them two responsibilities: 1) Displaying data, and 2) Receiving and forwarding user-inputs. Expressly describing Views as a service layer with two responsibilities makes it clear that other kinds of logic (form logic, data fetching, etc.) belong elsewhere, in other service layers. Adhering to this organizing makes view code as simple to understand as possible, and in fact, lines up nicely with projects like modeling UI's as state machines."
     "To address the writer concerns, each service layer should be careful to have distinct types for its logic. A service layer should never depend on the types of other service layers. Consider the example of Views again. In a framework like React, the View code might have a `beforeMount` hook that fetches data via an explicit AJAX call, e.g. fetching an array of objects with an `id` key. If the View code makes an assumption that the fetched data has an `id`, it might map the `id` key of the data to a `title` key in a child component"
     "Unfortunately, this dependency means that if the source of the data changes its schema, each View with this dependency is now broken. The change in the data source layer has rippled out to affect the correctness of a service layer whose logic has *nothing* to do with data provisioning."
     "To remove this dependency, the AJAX call should be moved into a function in its own service layer. This layer essentially acts as an Adapter between the data source and the View code, mapping the export data schemas of the data source to the display data schemas of the Views. Changes like these ensure that modifications to the data source layer can only ripple outward to affect the correctness of the Adapter layer, whose responsibilities are directly concerned with schema transformations."

     "Structuring the service layers ensures that the logic of one layer cannot become entangled in the alien logic of another layer. If you hide the types, you hide the logic."
      ]

   :href (clean-code-path {:topic service-topic})
   }
  )

(def ^:const adts-topic "adts")

(def adts
  {:text "Using ADTs judiciously"
   :paragraphs []
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
