(ns demo.subs
  (:require
   [re-frame.core :as rf]))

(rf/reg-sub
 ::name
 (fn [db]
   (:name db)))

(rf/reg-sub
 ::color
 (fn [db]
   (:color db)))

(rf/reg-sub
 ::date
 (fn [db]
   (:date db)))
