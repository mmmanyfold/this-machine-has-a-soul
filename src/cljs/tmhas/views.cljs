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
   :gap "1em"
   :children [[re-com/v-box
               :children [[:h2 "_side-panel"]]]]])


(defn content-panel []
  [re-com/h-box
   :gap "1em"
   :children [[re-com/v-box
               :children [[:h2 "_content-panel"]]]]])


(defn- panels [panel-name]
  (case panel-name
    :home-panel  [home-panel]
    :about-panel [about-panel]
    :people-panel [people-panel]
    :events-panel [events-panel]
    :404         [:div "404"]
    [:div]))


(defn main-panel []
  (let [active-panel (re-frame/subscribe [:active-panel])]
    (fn []
      [re-com/v-box
       :height "100%"
       :class "w-100 pa3 ph5-ns bg-white"
       :children [[components/navigation]
                  [side-panel]
                  [content-panel]
                  [panels @active-panel]]])))