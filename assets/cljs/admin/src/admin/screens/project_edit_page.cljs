(ns admin.screens.project-edit-page
  (:require
   [reagent.core :as r]
   [reagent.dom :as d]
   [component-lib.core :as c]
   )
  )

(defn project-edit-page []
  [:div {:class "ProjectEditPage"}
   [c/text {:type 2, :class "ProjectEditPage-Header"}
     "Edit Project"
    ]
   [:form {:class "ProjectEditPage-Form"
           }
    [c/input-group {:type "text"
                    :label "Title"
                    :id "title"
                    }]
    [c/input-group {:type "text"
                    :label "Subtitle"
                    :id "subtitle"
                    }]
    [c/input-group {:label "Display Image"
                    :id "display-image"
                    :type "file"
                    :types [:jpg, :png, :jpeg]
                    }
     ]
    [c/input-group {:label "Thumbnail"
                    :type "file"
                    :id "thumbnail-image"
                    :types [:jpg, :png, :jpeg]
                    }]
    [c/input-group {:label "Description"
                    :type "text"
                    :id "description"
                    }
     ]
    [c/input-group {:label "Extended Thoughts (\"MARKDOWN\")"
                    :type "textarea"
                    :id "thoughts"
                    }]
    
    ]
   ]
  )

