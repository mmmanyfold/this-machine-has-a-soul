(ns tmhas.components.media-thumb
  (:require [re-com.core :as re-com]
            [tmhas.components.common :refer [showdown]]))

(defn tags-component [tags]
  [:section.tags
   (for [tag tags]
        ^{:key (gensym "tag-")}
        [:span (str "#" tag)])])

(defn single-image [data]
  (let [{:keys [postTitle
                postDate
                postText
                tags
                images]}
        data]
       [re-com/v-box
        :class "media-thumb mb2"
        :children [[:img {:class "mb1"
                          :src   (-> images first :url)}]
                   [:h2 {:class "mb0 mh1 mt2 f2 f3-ns"}
                    postTitle]
                   [:div {:class "metadata f5"}
                    (when postText
                          [:p.ma1 postText])
                    [:span postDate]
                    [:span "•"]
                    [tags-component tags]]]]))

(defn image-gallery [data]
  (let [{:keys [postTitle
                postDate
                postText
                tags
                images]}
        data]
       [re-com/v-box
        :class "media-thumb mb2"
        :children [[:div {:class "relative"}
                    [:img {:class "mb1"
                           :src   (-> images first :url)}]
                    [:div {:class "gallery-icon absolute ph2 pb1 pt2 bg-white o-50 br1"}
                     [:i {:class "fa fa-clone f3"
                          :aria-hidden true}]]]
                   [:h2 {:class "mb0 mh1 mt2 f2 f3-ns"}
                    postTitle]
                   [:div {:class "metadata f5"}
                    (when postText
                          [:p.ma1 postText])
                    [:span postDate]
                    [:span "•"]
                    [tags-component tags]]]]))
