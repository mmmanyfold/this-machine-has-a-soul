(ns tmhas.panels.events
  (:require [re-frame.core :as re-frame]
            [re-com.core :as rc]))

(defn events-panel []
      [rc/v-box
       :children
       [[:h1 "Calendar of Events"]
        [:p "COMING SOON"]]])
