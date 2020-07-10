(ns front-end.screens.contactpage.test-forms.top-level-form
  (:require
     [front-end.screens.contactpage.form-macros :refer [form validate fields scope]]
     [front-end.screens.contactpage.interface :refer [IComposableForm]]
             ))


(form TopLevelForm atom
      (fields [first second third])
      (validate [first second] (if (< first second)
                                   (assoc errors first "Oops")
                                   errors
                                   )
                )
      )

