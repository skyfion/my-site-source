(ns my-site.core
    (:require [reagent.core :as r]))

(enable-console-print!)

(defn current-page []
  [:div "test"])

(defn mountit []
  (r/render [current-page] (.getElementById js/document "app")))

(mountit)