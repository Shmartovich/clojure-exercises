(defn mapset
  [operator vector]
  (set (map operator vector))
 )
(mapset inc [1 1 2 2])
