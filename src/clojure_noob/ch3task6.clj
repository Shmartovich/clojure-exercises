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
  ([part radial-parts]
   (if (= (:name part)(clojure.string/replace (:name part) #"^left-" (str 1 "_")))
     (do (println "no need for" (:name part))
         [part])
     (radial-matching-part part 1 radial-parts)))

  ([part iteration-num radial-parts]
   (let [new-part
         {:name (clojure.string/replace
                 (:name part)
                 #"^left-"
                 (str iteration-num "_"))
          :size (:size part)}]
     (if (= iteration-num radial-parts)
       [new-part]
       (into [new-part]
             (radial-matching-part part (inc iteration-num) radial-parts))))))

(defn radial-symmetrize-alien
  [body-parts radial-parts]
  (reduce
   (fn [result part]
     (into result (radial-matching-part part radial-parts)))
   []
   body-parts))

(radial-symmetrize-alien asym-alien-body-parts 8)
