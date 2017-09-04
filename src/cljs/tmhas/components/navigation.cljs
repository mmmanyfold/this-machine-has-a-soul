(ns tmhas.components.navigation
  (:require [re-com.core :as re-com]
            [tmhas.components.common :refer [link]]))

(defn navigation []
      [re-com/h-box
       :gap "1em"
       :children [[:header
                   {:class "fl w-100 pa3 ph5-ns bg-white ba"}
                   [:h1 "NAV"]
                   [re-com/h-box
                    :children [[:ul
                                [:li [:span [link "home" "/"]]]
                                [:li [:span [link "about" "about"]]]
                                [:li [:span [link "people" "people"]]]
                                [:li [:span [link "events" "events"]]]]]]]]])