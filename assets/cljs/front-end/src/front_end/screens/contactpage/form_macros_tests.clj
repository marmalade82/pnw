(ns front-end.screens.contactpage.form-macros-tests
  (:require
   [front-end.screens.contactpage.form-macros :refer [validate fields scope]]
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
