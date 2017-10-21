(ns tmhas.panels.media-post
  (:require [re-frame.core :as rf]
            [re-com.core :as rc]
            [re-com.modal-panel :refer [modal-panel-args-desc]]
            [reagent.core :as reagent]
            [tmhas.components.common :refer [showdown]]
            [goog.string :refer [format]]))

(defn query [id]
  (format
   "{ singleImageTextPost(id: \"%s\") {
       postTitle
       postDate
       postText
       imageFile {
         title
         description
         url
       }
      }
    }" id))

(defn media-post-panel []
  (let [db-key :active-post
        active-post-id (rf/subscribe [:active-post-id])]
    (rf/reg-sub db-key #(db-key %))
    (when @active-post-id
      (fn []
        (rf/dispatch [:get-contentful-data db-key (query @active-post-id) :media])
        (let [active-post (rf/subscribe [db-key])
              _ (prn @active-post)]
          (when @(rf/subscribe [:show-media-post])
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
                                 [:img {:class "mb1"
                                        :src ""}]
                                 [:h2 {:class "mb0 mh1 mt2 f2 f3-ns"}
                                      "Case Studies"]
                                 [:div {:class "metadata f5"}
                                   [:span "10/7/17"][:span "•"]
                                   [:span "#Cole"] [:span "#Art"] [:span "#Process"]
                                   [:p.ma1 "Full text from post text or image caption..."]]]]]]))))))
