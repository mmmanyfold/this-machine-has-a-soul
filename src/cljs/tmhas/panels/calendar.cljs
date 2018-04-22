(ns tmhas.panels.calendar
  (:require [re-frame.core :as re-frame]
            [re-com.core :as rc]
            [tmhas.components.calendar-event :refer [event]]))

(defn calendar-panel []
      [rc/v-box
       :children
       [[:h1 "Calendar of Events"]
        [event]
        [event]]])
