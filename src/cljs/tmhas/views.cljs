(ns tmhas.views
  (:require [re-frame.core :as rf]
            [re-com.core :as rc]
            [tmhas.panels.media :refer [media-panel]]
            [tmhas.panels.about :refer [about-panel]]
            [tmhas.panels.people :refer [people-panel]]
            [tmhas.panels.calendar :refer [calendar-panel]]
            [tmhas.components.tags :refer [tags]]
            [tmhas.components.navigation :refer [navigation]]
            [tmhas.components.footer :refer [footer]]))


(defn side-panel []
  [rc/v-box
   :class "side-panel fixed fl w-100 w-25-l w-30-m ph3 ph4-ns pb0-m pt3-ns mt5"
   :children [[rc/v-box
               :class "w-100 tc mt3 mt4-ns"
               :align :center
               :children [[:a {:href "/#"
                               ;; reset tag filter
                               :on-click #(rf/dispatch [:set-filter-tag nil true])}
                           [:img {:src "/img/TMHAS_Logo_600.jpg"
                                  :class "w-100"}]]
                          [tags]]]]])
                        ; [filtering component] for media (home) page
                        ; [sub navigation component] in about + people

(defn- show-panel [panel-name]
  (case panel-name
        :media-panel [media-panel]
        :about-panel [about-panel]
        :people-panel [people-panel]
        :calendar-panel [calendar-panel]
        :404 [:div "404"]
        [:div]))


(defn- content-panel [panel-name]
  [rc/h-box
   :class "content-panel w-100 mr3 mr4-ns mt5-ns pt3"
   :children [[:div {:class "push w-0 w-25-l w-30-m"}]
              [:div {:class "main w-100 w-75-l w-70-m ph3 ph4-ns"
                     :style {:margin "1.25em 0"}}
                [show-panel panel-name]]]])

(defn main-panel []
  (let [active-panel (rf/subscribe [:active-panel])]
    (fn []
      [rc/v-box
       :class "w-100 h-100 mb0"
       :children [[navigation]
                  [side-panel]
                  [content-panel @active-panel]
                  [footer]]])))
