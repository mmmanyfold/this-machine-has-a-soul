(ns tmhas.components.person
  (:require [re-com.core :as rc]
            [tmhas.components.common :refer [showdown]]))

(defn person [data]
      [:div {:class "flexrow-wrap w-100 mv3"}
         [:div {:class "w-100 w-25-ns tc"}
            [:div {:class "photo-container mb3"}
               [:div {:class "photo br-100"
                      :style {:background-image (str "url('" (-> data :image :url) "')")
                              :background-size "cover"}}]]]
         [rc/v-box
          :class "w-100 w-75-ns pl4-ns"
          :children [[:h2 {:class "mt0 f2 f3-ns b"}
                         (:name data)]
                     [:div {:class "markdown"
                            "dangerouslySetInnerHTML"
                            #js{:__html (.makeHtml showdown (:bio data))}}]]]])
