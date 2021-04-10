(ns demo.views
  (:require
   [re-frame.core :as rf]
   [clojure.string :as str]
   [demo.subs :as subs]
   [demo.events :as events]))

(defonce do-timer (js/setInterval #(rf/dispatch [::events/change-date (js/Date.)]) 1000))

(defn clock
  [color]
  (let [date @(rf/subscribe [::subs/date])]
    [:div {:style {:color color}} (-> date .toTimeString (str/split " ") first)]))

(defn main-panel []
  (let [name @(rf/subscribe [::subs/name])
        color @(rf/subscribe [::subs/color])]
    [:div
     [:h1 {:style {:color color}} "Hello from " name]
     [:input
      {:on-change #(rf/dispatch [::events/change-color (-> % .-target .-value)])  
       :type :text
       :value color}]
     [clock color]]))
