(ns tmhas.panels.calendar
  (:require [re-frame.core :as rf]
            [re-com.core :as rc]
            [tmhas.components.calendar-event :refer [render-event]]))

(defn calendar-panel []
  (rf/dispatch [:get-calendar-data])
  (let [events @(rf/subscribe [:calendar-events])]
    [rc/v-box
     :children
     [[:h1 "Calendar of Events"]
      (for [event events]
        ^{:key (gensym "e-")}
        [render-event event])]]))
