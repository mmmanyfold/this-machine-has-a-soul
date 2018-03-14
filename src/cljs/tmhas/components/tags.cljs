(ns tmhas.components.tags
  (:require [re-frame.core :as rf]
            [reagent.core :as reagent]))

(def query
  "{
    imageGalleryPosts {
      sys { id contentTypeId }
			tags
		},
    singleImageTextPosts {
      sys { id contentTypeId }
			tags
		},
    videoPosts {
      sys { id contentTypeId }
			tags
		}
	}")

(def db-key :tag-component) ;; 0. declare unique db-key

(rf/reg-sub db-key #(db-key %)) ;; 1. register subscriber for db-key

(def tags-&-meta (rf/subscribe [db-key]))

(defn tags []
  (reagent/create-class
    {:component-will-mount
     #(rf/dispatch [:get-contentful-data db-key query :media])
     :reagent-render
     (fn []
       [:div#tags
        (if-not (empty? @tags-&-meta)
          (doall
            (for [c (keys @tags-&-meta)
                  :let [coll (c @tags-&-meta)
                        tags (->> (map #(:tags %) coll)
                                  (remove nil?)
                                  flatten
                                  set)]
                  :when (seq (c @tags-&-meta))]
              (map (fn [t]
                     ^{:key (gensym "--tag")}
                     [:span.tag
                      [:a {:on-click #(rf/dispatch [:set-filter-tag t])}
                       (str "#" t)]])
                   tags)))
          [:p "loading.."])])}))

