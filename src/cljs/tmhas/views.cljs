(ns tmhas.views
  (:require [re-frame.core :as re-frame]
            [re-com.core :as re-com]
            [tmhas.components.common :as components]
            [tmhas.panels.home :refer [home-panel]]
            [tmhas.panels.about :refer [about-panel]]
            [tmhas.panels.people :refer [people-panel]]
            [tmhas.panels.events :refer [events-panel]]))


(defn side-panel []
  [re-com/h-box
   :class "fl w-30 pa3 ph5-ns bg-white ba"
   :children [[re-com/v-box
               :children [[:h2 "_side-panel"]]]]])

(defn- show-panel [panel-name]
  (case panel-name
        :home-panel [home-panel]
        :about-panel [about-panel]
        :people-panel [people-panel]
        :events-panel [events-panel]
        :404 [:div "404"]
        [:div]))


(defn- content-panel [panel-name]
  [re-com/h-box
   :class "fl w-70 pa3 ph5-ns bg-white ba"
   :children [[re-com/v-box
               :children [[:h2 "_content-panel"]
                          [show-panel panel-name]]]]])


(defn main-panel []
  (let [active-panel (re-frame/subscribe [:active-panel])]
    (fn []
      [re-com/v-box
       :height "100%"
       :class "w-100 pa3 ph5-ns bg-white"
       :children [[components/navigation]
                  [re-com/h-box
                   :children [[side-panel]
                              [content-panel @active-panel]]]]])))
