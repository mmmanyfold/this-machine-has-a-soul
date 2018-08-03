(ns tmhas.components.footer
  (:require [re-com.core :as rc]
            [re-frame.core :as rf]))

(defn footer []
  [:div {:class "footer bt mh3 mh4-ns ph1 pv3 tr"}
   [:span {:on-click #(js/window.showMailingPopUp)
           :class "f4 b ttu pointer pb1"
           :style {:letter-spacing "1px"}}
      "Join Mailing List >>"]])
