(ns front-end.screens.contactpage.form-macros-tests
  (:require
   [front-end.screens.contactpage.form-macros :refer
    [validate fields scope]]
   [front-end.screens.contactpage.test-forms.top-level-form :as tlf]
   [front-end.screens.contactpage.interface :refer
    [IComposableForm my-input my-value all-data my-error]]
   )
  )

(use 'clojure.test)

(deftest test-validate
  (is (= :validate (:tag (validate [first second] (errors)))))
  (is (= 2 (count (:fields (validate [first second] errors)))))
  (is (= {:error 1}
         (let [valid-fn (:validate (validate [first second] errors))]
              (valid-fn {:error 1} {:error 2})
              )
         )))

(deftest test-fields
  (is (= :fields (:tag (fields [first second]))))
  (is (= 3 (count (:fields (fields [first second third])))))
  )


(deftest test-scope-single
  "Tests single declarations in the scope"
  (let [s (scope apples id-apple
             (fields [first second third])
             (validate [first second] errors)
             (scope banana id-banana
                    )
             (scope orange id-orange
                    )
                 )]
     (is (= :scope (:tag s)))
     (is (= :apples (:name s)))
     (is (= :id-apple) (:id s))
     (is (= 3 (count (:fields s))))
     (is (= 2 (count (:triggers s))))
     (is (= #{:first :second} (set (keys (:triggers s)))))
     (is (= 2 (count (:scopes s))))
     (is (= #{:banana :orange} (set (keys (:scopes s)))))
       )
  )

(deftest test-scope-happy-multi
  "Tests multiple declarations in the scope"
  (let [s (scope apples id-apple
                 (fields [first second])
                 (fields [third])
                 (validate [first second] errors)
                 (validate [third] errors)
                 (scope banana id-banana
                        )
                 (scope orange id-orange
                        )
                 )]
    (is (= :scope (:tag s)))
    (is (= :apples (:name s)))
    (is (= :id-apple) (:id s))
    (is (= 3 (count (:fields s))))
    (is (= 3 (count (:triggers s))))
    (is (= #{:first :second :third} (set (keys (:triggers s)))))
    (is (= 2 (count (:scopes s))))
    (is (= #{:banana :orange} (set (keys (:scopes s)))))
    )
  )


(deftest test-toplevelform
  (let [form (tlf/mk-TopLevelForm {:initial {:first 1}})]
    (is (= 0  (count @(:errors form))))
    (is (= 3 (count @(:values form))))
    (is (= 1 (my-value form :first 2)))
    (is (= 2 (my-value form :second 2)))
    (is (= {:first 1, :second nil, :third nil} (all-data form)))
    (is (= "Oops" (my-error form :first)))
    )
  )
