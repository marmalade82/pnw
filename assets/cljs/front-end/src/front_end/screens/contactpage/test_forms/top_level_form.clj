(ns front-end.screens.contactpage.test-forms.top-level-form
  (:require
     [front-end.screens.contactpage.form-macros :refer [form validate fields scope]]
             ))


(form TopLevelForm atom
      (fields [first second third])
      (validate [first second] errors)
      )

