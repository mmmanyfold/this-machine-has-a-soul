(ns tmhas.components.media-thumb
  (:require [re-com.core :as re-com]
            [tmhas.components.common :refer [showdown]]))

(defn media-thumb []
      [re-com/v-box
       :class "media-thumb"
       :children [[:img {:class "mb1"
                         :src "../img/computer2.gif"}]
                  [:h2 {:class "mb0 mh1 mt2 f2 f3-ns"}
                       "Case Studies"]
                  [:div {:class "metadata f5"}
                    [:p.ma1 "One line of text from post text or image caption..."]
                    [:span "10/7/17"][:span "•"]
                    [:span "#Cole"] [:span "#Art"] [:span "#Process"]]]])
