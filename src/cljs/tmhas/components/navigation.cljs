(ns tmhas.components.navigation
  (:require [re-com.core :as re-com]))

(defn nav-link [label to]
      [re-com/hyperlink-href
       :label label
       :class "nav-link ph3 pb1 pt2 ba b--white bw1"
       :href (str "#" to)])

(defn vote-link [label to]
      [re-com/hyperlink-href
       :label label
       :class "vote-link ph3 pb1 pt2 ba b--white bw1"
       :href (str "#" to)])

(defn navigation []
      [:header {:class "w-100 pt2 pb3 ph4 bg-white ttu tracked"}
       [re-com/h-box
        :justify :between
        :children [[:span [nav-link "latest" "/"]]
                   [:span [nav-link "about" "/about"]]
                   [:span [nav-link "people" "/people"]]
                   [:span [nav-link "events" "/events"]]
                   [:span [vote-link "vote" "/"]]]]])
