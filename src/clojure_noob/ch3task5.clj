(def asym-alien-body-parts [{:name "head" :size 3}
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

(defn radial-matching-part
  ([part]
   (if (= (:name part)(clojure.string/replace (:name part) #"^left-" (str 1 "_")))
     (do (println "no need for" (:name part))
         [part])
     (radial-matching-part part 1)))

  ([part iteration-num]
   (let [new-part
         {:name (clojure.string/replace
                 (:name part)
                 #"^left-"
                 (str iteration-num "_"))
          :size (:size part)}]
     (if (= iteration-num 5)
       [new-part]
       (into [new-part]
             (radial-matching-part part (inc iteration-num)))))))

(defn symmetrize-alien
  [body-parts]
  (reduce (fn [result part]
            (into result (radial-matching-part part)))
          []
          body-parts))

(symmetrize-alien asym-alien-body-parts)
