(ns tmhas.components.navigation
  (:require [re-com.core :as rc]
            [re-frame.core :as rf]
            [reagent.core :as reagent]))

(defn nav-link [label to panel-name]
  (let [active? (rf/subscribe [:active-panel])
        nav-classes "nav-link ph2 ph3-ns pb1 pt2 ba bw1"
        classes (if (= @active? panel-name)
                    (str nav-classes " active")
                    nav-classes)]
      [rc/hyperlink-href
       :label label
       :class classes
       :href (str "#" to)]))

(defn vote-link [label to]
      [rc/hyperlink-href
       :label label
       :class "vote-link ph3 pb1 pt2 ba bw1"
       :href (str "#" to)])

(defn navigation []
  (let [showing? (reagent/atom false)]
    [:header {:class "nav fixed w-100 pt3 ttu tracked bg-white"}
       [rc/h-box
        :class "mh3 bb bw1 pb3"
        :justify :between
        :children [[:span [nav-link "media" "/" :media-panel]]
                   [:span [nav-link "about" "/about" :about-panel]]
                   [:span [nav-link "people" "/people" :people-panel]]
                   [:span [nav-link "events" "/events" :events-panel]]
                   [rc/popover-anchor-wrapper
                      :showing? showing?
                      :position :right-below
                      :anchor   [:span {:on-mouse-over #(swap! showing? not)
                                        :on-mouse-out  #(swap! showing? not)}
                                    [vote-link "vote" "/"]]
                      :popover  [rc/popover-content-wrapper
                                 :close-button? false
                                 :body          "Coming Soon"]]]]]))
