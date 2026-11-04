(ns chapter5.core
  (:gen-class))

(defn tri*
  "generate lazy seq of triangular numbers"
  ([] (tri* 0 1))
  ([sum n]
   (let [new-sum (+ sum n)]
     (cons new-sum (lazy-seq (tri* new-sum (inc n)))))))

(def tri (tri*))

;;(take 5 tri)

(defn triangular?
  [n]
  (= n (last (take-while #(>= n %) tri))))

(triangular? 5)
(triangular? 6)


(defn factor-x-gen
  ([] (factor-x-gen 0 2))
  ([sum x]
   (cons sum (lazy-seq (factor-x-gen (+ sum x) x)))))

;;(take 1 (factor-x-gen))

(defn row-tri
  [n]
  (last (take n tri)))

(defn row-num
  [pos]
  (inc (count (take-while #(> pos %) tri))))

(defn connect
  [board max-pos pos neighbor destination]
  (if (<= destination max-pos)
    (reduce (fn [new-board [p1 p2]]
              (assoc-in new-board [p1 :connections p2] neighbor))
            board
            [[pos destination] [destination pos]])
    board))

(connect {} 15 1 2 4)


(defn connect-right
  [board max-pos pos]
  (let [neighbor (inc pos)
        destination (inc neighbor)]
    (if-not (or (triangular? neighbor) (triangular? pos))
      (connect board max-pos pos neighbor destination)
      board)))

(defn connect-down-left
  [board max-pos pos]
  (let [row (row-num pos)
        neighbor (+ row pos)
        destination (+ 1 row neighbor)]
    (connect board max-pos pos neighbor destination)))

(defn connect-down-right
  [board max-pos pos]
  (let [row (row-num pos)
        neighbor (+ 1 row pos)
        destination (+ 2 row neighbor)]
    (connect board max-pos pos neighbor destination)))

;; TODO understand the game

;; Task 1

(def character
  {:name "Smooches McCutes"
   :attributes {:intelligence 10
                :strength 4
                :dexterity 5}})
(def c-int (comp :intelligence :attributes))
(def c-str (comp :strength :attributes))
(def c-dex (comp :dexterity :attributes))

(c-int character)

(defn attr
  [key]
  (fn [character]
    (key (:attributes character)))
  )
(def c-int2 (attr :intelligence))
(c-int2 character)

;; Task 2
;; Implement the comp function.
#_(defn my-comp [& fns]
  (fn [& args]
    (if (= 1 (count fns))
      (apply (first fns) args)
      ((apply my-comp (drop-last fns)) (apply (last fns) args))
      )
    )
  )

;; Implement the comp function with loop n recur

(defn my-comp [& fns]
  (fn [& args]
    (loop [rest-fns fns
           new-args args]
      (if (empty? (rest rest-fns))
        (apply (first rest-fns) new-args)
        (recur (drop-last rest-fns) (list (apply (last rest-fns) new-args)))
        ))
    ))

;; Testing task 2
(def negative-quotient (my-comp - /))
(negative-quotient 8 3)  ;;=> -8/3

(def concat-and-reverse (my-comp (partial apply str) reverse str))
(concat-and-reverse "hello" "clojuredocs")

((my-comp str +) 8 8 8)
