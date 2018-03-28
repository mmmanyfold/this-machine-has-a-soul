(ns tmhas.routes
  (:require-macros [secretary.core :refer [defroute]])
  (:import goog.History)
  (:require [secretary.core :as secretary]
            [goog.events :as events]
            [goog.history.EventType :as EventType]
            [re-frame.core :as rf]))

(defn hook-browser-navigation! []
  (doto (History.)
    (events/listen
     EventType/NAVIGATE
     (fn [event]
       (secretary/dispatch! (.-token event))))
    (.setEnabled true)))

(defn app-routes []
  (secretary/set-config! :prefix "#")

  ;; --------------------
  ;; define routes here

  (defroute "/" []
            (rf/dispatch [:set-active-panel :media-panel]))

  (defroute "/post/:post" {:as params}
            (rf/dispatch [:set-active-panel :media-panel (:post params)]))

  (defroute "/about" []
            (rf/dispatch [:set-active-panel :about-panel]))

  (defroute "/people" []
            (rf/dispatch [:set-active-panel :people-panel]))

  (defroute "/events" []
            (rf/dispatch [:set-active-panel :events-panel]))

  (defroute "/404" []
            (rf/dispatch [:set-active-panel :404]))

  ;; TODO: fix this
  ;(defroute #"/[a-z]+" []
  ;          ;; section nav links mutate the url so we need this catch all for when a user returns to the site
  ;          ;; after clicking on an section navigation link such as #contact-section
  ;          (rf/dispatch [:set-active-panel :latest-panel]))


  ;; --------------------
  (hook-browser-navigation!))
