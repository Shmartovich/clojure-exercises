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
                             {:name "left-foot" :size 2}]
  )
(defn matching-part
  "Changes value of :name left to right"
  [part]
  {:name (clojure.string/replace (:name part "no name") #"^left-" "right-")
   :size (:size part)}
  )
(matching-part {:name "left-eye"})

(defn symmetrise-body-parts
  "Expects a sequence of maps that have :name :size"
  [body-parts]
  (loop [remaining-asym-parts body-parts
         final-body-parts []]
    (if (empty? remaining-asym-parts)
      final-body-parts
      (let [[part & remaining] remaining-asym-parts]
        (recur remaining
               (into final-body-parts
                     (set [part (matching-part part)]))))))
  )
(symmetrise-body-parts asym-hobbit-body-parts)





(defn better-symmetrize-body-parts
  "Expects a seq of maps that have a :name and :size"
  [asym-body-parts]
  (reduce (fn [final-body-parts part]
            (into final-body-parts (set [part (matching-part part)])))
          []
          asym-body-parts))
(better-symmetrize-body-parts asym-hobbit-body-parts)



(defn radial-matching-part
  [part]
  (loop [five-of-part []
         number 1]
    (if (= number 5)
      five-of-part
      (do
        (into five-of-part {:name (clojure.string/replace (:name part) #"^left-" (str "#" number " "))
                            :size (:size part)})
        (recur five-of-part (inc number))))))
(radial-matching-part {:name "left-fadsf"
                       :size 1
                       })





(defn symmetrize-alien-body-parts
  "Given only type of body parts in form of sequence of maps returns seq of maps with 5 body parts. Everything what is left- considered to have 5 separate parts"
  [body-parts]
  (loop [remaining-parts body-parts
         final-body []]
    (if (empty? remaining-parts)
      final-body
      (let [[part & remains] body-parts]
        (recur remains
               (into final-body (radial-matching-part part)))
        )
      )
    )
  )

  (symmetrize-alien-body-parts  asym-hobbit-body-parts)
