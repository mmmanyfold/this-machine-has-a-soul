(ns tmhas.views
  (:require [re-frame.core :as re-frame]
            [re-com.core :as re-com]
            [tmhas.panels.home :refer [home-panel]]
            [tmhas.panels.about :refer [about-panel]]
            [tmhas.panels.people :refer [people-panel]]
            [tmhas.panels.events :refer [events-panel]]
            [tmhas.components.navigation :refer [navigation]]))


(defn side-panel []
  [re-com/h-box
   :class "side-panel fl w-30 pa4 bg-white"
   :children [[re-com/v-box
               :class "w-100"
               :children [[:img {:src "/img/TMHAS_Logo_600.jpg"
                                 :width "100%"}]
                          [:h1 {:class "f3 tc mv4"}
                            "Participatory Budgeting Project"]]]]])
                        ; [filtering component] for homepage
                        ; [sub navigation component] in about + people

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
   :class "fl w-70 pv3 ph4 bg-white"
   :children [[re-com/v-box
               :class "w-100"
               :children [[show-panel panel-name]]]]])


(defn main-panel []
  (let [active-panel (re-frame/subscribe [:active-panel])]
    (fn []
      [re-com/v-box
       :height "100%"
       :class "w-100 pa3 bg-white mb5"
       :children [[navigation]
                  [re-com/h-box
                   :children [[side-panel]
                              [content-panel @active-panel]]]]])))
