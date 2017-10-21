(ns tmhas.core
  (:require [reagent.core :as reagent]
            [re-frame.core :as rf]
            [re-frisk.core :refer [enable-re-frisk!]]
            [tmhas.events]
            [tmhas.subs]
            [tmhas.routes :as routes]
            [tmhas.views :as views]
            [tmhas.config :as config]))


(defn dev-setup []
  (when config/debug?
    (enable-console-print!)
    (enable-re-frisk!)
    (println "dev mode")))

(defn mount-root []
  (rf/clear-subscription-cache!)
  (reagent/render [views/main-panel]
                  (.getElementById js/document "app")))

(defn ^:export init []
  (routes/app-routes)
  (rf/dispatch-sync [:initialize-db]
    (dev-setup))
  (mount-root))
