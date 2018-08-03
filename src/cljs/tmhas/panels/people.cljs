(ns tmhas.panels.people
  (:require [re-com.core :as rc]
            [re-frame.core :as rf]
            [tmhas.components.person :refer [person]]
            [tmhas.components.loading :refer [loading-component]]))

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
		},
		personEvaluators {
      name, image {
        url
      }, bio
		},
		personAurarias {
      name, image {
        url
      }, bio
		}
	}")

(defn people-panel []
  (let [db-key :people-panel]                              ;; 0. declare unique db-key
    (rf/reg-sub db-key #(db-key %))                       ;; 1. register subscriber db-key
    (rf/dispatch [:get-contentful-data db-key query :people]) ;; 2. retrieve contentful data & pass key for assoc in db

    (if-let [{:keys [personArtists personProjectBelays personAurarias personEvaluators
                     personProjectVoyces personWcors]} @(rf/subscribe [db-key])]
      [rc/v-box
       :align :end
       :class "people-panel"
       :children [;[:h1 {:id "auraria-section"
                  ;      :class "w-100 w-75-ns ml4-ns"
                  ; "Auraria PB Initiative"

                  ;(for [datum personAurarias]
                  ;  ^{:key (gensym "person-")}
                  ;  [person datum]


                  [:h1 {:id "project-voyce-section"
                        :class "w-100 w-75-ns ml4-ns"}
                   "Project VOYCE"]

                  (for [datum personProjectVoyces]
                       ^{:key (gensym "person-")}
                       [person datum])


                  [:h1 {:id "project-belay-section"
                        :class "w-100 w-75-ns ml4-ns"}
                   "Project Belay"]

                  (for [datum personProjectBelays]
                       ^{:key (gensym "person-")}
                       [person datum])


                  [:h1 {:id "the-artists-section"
                        :class "w-100 w-75-ns ml4-ns"}
                   "The Artists"]

                  (for [datum personArtists]
                       ^{:key (gensym "person-")}
                       [person datum])


                  [:h1 {:id "wcr-section"
                        :class "w-100 w-75-ns ml4-ns"}
                   "Warm Cookies of the Revolution"]

                  (for [datum personWcors]
                       ^{:key (gensym "person-")}
                       [person datum])


                  [:h1 {:id "evaluators-section"
                        :class "w-100 w-75-ns ml4-ns"}
                   "Evaluators"]

                  (for [datum personEvaluators]
                    ^{:key (gensym "person-")}
                    [person datum])]]

      [loading-component])))
