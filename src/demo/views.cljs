(ns demo.views
  (:require
   [re-frame.core :as rf]
   [clojure.string :as str]
   [demo.subs :as subs]
   [demo.events :as events]))

(defn clock
  [color]
  [:div 
   {:style {:color color}} 
   (first (str/split (.toTimeString @(rf/subscribe [::subs/date])) #" "))])

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

(defonce do-timer (js/setInterval #(rf/dispatch [::events/change-date (js/Date.)]) 1000))
