(ns front-end.screens.contactpage.interface)


(defprotocol IComposableForm
  "A protocol that describes the behavior of form logic and state"
  (my-input [this field new-val]
    "Method for passing new value to a particular named field")
  (my-error [this field]
    "Method for accessing latest error message/object for a field")
  (my-value [this field default]
    "Method for accessing current value of a field")
  (all-data [this]
    "Method for getting all the data of the form as a map"
    )
  (revalidate [this field]
    "Method that ensures a particular field is valid"
    )
  (refresh [this]
    "Method that reruns validation on all fields in the form"
    )
  )
