(ns fwpd.core)
(def filename "suspects.csv")

#_(slurp filename)

(def vamp-keys [:name :glitter-index])
(defn str->int
  [str]
  (Integer. str))
(def conversions {:name identity
                  :glitter-index str->int})
(defn convert
  [vamp-key value]
  ((get conversions vamp-key) value))

;(convert :glitter-index "3")

(defn parse
  "Convert a CSV into rows of columns"
  [string]
  (map #(clojure.string/split % #",")
       (clojure.string/split string #"\n")))

(def lazyseq-from-csv-file (parse (slurp filename)))

(defn mapify
  "Return a seq of maps like {:name \"Edward Cullen\" :glitter-index 10}"
  [rows]
  (map (fn [unmapped-row]
         (reduce (fn [row-map [vamp-key value]]
                   (assoc row-map vamp-key (convert vamp-key value)))
                 {}
                 (map vector vamp-keys unmapped-row)))
       rows))

(defn glitter-filter
  [minimum-glitter records]
  (filter #(>= (:glitter-index %) minimum-glitter) records))

(def mapped-csv-file (mapify lazyseq-from-csv-file))

;(glitter-filter 3 (mapify parsed-csv-file))

;chapter 4 task 1
;changing the return value to a list of names

(defn glitter-filter-as-list [minimum-glitter records]
  "Turn the result of your glitter filter into a list of names."
  (reduce (fn [result map-entry]
            (conj result (:name map-entry)))
          '()
          (glitter-filter minimum-glitter records)))

;;ch4t2
(defn prepend-suspect [name glitter-index list-suspects]
  (conj list-suspects {:name name :glitter-index glitter-index}))

(defn prepend-suspectv2 [name glitter-index list-suspects]
  (cons {:name name :glitter-index glitter-index} list-suspects ))

(defn append-suspect [name glitter-index list-suspects]
  "Write a function, append, which will append a new suspect to your list of suspects."
  (concat list-suspects [{:name name :glitter-index glitter-index}]))

;;ch4t3
(defn validate
  "Checks that :name and :glitter-index are present when you append.
  The validate function should accept two arguments: a map of keywords to
  validating functions, similar to conversions, and the record to be validated."
  [map-of-validating-functions record]
  (if (or (nil? (get record :name)) (nil? (get record :glitter-index)))
    (println "something is missing, no append TODO")
    (do
      ((get map-of-validating-functions :name) (get record :name))
      ((get map-of-validating-functions :glitter-index) (get record :glitter-index)))))

;;ch4t4
(defn maps->csv-string
  "Function that will take your list of maps and convert it back to a CSV string. "
  [list-of-maps]
  (clojure.string/join "\n" (reduce
                             (fn [res map-entry]
                               (conj res (str (get map-entry :name) "," (get map-entry :glitter-index))))
                             []
                             list-of-maps)))
