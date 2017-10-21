(ns tmhas.panels.events
  (:require [re-frame.core :as re-frame]
            [re-com.core :as rc]))

(defn events-panel []
      [rc/v-box
       :children
       [[:h1 {:class "bb bw1"} "Calendar of Events"]]])
