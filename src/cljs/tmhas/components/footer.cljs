(ns tmhas.components.footer
  (:require [re-com.core :as rc]
            [re-frame.core :as rf]))

(defn footer []
  [:div {:class "footer bt mh3 mh4-ns ph1 pv3 flex justify-between"}
   [:div
    [:a.social-link {:href "https://www.facebook.com/warmcookiesoftherevolution"}
     [:img {:src "icons/fb.svg"}]]
    [:a.social-link {:href "https://www.instagram.com/warmcookiesrevolution/"}
     [:img {:src "icons/ig.svg"}]]
    [:a.social-link {:href "https://vimeo.com/user24711262"}
     [:img {:src "icons/vimeo.svg"}]]]
   [:div
    [:span {:on-click #(js/window.showMailingPopUp)
            :class "f4 b ttu pointer pb1"
            :style {:letter-spacing "1px"}}
       "Join Mailing List >>"]]])
