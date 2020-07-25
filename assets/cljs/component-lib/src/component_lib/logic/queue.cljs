(ns component-lib.logic.queue)


; This queue is not thread safe,
; as it doesn't use atomic functions to read 
; and then update the internal queue.
; In that sense, immutable queues may indeed be better, as 
; you are using a queue on a snapshot of the queue. But it also
; makes it useless for sharing.
(defn mk-queue []
  (let [head (atom (list))
        tail (atom (list))
        is-empty? (fn []
                (and (empty? @head) (empty? @tail))
                )
        peek! (fn []
               (cond
                 (is-empty?) nil
                 (empty? @head)
                 (do
                   (reset! head @tail)
                   (reset! tail (list))
                   (recur)
                   )
                 :else
                 (let [val (first @head)]
                   val
                   )
                 )
               )
        ]
    {:enqueue! (fn [val]
                (swap! tail #(conj % val))
                )
     :dequeue! (fn []
                 (let [val (peek!)]
                    ; peek will ensure that tail refills head if needed.
                    (swap! head #(into (list) (rest %)))
                   )
                 )
     :peek peek!
     :is-empty? is-empty?
     }
    )
  )
