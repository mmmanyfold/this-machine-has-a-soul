(ns tmhas.components.navigation
  (:require [re-com.core :as re-com]
            [re-frame.core :as rf]))

(defn nav-link [label to panel-name]
  (let [active? (rf/subscribe [:active-panel])
        nav-classes "nav-link ph3 pb1 pt2 ba b--white bw1"
        classes (if (= @active? panel-name)
                    (str nav-classes " active")
                    nav-classes)]
      [re-com/hyperlink-href
       :label label
       :class classes
       :href (str "#" to)]))

(defn vote-link [label to]
      [re-com/hyperlink-href
       :label label
       :class "vote-link ph3 pb1 pt2 ba b--white bw1"
       :href (str "#" to)])

(defn navigation []
      [:header {:class "w-100 pt2 pb3 ph4 bg-white ttu tracked"}
       [re-com/h-box
        :justify :between
        :children [[:span [nav-link "latest" "/" :home-panel]]
                   [:span [nav-link "about" "/about" :about-panel]]
                   [:span [nav-link "people" "/people" :people-panel]]
                   [:span [nav-link "events" "/events" :events-panel]]
                   [:span [vote-link "vote" "/"]]]]])
