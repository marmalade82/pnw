(ns front-end.screens.contact-page.contact-form
  (:require
   [reagent.core :as r]
   [reagent.dom :as d]
   )
  )

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


(defmacro validate "Transforms a validation spec into a data structure"
  [fields body]
  )


; This macro should generate a class and implementation of 
; the form protocol
(defmacro def-form "Defines a form and the hooks into it"
  [name & forms]
  `(do
     (defrecord ~name [values errors])
     (defn ~(symbol (str "mk-" name))
       (let [values (r/atom {})
             errors (r/atom {})
             ]
         (~(symbol (str name ".")) values errors)
         )
       )

     #_(extend-type ~name
       IComposableForm
       (my-input [this field new-val]
         (let [values (:values this)]
           (if (contains? @values field) (swap! values #(assoc % field new-val)))
           ))
       (my-error [this field]
         (let [errors (:errors this)]
           (if (contains? @errors field) (field @errors))
           )
         )
       (my-value [this field]
         (let [values (:values this)]
           (field @values))
         )
       (all-data [this field]
         (let [values (:values this)]
           values)
         )
       )
    )
  )

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
  (validate [this field]
    "Method that ensures a particular field is valid"
    )
  )

(defrecord ContactForm [values errors])


(extend-type ContactForm
  IComposableForm
  (my-input [this field new-val]
    (let [values (:values this)]
      (if (contains? @values field) (swap! values #(assoc % field new-val)))
    ))
  (my-error [this field]
    (let [errors (:errors this)]
      (if (contains? @errors field) (field @errors))
      )
    )
  (my-value [this field]
    (let [values (:values this)]
      (field @values))
    )
  (all-data [this field]
    (let [values (:values this)]
      values)
    )
  )

(defn mk-contact-form [{:keys [name email message] :or {name "", email "", message ""}}]
  (let [values {:name name, :email email, :message message}
        errors {}
        ]
    (ContactForm. (r/atom values) (r/atom errors)))
  )

(defn input [name val]
  '()
  )

(def error
  (r/atom "error")
  )

(defn error-message [name]
  error
  )

(def value
  (r/atom "value")
  )

(defn get-value [name]
  value
  )
