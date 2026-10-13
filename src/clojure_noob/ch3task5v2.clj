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

(radial-matching-part {:name "left-dfgasdfsdf" :size 12})
