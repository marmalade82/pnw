(ns front-end.components.styled.form
  (:require [reagent.core :as r]
            [reagent.dom :as d]
            [front-end.components.styled.input :refer [input]]
            [front-end.components.styled.label :refer [label]]))

#_(comment
  Helper function for creating forms. Takes in a format specification (see example below) and returns a function component that expects certain inputs for storing the form state and validation state at the top level

  input is [{ :type "text"
            :name "password"
            :label "Password"
            :validate ["required", "min-password", "max-password"] 
            :validateOn :blur
           },
          {:type "choice"
           :name "country"
           :label "Country of origin"
           :validate ["required" "not-china"]
           :validateOn :input
           }
          ]

  This should generate a form with a password field and a country field.

  The form should take in a Validations ratom that can be called with the "required", "min-password", "max-password", and "not-china" keys to obtain the validations. Each validation should take a map with the keys
    { :state ; clean, dirty -- in case validation depends on whether the user has changed the initial input
      :value ; the value of the input
    }
  Each validation should return one of the following results
    [:ok, "message"],
    [:warning, "message"],
    [:error, "message"].
  It is up to each input to decide how to respond based on the results.

  The form should take in a State ratom that can be called with the "password" and "country" keys to read and write from the current value of the ratom while the input changes.

  The form should take in a Valid ratom that can be called with the "password" and "country" keys to read and write whether the current input is valid, so that the input can decide whether to display any 

  Finally, the form should take in a Show ratom that can be called with the "password" and "country" keys to control whether the input is currently informing the user about its validity. There are three modes
    "never", in which case the input will never show its validity
    calculation.
    "always", in which case the input always shows its validity
    "hide-once", in which case the input will hide its validity, but on
      the next change to its input,
      it will revert the mode back to "always".
    "show-once", the input will show its validity, but on the next
      change to its input, it will revert the mode back to "always"
)

(defn mk-local-state [!state !validations !valid !mode]
  {:!state !state
   :!validations !validations
   :!valid !valid
   :!mode !mode
   })

(defn run-validations [val spec local-state]
  (let [!validations (:!validations local-state)
        !mode (:!mode local-state)
        !valid (:!valid local-state)
        name (:name spec)
        validator (:validate spec)]
      ; We run `val` through each `validator` and return the result of the first one that fails, or the very last one. We feed the result into the `!valid` map.

    ; TODO. Finish this and use it in mk-input.
    ; TODO. Allow inputs to show the validation-results.
    ; TODO. Allow form to track which inputs are dirty.
    
    ))

(defn mk-input [spec local-state]
  (let [!state (:!state local-state)
        name (:name spec)
        val (get @!state name)
        update #(swap! !state assoc name %)
        on-change #(-> % update (run-validations spec local-state))
        ]
    (case (:type spec)
      "text" (input "text" val update)
      "password" (input "password" val update)
      "multi-text" (input "multi-text" val update))))


; This function returns hiccup to massage each spec into an input
(defn convert-spec [spec local-state]
  [:<>
   [label (:label spec)]
   [mk-input spec local-state]]
  )
(defn form [specs]
  (fn [!state !validations !valid !mode]
    (let [local-state (mk-local-state !state !validations !valid !mode)
          children (map #(convert-spec % local-state) specs)
          form (into [] (concat [:div {:class ["form"]}] children))]
      form )))
