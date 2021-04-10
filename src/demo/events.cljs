(ns demo.events
  (:require
   [re-frame.core :as rf]
   [demo.db :as db]
   [day8.re-frame.tracing :refer-macros [fn-traced]]))

(rf/reg-event-db
 ::initialize-db
 (fn-traced [_ _]
   db/default-db))

(rf/reg-event-db
 ::change-date
 (fn [db [_ new-date]]
   (assoc db :date new-date)))

(rf/reg-event-db
 ::change-color
 (fn [db [_ new-color]]
   (assoc db :color new-color)))