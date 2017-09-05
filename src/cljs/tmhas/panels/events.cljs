(ns tmhas.panels.events
  (:require [re-frame.core :as re-frame]
            [re-com.core :as re-com]))

(defn events-panel []
      [re-com/v-box
       :children
       [[:h1 "Calendar of Events"]]])
