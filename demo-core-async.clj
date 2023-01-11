(ns demo-core-async
  (:require [clojure.core.async :as async]))


; SLIDING BUFFER FIFO
;(def sliding-ch (async/chan (async/sliding-buffer 5)))
;
;(async/go
;  (dotimes [i 10]
;    (async/>! sliding-ch i)))
;
;(async/go
;  (dotimes [i 10]
;    (println (str "Taking " (async/<! sliding-ch)))))


;;; DROPPING BUFFER LIFO
;(def dropping-ch (async/chan (async/dropping-buffer 5)))
;
;(async/go
;  (dotimes [i 10]
;    (async/>! dropping-ch i)))
;
;(async/go
;  (dotimes [i 10]
;    (println (str "Taking " (async/<! dropping-ch)))))


;;; BACKPRESSURE
(def back-pressure-ch (async/chan 5))

(async/thread
  (dotimes [i 10]
    (async/>!! back-pressure-ch i)
    (println (str "Putting " i))))

(Thread/sleep 2000)

(dotimes [i 10]
  (Thread/sleep 200)
  (println (str "Taking " (async/<!! back-pressure-ch))))
