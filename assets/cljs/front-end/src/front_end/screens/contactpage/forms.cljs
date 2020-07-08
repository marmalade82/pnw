(ns front-end.screens.contactpage.forms
  (:require-macros
      [front-end.screens.contactpage.form-macros ]
  ))




(comment "What we want is the ability to specify form logic in the way of trees, while also allowing custom behavior, the ability to validate, etc."
         "We want to avoid the issue that Formik has, where Formik is responsible for BOTH rendering and form logic, which really need to be separate services if we want this to be flexible."
         "Form logic should also be composable at run-time AND compile-time")

(def BaseExample
  #_(fn [values errors] ; each is a map to values
      ; This is the formik approach. There is a top-level validation function
    ; The function doesn't known what fields it is validating, 
    ; or what the dependencies are
    errors
    )
  )

(def BaseExampleWithHooks
  #_(validate [name age]
            (fn [values errors]
              errors
              ; Here we specify the fields being validated. When
              ; validation is rerun on name or age, this function
              ; will be run
                         ))
  #_(validate (fn [values errors]
                errors
                ; Here we can leave out the dependencies, and the macro 
                ; will still do the work
                ))
  )

(def HooksWithDeclarations
  #_(validate [name age] :against [height]
              (fn [errors]
                errors
                ; Here, name and age are available for validation,
                ; as is height, the dependency. This allows 
                ; the logic to say that when [height] changes, 
                ; the validation should be rerun as well
                ))
  )


; Perhaps if there's no ., then it is relative to the current scope.
; But if there is a chain, then it is relative to root.
(def HooksWithNested
  #_(validate [name.first name.last] :against [physical.height])
  )

; Notice that if we do it this way, we don't need to define fields,
; since the validation declarations do this implicitly
; But if we allow fields to be omitted in the validate functions, then 
; we don't know what fields are in the form.
(def HooksWithScope
  #_(scope name
           #_(validate [first last] :against [physical.height :as h]
                       (fn [errors]
                         errors )
                       )
         ))
#_(let ph (scope physical)
       (input :name ph)
       )
#_(input :name, :scope :physical)

(def HooksWithScopeWithId
  #_(scope name :id id-name
           #_(validate [first last] :against [height]
                       (fn [errors] errors)
                       )
           )
  )
