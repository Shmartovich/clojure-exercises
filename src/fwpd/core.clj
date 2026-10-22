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

(first mapped-csv-file)

;(glitter-filter 3 (mapify parsed-csv-file))

;chapter 4 task 1
;changing the return value to a list of names

(defn glitter-filter-as-list [minimum-glitter records]
  "Turn the result of your glitter filter into a list of names."
  (reduce (fn [result map-entry]
            (conj result (:name map-entry)))
          '()
          (glitter-filter minimum-glitter records)))

(glitter-filter-as-list 0 (mapify parsed-csv-file))
;;ch4t2
(defn append-suspect [name glitter-index list-suspects]
  "Write a function, append, which will append a new suspect to your list of suspects."
  (conj list-suspects (conj [] name glitter-index)))
(append-suspect "Ciel Phantomhive" "1" lazyseq-from-csv-file)
