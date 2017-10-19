(ns tmhas.panels.home
  (:require [re-frame.core :as re-frame]
            [re-com.core :as re-com]
            [tmhas.components.media-thumb :refer [media-thumb]]
            [tmhas.components.common :refer [showdown]]))

(defn home-panel []
      [re-com/v-box
       :children
       [[:h1 {:class "bb bw1"} "Latest"]
        [:div {:class "flexrow-wrap w-100 mv3"}
              [media-thumb]
              [media-thumb]
              [media-thumb]
              [media-thumb]]]])
