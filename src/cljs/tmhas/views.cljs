(ns tmhas.views
  (:require [re-frame.core :as re-frame]
            [re-com.core :as re-com]
            [tmhas.panels.home :refer [home-panel]]
            [tmhas.panels.about :refer [about-panel]]
            [tmhas.panels.people :refer [people-panel]]
            [tmhas.panels.events :refer [events-panel]]
            [tmhas.components.navigation :refer [navigation]]))


(defn side-panel []
  [re-com/v-box
   :class "side-panel fl w-100 w-30-l ph1 pv3 pa4-ns"
   :children [[re-com/v-box
               :class "w-100 tc"
               :align :center
               :children [[:img {:src "/img/TMHAS_Logo_600.jpg"
                                 :class "w-50-m w-100-l mb2"}]
                          [:h1 {:class "f4 f3-m mv3"}
                            "Participatory Budgeting in Denver"]
                          [:div {:class "mail mail-ns bb bw1 pointer"
                                 :on-click #(js/window.showMailingPopUp)}
                              [:h4 "Join Mailing List >>"]]]]]])
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
   :class "content-panel fl w-100 w-70-l ph4-ns"
   :children [[re-com/v-box
               :class "w-100"
               :children [[show-panel panel-name]
                          [:div {:class "mail mail-s bb bw1 pointer"
                                 :on-click #(js/window.showMailingPopUp)}
                              [:h4 "Join Mailing List >>"]]
                          [:div {:class "mail mail-xs bb bw1"}
                              [:h4 [:a {:href "http://eepurl.com/c7-hYL"
                                        :target "_blank"}
                                    "Join Mailing List >>"]]]]]]])


(defn main-panel []
  (let [active-panel (re-frame/subscribe [:active-panel])]
    (fn []
      [re-com/v-box
       :class "w-100 pv3 ph3 mb5"
       :children [[navigation]
                  [:div.w-100.ph1
                      [side-panel]
                      [content-panel @active-panel]]]])))
