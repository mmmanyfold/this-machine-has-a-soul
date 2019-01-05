(ns tmhas.components.navigation
  (:require cljsjs.jquery
            [re-com.core :as rc]
            [re-frame.core :as rf]
            [reagent.core :as reagent]))

(defn nav-link [label to panel-name]
  (let [active? (rf/subscribe [:active-panel])
        nav-classes "nav-link ph2 ph3-ns pb1 ba bw1 mr3-m mr5-l"
        classes (if (= @active? panel-name)
                    (str nav-classes " active")
                    nav-classes)]
      [:a {:href (str "#" to)
           :class classes
           :on-click (fn []
                       (.scrollTop (js/$ "html, body") 0)
                       (rf/dispatch [:set-filter-tag nil true])
                       (rf/dispatch [:set-active-section nil true]))}
       label]))

(defn vote-link [label to]
      [rc/hyperlink-href
       :label label
       :class "vote-link ph3 pb1 ba bw1"
       :href to])

(defn navigation []
  (let [showing?a (reagent/atom false)
        showing?b (reagent/atom false)]
    [:header {:class "fixed w-100 pt3 ttu tracked bg-white"}
       [rc/h-box
        :class "nav-wrapper mh3 mh4-ns bb bw1 pb3"
        :justify :between
        :children [[:div {:class "nav-left flex"}
                    [nav-link "media" "/" :media-panel]
                    [nav-link "about" "/about" :about-panel]
                    [nav-link "people" "/people" :people-panel]
                    [nav-link "events" "/events" :calendar-panel]
                    [rc/popover-anchor-wrapper
                       :showing? showing?a
                       :position :below-center
                       :anchor   [:span {:on-mouse-over #(swap! showing?a not)
                                         :on-mouse-out  #(swap! showing?a not)}
                                [vote-link "vote" "/"]]
                       :popover  [rc/popover-content-wrapper
                                  :close-button? false
                                  :body          "Voting has ended"]]]
                   [:div {:class "nav-right flex items-center"}
                    [:div {:class "subtitle f7 tr mr3"} "Participatory Budgeting"
                     [:br] "in Denver, CO"]
                    [rc/popover-anchor-wrapper
                       :showing? showing?b
                       :position :below-center
                       :anchor   [:span {:on-mouse-over #(swap! showing?b not)
                                         :on-mouse-out  #(swap! showing?b not)}
                                     [vote-link "vote" "http://www.colehasasoul.com"]]
                       :popover  [rc/popover-content-wrapper
                                  :close-button? false
                                  :body          "Vote in Cole Election"]]]]]
      [:a.call-for-ideas {:href "/#/submit-ideas"
                          :on-click #(.scrollTop (js/$ "html, body") 0)}
       [:div {:class "flex align-center justify-center"}
         [:p.ma0]]]]))
