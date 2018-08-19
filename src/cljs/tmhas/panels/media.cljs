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
         [[:div {:class "media-thumb w-100"
                 :style {:text-align "left"}}
           [:div {:class "w-100 header-image"}]
           [:div {:class "ph3 ph4-ns pv2 pv3-ns"}
            [:p {:class "b mt2 mb3 f3-ns lh-title"} "Join Warm Cookies of the Revolution for the opening of THIS MACHINE HAS A SOUL, an art installation that explores creative responses to obstacles that residents face while navigating their home, their neighborhood, and their government. It’s weird. And fun. And means a lot to a lot of people..."]
            [:p "It includes Hot Wheels, a museum of repeated history, a resurrected 1953 Chevy Bel Air, a bus stop, paintings of humans, a closet of souls on video, many murals, free trees, and it is wild!"]
            [:p "This installation is part of our larger project and is inspired by the PB processes as well as by interviews with participants, neighborhood residents, history, artists and activists."]
            [:p [:b "Featuring work by the following artists and residents: "] "BirdSeed Collective and Anthony Garcia Sr., Buntport Theater, Silas Emanon Jolt from Guerrilla Garden, Lockerpartners, mmmanyfold, Rochelle Johnson, Elvis Nunez and Manuel Chavez, Incite Colorado, Danza Guadalupana Matachina Tlaxcalteca, Warm Cookies of the Revolution, Maria Carrillo, Lupe Carrillo, and Kerrie Joy."
             [:br] [:b "On camera: "] "Candi CdeBaca, David Torres, Tony Garcia, Candace L. Johnson, Brande Micheau, Nola Miguel, Alma Urbano-Torres, Angelina Torres."]
            [:p [:b "Location: "] "The installation is located in and around the detached garage at "
             [:a {:href "https://www.google.com/maps/search/4335%20Thompson%20Ct,%20Denver,%20CO%2080216,%20USA"}
              "4335 Thompson Court"]
             ". It is wheelchair accessible. The garage can be accessed via the alley between Elizabeth Street and Thompson Court. Please note that there are no bathrooms available at the installation location."]
            [:p [:b "Parking: "] "Street parking is available around Dunham Park at East 44th Avenue and Thompson Court or the fenced off property on Elizabeth at 44th. Please park at one of these locations and walk to the installation to ensure that residents’ ability to park in their neighborhood is not disrupted."]
            [:p [:b "Exhibition Hours"]
             [:br] "August 16, 17, 20 || 4:30-8:30pm"
             [:br] "August 18, 19 || 2-7pm"]
            [:p.i "All materials in the exhibition are in English and Spanish."
             [:br] "(Live Spanish interpretation available as well on Saturday, August 18 from 2-7pm.)"]
            [:p.b "Opening reception on August 16th with music from Molina Speaks, Ill Se7en, and Roots, Rice & Beans!"]]]
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
