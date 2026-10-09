(ns clojure-noob.core
  (:gen-class))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "Hello, teapot"))
(println "Cleanliness is next to godliness")

(defn train
[]
(println "choo choo"))

(defn tra
[]
(println "choo choo"))

(+ 123 1)

(if false
  "true"
  "false")

(if false
"true"
)

(if true
  (do (println "Success") "Bt zeuts hamer")
  (do (println ("Failure")) "by aquamans'"))

(when true
  (println "succdess")
  "abradabra")

(nil? (if true
        "dasd"))

(def failed-vector ["asdas" "dasda" "aas"])

failed-vector

(def severity :mild)
(def error-msg :disaster!!!)
(if (= severity :mild)
  (def error-msg (str error-msg "mild disaster"))
  (def error-msg (str error-msg "huge disaster!!!")))
error-msg
severity

(defn error-message
  [severity])
(error-message :mild)

"asdasd"
"\"he who must not be name\""

(def mymap{:first-name "sa"
     :last-name "fa"})
(println mymap)

(if false
  (println "true")
  (println "false")
  )
(if true
  (do(println "do1") (println "do11"))
  (do(println "do2") (println "do22"))
  )

(nil? (when true
        "returned bcs true")
)

(or false  "true really")
(and nil false)

{:first-name "charlie"
 :last-name "fadsfacmc"}

{"string-key" +}


(def map1 (hash-map :a 1 :b 2 :c {:a {:a "ho ho"}}))
(get map1 :a)
(get {:a 123 :b 342} :b)
(get map1 :c "nopthing dound")
(get-in map1 [:c :a :a])

(:z map1 "no z key")

;; commentary
(def array [1 4 6 123])
(get array 3)
(vector "ass" "butt" "5th point")

(conj array 321 6 4 1)

(def list '(1 22 3 4))
(get list 1) ;;->nil
(nth list 1)

(map inc [0 14 4 9])

(contains? (hash-set false nil) nil)

(defn codger-com
  [kid]
  (str "get off, " kid))
(defn codger
  [& kids]
  (map codger-com kids))
(codger "timmy" "Tommy")

(defn fun1
  [arg]
  (inc arg))
(defn fun2
  [& args]
  (map fun1 args))
(fun2 3 4 5 0)



(defn inc-maker
  "custom incrementor"
  [inc-by]
  )

;;1

;;2
(defn hundread-adder
  [integer]
  (+ integer 100))

(hundread-adder 123)
;;3
(defn dec-maker
  [subtractor]
  ())

(def dec-maker9 (dec-maker 9))
;(dec-maker9 10)


(defn kick
  ([]
   "karate kick")
  ([kick-name]
   (str kick-name " kick")))

(kick "butt")

(defn double-foo
  [arg]
  (* 2 arg)
  )

(defn foo2
  [& args]
  map double-foo args)

(foo2 [1 2 3])

(defn destruct1
  [[first second & other]]
  (println (str "this is first " first))
  (println (str "this is second " second))
  (println (str "this is other " (clojure.string/join ", " other))))
(destruct1 ["1" "2" "3" "4"])


(defn chooser
  [[first-choice second-choice & unimportant-choices]]
  (println (str "Your first choice is: " first-choice))
  (println (str "Your second choice is: " second-choice))
  (println (str "We're ignoring the rest of your choices. "
                "Here they are in case you need to cry over them: "
                (clojure.string/join ", " unimportant-choices))))
(chooser [1 2 3 4 5])

(defn location
  [{lat :lat lng :lng}]
  (println (str lat " lattitude"))
  (println (str lng " longtitude")))
(location {:latdsa 12 :lng 15})

(defn receive-treasure-location
  [{:keys [lat lng] :as treasure-location}]
  (println (str "Treasure lat: " lat))
  (println (str "Treasure lng: " lng))
  )

(map (fn [name] (str "Hi " name))
     ["Mark" "Timmy"])

((fn [integ] (+ integ 15)) 10)


(#(* % 3) 12)


(def asym-hobbit-body-parts [{:name "head" :size 3}
                             {:name "left-eye" :size 1}
                             {:name "left-ear" :size 1}
                             {:name "mouth" :size 1}
                             {:name "nose" :size 1}
                             {:name "neck" :size 2}
                             {:name "left-shoulder" :size 3}
                             {:name "left-upper-arm" :size 3}
                             {:name "chest" :size 10}
                             {:name "back" :size 10}
                             {:name "left-forearm" :size 3}
                             {:name "abdomen" :size 6}
                             {:name "left-kidney" :size 1}
                             {:name "left-hand" :size 2}
                             {:name "left-knee" :size 2}
                             {:name "left-thigh" :size 4}
                             {:name "left-lower-leg" :size 3}
                             {:name "left-achilles" :size 1}
                             {:name "left-foot" :size 2}])
(loop [iteration 0]
  (println "iteration " iteration)
  (if (> iteration 3)
    "ended 4"
    (recur (inc iteration)))
  )

(re-find #"^left-" "cleft-hand")
