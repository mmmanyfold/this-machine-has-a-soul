(ns tmhas.components.tags
  (:require-macros [purnam.core :refer [!>]])
  (:require cljsjs.jquery
            [re-frame.core :as rf]
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

(def db-key :tag-component)                                 ;; 0. declare unique db-key

(rf/reg-sub db-key #(db-key %))                             ;; 1. register subscriber for db-key

(def tags-&-meta (rf/subscribe [db-key]))

(def active-panel? (rf/subscribe [:active-panel]))

(defn re-map
  "A la Processing: https://processing.org/reference/map_.html
  -- Re-maps a number from one range to another"
  [val low1 high1 low2 high2]
  (/ (* (+ low2 (- val low1))
        (- high2 low2))
     (- high1 low1)))

(defn map-values
  "http://dacamo76.com/blog/2014/11/28/updating-map-values-in-clojure
  (map-values {:a 1 :b 2 :c 0} [:a :b] inc) => {:a 2 :b 3 :c 0}"
  [m keys f & args]
  (reduce #(apply update-in %1 [%2] f args) m keys))

(def default-props {:class "section-anchor"
                    :on-click (fn [e]
                                (when-let [hash (-> e .-target .-hash)]
                                  (when-let [top (some-> (js/$ hash) (.offset) .-top)]
                                    (-> (js/$ "html, body")
                                        (.animate #js {:scrollTop (- top 100)})))))})

(defn about-section-nav []
  [:ul.list.pl0
   [:li.tl [:a (assoc default-props :href "#about-tmhas-section") "About TMHAS"]]
   [:li.tl [:a (assoc default-props :href "#contact-section") "Contact"]]])

(defn people-section-nav []
  [:ul.list.pl0
   [:li.tl [:a (assoc default-props :href "#project-voyce-section") "Project VOYCE"]]
   [:li.tl [:a (assoc default-props :href "#project-belay-section") "Project Belay"]]
   [:li.tl [:a (assoc default-props :href "#the-artists-section") "The Artists"]]
   [:li.tl [:a (assoc default-props :href "#wcr-section") "Warm Cookies of the Revolution"]]])

(defn tags
  "multi purpose tag filter component"
  []
  (reagent/create-class
    {:component-will-mount
     #(rf/dispatch [:get-contentful-data db-key query :media])
     :reagent-render
     (fn []
       (case @active-panel?
         nil ;; default
         [:div "loading..."]
         :about-panel
         [about-section-nav]
         :people-panel
         [people-section-nav]
         :events-panel
         [:div]
         :media-panel
         [:div#tags
          (if-not (empty? @tags-&-meta)
            (doall
              (for [c (keys @tags-&-meta)
                    :let [coll (c @tags-&-meta)
                          tags (->> (map #(:tags %) coll)
                                    (remove nil?)
                                    flatten)
                          freq (frequencies tags)
                          ks (apply vector (keys freq))
                          r-freq (map-values freq ks #(re-map % 1 100 1 10))
                          tag-set (set tags)]
                    :when (seq (c @tags-&-meta))]
                ^{:key (gensym "-tag-list")}
                [:ul.list.pl0
                 (map (fn [t]
                        ^{:key (gensym "-tag-item")}
                        [:li
                         [:span.tags
                          [:a {:style    {:font-size (str (* 8 (get r-freq t)) "rem")}
                               :on-click #(rf/dispatch [:set-filter-tag t])}
                           (str "#" t)]]])
                      tag-set)]))
            [:p "loading.."])]))}))
