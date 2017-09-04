(ns tmhas.panels.about
  (:require [re-frame.core :as re-frame]
            [re-com.core :as re-com]))

(defn about-title []
      [re-com/title
       :label "This is the About Page."
       :level :level1])

(defn about-panel []
      [re-com/v-box
       :gap "1em"
       :children [[about-title]]])