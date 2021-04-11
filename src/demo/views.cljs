(ns demo.views
  (:require
   [re-frame.core :as rf]
   [clojure.string :as str]
   [demo.subs :as subs]
   [demo.events :as events]))

(defn clock
  []
  [:div 
   {:style {:color @(rf/subscribe [::subs/color])}} 
   (-> @(rf/subscribe [::subs/date]) .toTimeString (str/split #" ") first)])

(defn color-input
  []
  [:input
   {:on-change #(rf/dispatch [::events/change-color (.-value (.-target %))])
    :type :text
    :value @(rf/subscribe [::subs/color])}])

(defn main-panel []
  (let [name @(rf/subscribe [::subs/name])]
    [:div
     [:h1 "Hello from " name]
     [color-input]
     [clock]]))

(defonce do-timer (js/setInterval #(rf/dispatch [::events/change-date (js/Date.)]) 1000))