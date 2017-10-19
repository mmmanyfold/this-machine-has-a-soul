(ns tmhas.panels.media-post
  (:require [re-frame.core :as re-frame]
            [re-com.core :as re-com]
            [re-com.modal-panel :refer [modal-panel-args-desc]]
            [reagent.core :as reagent]
            [tmhas.components.common :refer [showdown]]))

(defn media-post-panel []
  (let [show? (reagent/atom true)]
    (fn []
      (when @show?
        [re-com/modal-panel
         :backdrop-on-click #(reset! show? false)
         :wrap-nicely? false
         :style {:overflow-y "scroll"}
         :child [re-com/v-box
                 :class "w-100 pa4-ns"
                 :children [[:div.media-post
                             [:i {:class "fa fa-times-circle f2"
                                  :aria-hidden true
                                  :on-click #(reset! show? false)}]
                             [:img {:class "mb1"
                                    :src "../img/computer2.gif"}]
                             [:h2 {:class "mb0 mh1 mt2 f2 f3-ns"}
                                  "Case Studies"]
                             [:div {:class "metadata f5"}
                               [:span "10/7/17"][:span "•"]
                               [:span "#Cole"] [:span "#Art"] [:span "#Process"]
                               [:p.ma1 "Full text from post text or image caption..."]]]]]]))))
