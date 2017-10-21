(ns tmhas.panels.people
  (:require [re-com.core :as rc]
            [re-frame.core :as rf]
            [tmhas.components.person :refer [person]]))

(def query
  "{
		personArtists {
			name, image {
				url
			}, bio
		},
		personProjectBelays {
			name, image {
				url
			}, bio
		},
		personProjectVoyces {
			name, image {
				url
			}, bio
		},
		personWcors {
			name, image {
				url
			}, bio
		}
	}")

(defn people-panel []
      (let [db-key :people-panel]                              ;; 0. declare unique db-key
           (rf/reg-sub db-key #(db-key %))                       ;; 1. register subscriber db-key
           (rf/dispatch [:get-contentful-data db-key query :people]) ;; 2. retrieve contentful data & pass key for assoc in db

           (let [{:keys [personArtists personProjectBelays
                         personProjectVoyces personWcors]} @(rf/subscribe [db-key])]

                [rc/v-box
                 :align :end
                 :class "people-panel"
                 :children [[:h1 {:class "w-100 w-75-ns ml4-ns bb bw1"}
                             "Project VOYCE"]

                            (for [datum personProjectVoyces]
                                 ^{:key (gensym "person-")}
                                 [person datum])


                            [:h1 {:class "w-100 w-75-ns ml4-ns bb bw1"}
                             "Project Belay"]

                            (for [datum personProjectBelays]
                                 ^{:key (gensym "person-")}
                                 [person datum])

                            [:h1 {:class "w-100 w-75-ns ml4-ns bb bw1"}
                             "The Artists"]

                            (for [datum personArtists]
                                 ^{:key (gensym "person-")}
                                 [person datum])

                            [:h1 {:class "w-100 w-75-ns ml4-ns bb bw1"}
                             "Warm Cookies of the Revolution"]

                            (for [datum personWcors]
                                 ^{:key (gensym "person-")}
                                 [person datum])]])))
