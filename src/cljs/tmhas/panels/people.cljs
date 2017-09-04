(ns tmhas.panels.people
  (:require [re-frame.core :as re-frame]
            [re-com.core :as re-com]))

(defn people-title []
      [re-com/title
       :label "This is the People Page."
       :level :level1])

(defn people-panel []
      [re-com/v-box
       :gap "1em"
       :children [[people-title]]])
