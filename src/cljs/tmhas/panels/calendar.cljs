(ns tmhas.panels.calendar
  (:require [re-frame.core :as rf]
            [re-com.core :as rc]
            [tmhas.components.calendar-event :refer [render-event]]
            [tmhas.components.loading :refer [loading-component]]))

(def remove-nil (partial remove nil?))

(defn calendar-panel []
  (rf/dispatch [:get-calendar-data])
  (if-let [events @(rf/subscribe [:calendar-events])]
    (let [now (js/Date.)
          upcoming-events (remove-nil
                            (map #(when (< now (js/Date. (get-in % [:end :dateTime]))) %)
                              events))
          past-events (remove-nil
                        (map #(when (> now (js/Date. (get-in % [:end :dateTime]))) %)
                          events))]
      [:div
       (if (seq upcoming-events)
         [rc/v-box
          :class "pb2"
          :children
          [[:h1 "Upcoming Events"]
           (for [event upcoming-events]
             ^{:key (gensym "ue-")}
             [render-event event])]]
         [:h1 "There are no upcoming events at this time."])
       (when (seq past-events)
         [rc/v-box
          :children
          [[:h1.mt4.i "Past Events"]
           (for [event past-events]
             ^{:key (gensym "pe-")}
             [render-event event])]])])
    [loading-component]))
