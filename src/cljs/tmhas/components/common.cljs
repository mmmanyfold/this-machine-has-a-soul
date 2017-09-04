(ns tmhas.components.common
  (:require [re-com.core :as re-com]))

(declare link)

(defn navigation []
      [re-com/h-box
       :gap "1em"
       :children [[:header
                   {:class "fl w-100 pa3 ph5-ns bg-white ba"}
                   [:h1 "NAV"]
                   [re-com/h-box
                    :children [[:ul
                                [:li [:span [link "home" "#/"]]]
                                [:li [:span [link "about" "#/about"]]]
                                [:li [:span [link "people" "#/people"]]]
                                [:li [:span [link "events" "#/events"]]]]]]]]])


(defn link [label to]
      [re-com/hyperlink-href
       :label label
       :href to])
