(ns tmhas.components.media-thumb
  (:require [re-com.core :as re-com]
            [tmhas.components.common :refer [showdown]]))

(defn metadata [postTitle postText postDate tags]
  [:div
   [:h2 {:class "mb1 mh1 mt3 f2 f3-ns"}
    postTitle]
   [:div {:class "metadata f5"}
    (when postText
          [:p {:class "mh1 mv0 truncate"} postText])
    [:div.mv1
     [:span postDate]
     [:span "•"]
     [:section.tags
      (for [tag tags]
           ^{:key (gensym "tag-")}
           [:span (str "#" tag)])]]]])

(defn single-image [data]
  (let [{:keys [postTitle
                postDate
                postText
                tags
                imageFile]}
        data]
       [re-com/v-box
        :class "media-thumb"
        :children [[:div.media-wrapper
                    [:div {:class "mb1"
                           :style {:background-image (str "url('" (imageFile :url) "')")
                                   :background-size "cover"}}]]
                   [metadata postTitle postText postDate tags]]]))

(defn image-gallery [data]
  (let [{:keys [postTitle
                postDate
                postText
                tags
                images]}
        data]
       [re-com/v-box
        :class "media-thumb"
        :children [[:div {:class "relative"}
                    [:div.media-wrapper
                     [:div {:class "mb1"
                            :style {:background-image (str "url('" (-> images first :url) "')")
                                    :background-size "cover"}}]]
                    [:div {:class "gallery-icon absolute ph2 pb1 pt2 bg-white o-50 br1"}
                     [:i {:class "fa fa-clone f3"
                          :aria-hidden true}]]]
                   [metadata postTitle postText postDate tags]]]))

(defn video [data]
  (let [{:keys [postTitle
                postDate
                postText
                tags
                videoUrl]} data
        video-src (cond (re-find #"https://vimeo.com/" videoUrl)
                        (str "https://player.vimeo.com/video/" (re-find #"\d+" videoUrl) "?color=739f3e&title=0&byline=0&portrait=0&badge=0")

                        (re-find #"https://www.youtube.com/watch?v=" videoUrl)
                        (if (re-find #"=(.*)&" videoUrl)
                            (str "https://www.youtube.com/embed/" (second (re-find #"=(.*)&" videoUrl)) "?rel=0&amp;showinfo=0")
                            (str "https://www.youtube.com/embed/" (second (re-find #"=(.*)" videoUrl)) "?rel=0&amp;showinfo=0"))

                        (re-find #"https://youtu.be/" videoUrl)
                        (str "https://www.youtube.com/embed/" (second (re-find #"youtu.be/([\s\S]*)" videoUrl)) "?rel=0&amp;showinfo=0"))]

       [re-com/v-box
        :class "media-thumb"
        :children [[:div.media-wrapper
                    [:iframe {:src video-src
                              :frameBorder "0"
                              :allowFullScreen true}]]
                   [metadata postTitle postText postDate tags]]]))
