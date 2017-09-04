(ns tmhas.panels.events
  (:require [re-frame.core :as re-frame]
            [re-com.core :as re-com]))

(defn events-title []
      [re-com/title
       :label "This is the Events Page."
       :level :level1])

(defn events-panel []
      [re-com/v-box
       :gap "1em"
       :children [[events-title]]])