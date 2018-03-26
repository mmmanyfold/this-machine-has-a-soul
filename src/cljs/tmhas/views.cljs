(ns tmhas.views
  (:require [re-frame.core :as rf]
            [re-com.core :as rc]
            [tmhas.panels.media :refer [media-panel]]
            [tmhas.panels.about :refer [about-panel]]
            [tmhas.panels.people :refer [people-panel]]
            [tmhas.panels.events :refer [events-panel]]
            [tmhas.components.tags :refer [tags]]
            [tmhas.components.navigation :refer [navigation]]
            [tmhas.components.footer :refer [footer]]))


(defn side-panel []
  [rc/v-box
   :class "side-panel fixed fl w-100 w-25-l pv3 pa3 pa4-ns mt4"
   :children [[rc/v-box
               :class "w-100 tc mt4"
               :align :center
               :children [[:img {:src "/img/TMHAS_Logo_600.jpg"
                                 :class "w-50-m w-100-l mt3"}]
                          [:h1 {:class "f5 f3-m fw7 mv3"}
                            "Participatory Budgeting in Denver"]
                          [tags]]]]])
                        ; [filtering component] for media (home) page
                        ; [sub navigation component] in about + people

(defn- show-panel [panel-name]
  (case panel-name
        :media-panel [media-panel]
        :about-panel [about-panel]
        :people-panel [people-panel]
        :events-panel [events-panel]
        :404 [:div "404"]
        [:div]))


(defn- content-panel [panel-name]
  [rc/h-box
   :class "content-panel w-100 h-100 mr3 mr4-ns mt5 pt3"
   :children [[:div {:class "w-0 w-25-ns"}]
              [:div {:class "w-100 w-75-ns ph4 bl-ns bw1-ns"
                     :style {:margin "1.25em 0"}}
                [show-panel panel-name]]]])

(defn main-panel []
  (let [active-panel (rf/subscribe [:active-panel])]
    (fn []
      [:div
       [rc/v-box
        :class "w-100 h-100 mb0"
        :children [[navigation]
                   [side-panel]
                   [content-panel @active-panel]]]
       [footer]])))
