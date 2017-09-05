(ns tmhas.components.navigation
  (:require [re-com.core :as re-com]
            [tmhas.components.common :refer [link]]))

(defn navigation []
      [:header {:class "f2 w-100 pa3 bg-white"}
       [re-com/h-box
        :justify :around
        :children [[:span {:class "nav-link ph4 pv2 ba b--white bw1"}
                      [link "home" "/"]]
                   [:span {:class "nav-link ph4 pv2 ba b--white bw1"}
                      [link "about" "/about"]]
                   [:span {:class "nav-link ph4 pv2 ba b--white bw1"}
                      [link "people" "/people"]]
                   [:span {:class "nav-link ph4 pv2 ba b--white bw1"}
                      [link "events" "/events"]]
                   [:span [link "vote" "/"]]]]])
