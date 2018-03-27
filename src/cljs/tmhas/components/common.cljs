(ns tmhas.components.common
  (:require [re-com.core :as rc]
            [cljsjs.showdown]))

(defn link [label to]
      [rc/hyperlink-href
       :label label
       :href (str "#" to)])

;; for parsing markdown with showdown
(def showdown (js/showdown.Converter.))

;; for embedding vimeo or yt videos from video url
(defn embed-video [url]
  (cond (re-find #"https://vimeo.com/" url)
        (str "https://player.vimeo.com/video/" (re-find #"\d+" url) "?color=8e4f78&title=0&byline=0&portrait=0&badge=0")

        (re-find #"https://www.youtube.com/watch?v=" url)
        (if (re-find #"=(.*)&" url)
            (str "https://www.youtube.com/embed/" (second (re-find #"=(.*)&" url)) "?rel=0&amp;showinfo=0")
            (str "https://www.youtube.com/embed/" (second (re-find #"=(.*)" url)) "?rel=0&amp;showinfo=0"))

        (re-find #"https://youtu.be/" url)
        (str "https://www.youtube.com/embed/" (second (re-find #"youtu.be/([\s\S]*)" url)) "?rel=0&amp;showinfo=0")))
