(ns tmhas.panels.media-post
  (:require [re-frame.core :as rf]
            [re-com.core :as rc]
            [re-com.modal-panel :refer [modal-panel-args-desc]]
            [reagent.core :as reagent]
            [tmhas.components.common :refer [embed-video showdown]]
            [goog.string :refer [format]]
            [cljsjs.bootstrap]))

(defn media-component [active-post]
  (let [type (-> active-post :sys :contentTypeId)]
    (case type
      "singleImage"
      [:img {:class "mb1 w-100"
             :src (-> active-post :imageFile :url)}]
      "manyImagePost"
      [:div {:id "image-gallery", :class "carousel slide", :data-ride "carousel" :data-interval false}
        ; indicators
        [:ol {:class "carousel-indicators"}
         [:li {:data-target "#image-gallery", :data-slide-to "0", :class "active"}]
         [:li {:data-target "#image-gallery", :data-slide-to "1"}]]
        ; wrapper for slides
        [:div {:class "carousel-inner"}
         [:div {:class "item active"}
          [:img {:src (-> active-post :images first :url), :alt "Chania"}]
          [:div {:class "carousel-caption f5 f5-m f4-l"}
           [:p (-> active-post :images first :description)]]]
         [:div {:class "item"}
          [:img {:src (-> active-post :images second :url), :alt "Chicago"}]
          [:div {:class "carousel-caption f5 f5-m f4-l"}
           [:p (-> active-post :images second :description)]]]]
        ; left and right controls
        [:a {:class "left carousel-control", :href "#image-gallery", :data-slide "prev"}
         [:span {:class "glyphicon glyphicon-chevron-left"}]
         [:span {:class "sr-only"} "Previous"]]
        [:a {:class "right carousel-control", :href "#image-gallery", :data-slide "next"}
         [:span {:class "glyphicon glyphicon-chevron-right"}]
         [:span {:class "sr-only"} "Next"]]]
      "video"
      (let [videoUrl (active-post :videoUrl)
            video-src (embed-video videoUrl)]
        [:div {:class "media-wrapper"}
         [:iframe {:src video-src
                   :frameBorder "0"
                   :allowFullScreen true}]]))))

(defn media-post-panel [posts]
  (let [active-post-id (rf/subscribe [:active-post-id])]
    (when (and @(rf/subscribe [:show-media-post]) @posts)
      (let [active-post (some #(when (= (get-in % [:sys :id]) @active-post-id) %) @posts)
            {:keys [postTitle
                    postText
                    postDate
                    tags]} active-post]
        [rc/modal-panel
         :backdrop-on-click #(rf/dispatch [:set-show-media-post false])
         :wrap-nicely? false
         :style {:overflow-y "scroll"}
         :child [rc/v-box
                 :class "pa5-m pa6-l"
                 :children [[:div.media-post
                             [:i {:class "fa fa-times-circle f3 f2-ns pointer"
                                  :aria-hidden true
                                  :on-click #(rf/dispatch [:set-show-media-post false])}]
                             [:h2 {:class "mt3 mb1 mh1 f3 f2-ns"}
                                  postTitle]
                             [:div {:class "metadata f5"}
                               [:span postDate][:span "•"]
                               [:span "#Cole"] [:span "#Art"] [:span "#Process"]
                               [:p {:class "f4 mh1 mt2"} postText]]
                             [media-component active-post]]]]]))))
