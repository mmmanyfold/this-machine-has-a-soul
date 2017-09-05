(ns tmhas.panels.people
  (:require [re-com.core :as re-com]
            [tmhas.components.person :refer [person]]))

(defn people-panel []
      [re-com/v-box
       :align :end
       :class "people-panel"
       :children [[:h1 {:class "w-75 ml4 bb bw1"}
                    "Project VOYCE"]
                  [person]
                  [person]

                  [:h1 {:class "w-75 ml4 bb bw1"}
                    "Project Belay"]
                  [person]
                  [person]

                  [:h1 {:class "w-75 ml4 bb bw1"}
                    "The Artists"]
                  [person]
                  [person]

                  [:h1 {:class "w-75 ml4 bb bw1"}
                    "Warm Cookies of the Revolution"]
                  [person]]])
