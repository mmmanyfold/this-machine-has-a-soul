(ns tmhas.panels.events
  (:require [re-frame.core :as re-frame]
            [re-com.core :as rc]))

(defn events-panel []
      [rc/v-box
       :children
       [[:h1 "Calendar of Events"]
        [:h2.ml4-ns.mt0.i.b.tracked "Coming soon !"]]])
