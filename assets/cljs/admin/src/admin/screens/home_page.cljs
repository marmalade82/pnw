(ns admin.screens.home-page
  (:require
   [reagent.core :as r]
   [reagent.dom :as d]
   [component-lib.core :as c]
   [fork.reagent :as f]
   )
  )


(defn login-form [{:keys [values
                          handle-change
                          handle-submit
                          submitting?
                          form-id]}]
   [:form { :id form-id
           :class "Login-Form"
           :on-submit handle-submit
           }
    [c/input-group {:label "Username/Email"
                    :type "text"
                    :id "username-email"
                    :value (values "username-email")
                    :on-change handle-change
                    :class "Login-Input"
                    }]
    [c/input-group {:label "Password"
                    :type "password"
                    :id "password"
                    :value (values "password")
                    :on-change handle-change
                    :class "Login-Input"
                    }]
    [c/submit-button {
                      :disabled submitting?
                      :class "Login-Submit"
                      } "login"]
    ]
  )

(defn render-login-form []
  [f/form { :prevent-default? true
           :clean-on-unmount? true
           :path :login-form 
           :on-submit #(identity %)
           }
   login-form
   ]
  )

(defn home-page []
  [c/page 
   [c/header {:class "LoginPage-Header"}
    [c/logo {:class "LoginPage-Logo", :href "http://www.google.com"}
     [c/text {:class "LoginPage-LogoText"} "admin"]
     [c/text {:class "LoginPage-LogoTagline"} "one step at a time."]
     ]
    ]
   [render-login-form]
   ]
  )

