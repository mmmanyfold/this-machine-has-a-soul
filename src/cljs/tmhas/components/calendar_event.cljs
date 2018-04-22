(ns tmhas.components.calendar-event
  (:require [clojure.string :as string]
            [cljsjs.moment]
            [tmhas.components.common :refer [showdown]]))

(defn render-event [event]
  (let [{:keys [summary
                description
                location
                start
                end]} event
        start-date (:dateTime start)
        end-date (:dateTime end)]
    [:div.event-wrapper.mt0.mb3.pt4.pb2.bt
     [:div.event-heading.flex.flex-row
      [:div.event-date.relative.tc
       [:i.fa.fa-calendar-o]
       [:div.calendar-date.w-100.absolute.flex.flex-column
        [:p.f5.mb0 (.format (js/moment start-date) "MMM")]
        [:p.f2 (.format (js/moment start-date) "DD")]]]
      [:div.ml3
       [:h2.mt1.b summary]
       [:h3.f4.mt3
        (str (.format (js/moment start-date) "h:mm a")
             " - "
             (.format (js/moment end-date) "h:mm a"))
        [:br] "@ "
        [:a {:href (str "https://www.google.com/maps/search/" location)
             :target "_blank"}
         (first (string/split location #","))]]]]
     [:div {:class "event-info mt2"
            "dangerouslySetInnerHTML"
             #js{:__html (.makeHtml showdown description)}}]]))
