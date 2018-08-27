(ns tmhas.panels.media
  (:require [re-frame.core :as rf]
            [re-com.core :as rc]
            [tmhas.components.media-thumb :as media-thumb]
            [tmhas.panels.media-post :refer [media-post-panel]]
            [tmhas.components.common :refer [showdown]]
            [tmhas.components.loading :refer [loading-component]]))

(def query
  "{
    imageGalleryPosts {
      sys { id contentTypeId }
			postText
			postDate
			postTitle
			tags
			images { url description }
		},
    singleImageTextPosts {
      sys { id contentTypeId }
			postText
			postDate
			postTitle
			tags
			imageFile { url description }
		},
    videoPosts {
      sys { id contentTypeId }
			postText
			postDate
			postTitle
			tags
			videoUrl
		}
	}")

(def filter-tag (rf/subscribe [:filter-tag]))

(defn media-panel []
  (let [db-key :media-posts]                                 ;; 0. declare unique db-key
    (rf/reg-sub db-key #(db-key %))                          ;; 1. register subscriber db-key
    (rf/dispatch [:get-contentful-data db-key query :media]) ;; 2. retrieve contentful data & pass key for assoc in db
    (if-let [posts (rf/subscribe [db-key])]
      [rc/v-box
       :children
       [[rc/v-box
         :children
         [[:div {:class "media-thumb w-100 mt2"
                 :style {:text-align "left"}}
           [:div {:class "ph3 ph3-ns pt1 pt2-ns pb1 pb2-ns"}
            [:p {:class "mv2 text-box lh-title"} "Interested in supporting and/or volunteering for the Auraria Participatory Budgeting effort? "
             [:a {:href "https://docs.google.com/forms/d/e/1FAIpQLSdFoy4vS18AXOoxLl_pdCsZj5TBQLeCq-J3ws5WsrTkJL-KSA/viewform"}
                 "Get in touch"]
             "."]]]
          [media-post-panel posts]
          [:div {:id "media-posts"
                 :class "flexrow-wrap w-100 mt1"}
           (doall
             (for [post @posts
                   :let [post-id (-> post :sys :id)
                         tags (set (-> post :tags))
                         type (-> post :sys :contentTypeId)
                         index (.indexOf @posts post)]
                   :when (or (nil? @filter-tag)
                             (contains? tags @filter-tag))]
               (case type
                 "manyImagePost" ^{:key post-id}[media-thumb/image-gallery post-id post index]
                 "singleImage" ^{:key post-id}[media-thumb/single-image post-id post index]
                 "video" ^{:key post-id}[media-thumb/video post-id post index])))]]]]]
      [loading-component])))
