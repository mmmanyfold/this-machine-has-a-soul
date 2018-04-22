(ns tmhas.panels.calendar
  (:require [re-frame.core :as rf]
            [re-com.core :as rc]
            [tmhas.components.calendar-event :refer [render-event]]
            [tmhas.components.loading :refer [loading-component]]))

(defn calendar-panel []
  (rf/dispatch [:get-calendar-data])
  (if-let [events @(rf/subscribe [:calendar-events])]
    (if (nil? events)
      [:h1 "No upcoming events."]
      [rc/v-box
       :children
       [[:h1 "Upcoming Events"]
        (for [event events]
          ^{:key (gensym "e-")}
          [render-event event])]])
    [loading-component]))
