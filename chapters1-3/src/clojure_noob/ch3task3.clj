(defn dec-maker
  [dec-by]
  (#(- % dec-by))
  (def dec9 (dec-maker 9)))
(dec9 111)
