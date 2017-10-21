(ns tmhas.panels.media-post
  (:require [re-frame.core :as rf]
            [re-com.core :as rc]
            [re-com.modal-panel :refer [modal-panel-args-desc]]
            [reagent.core :as reagent]
            [tmhas.components.common :refer [showdown]]
            [goog.string :refer [format]]))

(defn media-component [active-post]
  (let [type (-> active-post :sys :contentTypeId)]
    (case type
      "singleImage"
      [:img {:class "mb1"
             :src (-> active-post :imageFile :url)}]

      "manyImagePost"
      [:img {:class "mb1"
             :src (-> active-post :images first :url)}]

      "video"
      (let [videoUrl (active-post :videoUrl)
           ;TODO: refactor this into its own function
            video-src (cond (re-find #"https://vimeo.com/" videoUrl)
                            (str "https://player.vimeo.com/video/" (re-find #"\d+" videoUrl) "?color=739f3e&title=0&byline=0&portrait=0&badge=0")

                            (re-find #"https://www.youtube.com/watch?v=" videoUrl)
                            (if (re-find #"=(.*)&" videoUrl)
                                (str "https://www.youtube.com/embed/" (second (re-find #"=(.*)&" videoUrl)) "?rel=0&amp;showinfo=0")
                                (str "https://www.youtube.com/embed/" (second (re-find #"=(.*)" videoUrl)) "?rel=0&amp;showinfo=0"))

                            (re-find #"https://youtu.be/" videoUrl)
                            (str "https://www.youtube.com/embed/" (second (re-find #"youtu.be/([\s\S]*)" videoUrl)) "?rel=0&amp;showinfo=0"))]
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
                 :class "w-100 pa5-ns"
                 :children [[:div.media-post
                             [:i {:class "fa fa-times-circle f2 pointer"
                                  :aria-hidden true
                                  :on-click #(rf/dispatch [:set-show-media-post false])}]
                             [media-component active-post]
                             [:h2 {:class "mb0 mh1 mt2 f2 f3-ns"}
                                  postTitle]
                             [:div {:class "metadata f5"}
                               [:span postDate][:span "•"]
                               [:span "#Cole"] [:span "#Art"] [:span "#Process"]
                               [:p.ma1 postText]]]]]]))))
