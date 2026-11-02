(ns chapter5.core
  (:gen-class))

(defn tri*
  "generate lazy seq of triangular numbers"
  ([] (tri* 0 1))
  ([sum n]
   (let [new-sum (+ sum n)]
     (cons new-sum (lazy-seq (tri* new-sum (inc n)))))))

(def tri (tri*))

(take 5 tri)

(defn triangular?
  [n]
  (= n (last (take-while #(>= n %) tri))))

(triangular? 5)
(triangular? 6)


(defn factor-x-gen
  ([] (factor-x-gen 0 2))
  ([sum x]
   (cons sum (lazy-seq (factor-x-gen (+ sum x) x)))))

(take 1 (factor-x-gen))

(defn row-tri
  [n]
  (last (take n tri)))

(defn row-num
  [pos]
  (inc (count (take-while #(> pos %) tri))))

(defn connect
  [board max-pos pos neighbor destination]
  (if (<= destination max-pos)
    (reduce (fn [new-board [p1 p2]]))
    )

  )
