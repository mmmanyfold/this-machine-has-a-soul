(ns tmhas.components.media-thumb
  (:require [re-com.core :as rc]
            [re-frame.core :as rf]
            [tmhas.components.common :refer [embed-video]]
            [reagent.core :as reagent]
            [tmhas.components.common :refer [showdown]]))

(defn metadata [id postTitle postText postDate tags]
  [:div
   [:h2 {:class "mb1 mh1 mt3 f2 f3-ns"}
    [:a {:href (str "/#/post/" id)}
      (str postTitle " →")]]
   [:div {:class "metadata f5"}
    (when postText
          [:div {:class "mh1 mv0"
                 "dangerouslySetInnerHTML"
                 #js{:__html (.makeHtml showdown postText)}}])
    [:div.mv1
     [:span postDate]
     (when tags
       [:span "•"]
       [:section.tags
        (for [tag tags]
             ^{:key (gensym "tag-")}
             [:span (str "#" tag)])])]]])


(defn single-image [id post]
  (let [{:keys [postTitle
                postDate
                postText
                tags
                imageFile]} post]
       [rc/v-box
        :class "media-thumb"
        :children [[:div.media-wrapper
                    [:a {:href (str "/#/post/" id)
                         :on-click #(rf/dispatch [:set-show-media-post true])}
                     [:div {:class "mb1"
                            :style {:background-image (str "url('" (imageFile :url) "')")
                                    :background-size "cover"}}]]]
                   [metadata id postTitle postText postDate tags]]]))


(defn image-gallery [id post]
  (let [{:keys [postTitle
                postDate
                postText
                tags
                images]} post]
       [rc/v-box
        :class "media-thumb"
        :children [[:div {:class "relative"}
                    [:div.media-wrapper
                     [:a {:href (str "/#/post/" id)
                          :on-click #(rf/dispatch [:set-show-media-post true])}
                      [:div {:class "mb1"
                             :style {:background-image (str "url('" (-> images first :url) "')")
                                     :background-size "cover"}}]]]
                    [:div {:class "gallery-icon absolute ph2 pb1 pt2 bg-white o-50 br1"}
                     [:i {:class "fa fa-clone f3"
                          :aria-hidden true}]]]
                   [metadata id postTitle postText postDate tags]]]))

(defn video [id post]
  (fn []
    (reagent/create-class
      {:component-did-mount
        #(.fitVids (js/$ ".video-wrapper"))
       :reagent-render
        (fn []
          (let [{:keys [postTitle
                        postDate
                        postText
                        tags
                        videoUrl]} post
                video-src (embed-video videoUrl)]
               [rc/v-box
                :class "media-thumb"
                :children [[:div.video-wrapper
                            [:iframe {:src video-src
                                      :frameBorder "0"
                                      :allowFullScreen true}]]
                           [metadata id postTitle postText postDate tags]]]))})))
