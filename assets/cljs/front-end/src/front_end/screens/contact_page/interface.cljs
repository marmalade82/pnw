(ns front-end.screens.contact-page.interface)


(defprotocol IComposableForm
  "A protocol that describes the behavior of form logic and state"
  (my-input [this field new-val]
    "Method for passing new value to a particular named field")
  (my-error [this field]
    "Method for accessing latest error message/object for a field")
  (my-value [this field]
    "Method for accessing current value of a field")
  (all-data [this field]
    "Method for getting all the data of the form as a map"
    )
  (revalidate [this field]
    "Method that ensures a particular field is valid"
    )
  )
