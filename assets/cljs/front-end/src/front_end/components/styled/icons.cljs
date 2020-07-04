(ns front-end.components.styled.icons
  (:require
     [reagent.core :as r]
     [reagent.dom :as d]
     [hiccup-icons.octicons :as octicons]
   ))

(defn github [{:keys [class] :or {class ""}}]
  [:span {:class ["Icon-Github" class]}
    octicons/mark-github
   ]
  )

(defn website [{:keys [class] :or {class ""}}]
  [:span {:class ["Icon-Website" class]}
    octicons/device-desktop
   ]
  )
