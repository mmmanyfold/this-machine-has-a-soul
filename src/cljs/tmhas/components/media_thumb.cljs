(ns tmhas.components.media-thumb
  (:require [re-com.core :as rc]
            [re-frame.core :as rf]
            [tmhas.components.common :refer [embed-video]]
            [reagent.core :as reagent]
            [tmhas.components.common :refer [showdown]]
            cljsjs.moment))

(defn metadata [id postTitle postText postDate tags]
  [:div.w-100.w-50-l.pa3
   [:h1 {:class "mb2 mh1 mt1 f3"} postTitle]
   [:div {:class "metadata"}
    (when postText
          [:div {:class "mh1 mv0"
                 "dangerouslySetInnerHTML"
                 #js{:__html (.makeHtml showdown postText)}}])
    [:div.mv1.f5
     [:span (.format (js/moment postDate) "MMM D, YYYY")]
     (when tags
       [:section.tags
        [:span "•"]
        (for [tag tags]
             ^{:key (gensym "tag-")}
             [:span (str "#" tag)])])]]])


(defn single-image [id post index]
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


(defn image-gallery [id post index]
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

(defn video [id post index]
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
               [rc/h-box
                :class "media-thumb w-100"
                :style {:flex-flow "row wrap"}
                :children [[:div.w-100.w-50-l
                            [:div.video-wrapper
                             [:iframe {:src video-src
                                       :frameBorder "0"
                                       :allowFullScreen true}]]]
                           [metadata id postTitle postText postDate tags]]]))})))
