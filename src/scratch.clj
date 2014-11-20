(ns scratch
  (require [clojure.pprint :as pp]) )

(def +benelux-client-v+
  [:aca :act :aed :avl :hvi :ika :ovi :pbe :pev :pnp :viv] )


; ===========================================================================================

(defn out-def [TYPE-v]
  (let [FLUX-v (symbol "FLUX-v")
        C      (symbol "C")]
    `(fn [~FLUX-v]
       (pp/cl-format true
                     "~&~:{ECL-~:@(~a~)-~:@(~a~)~:^, ~}."
                     (mapcat (fn [~C] (list ~@(for [T TYPE-v] `[(name ~C) (name ~T)])))
                             ~FLUX-v )))))

(comment
  (clojure.pprint/pprint (out-def [:e :i]))

  ((eval (out-def [:e :i])) [:mae :map])

  (defn out [TYPE-v] (eval (out-def TYPE-v)))

  ((out [:e :i]) [:mae :map])
)

; ===========================================================================================

; (out2 [:e :i] [:mae :map])
(defn out2 [TYPE-v FLUX-v]
  (pp/cl-format true
              "~&~:{ECL_~:@(~a~)_~:@(~a~)~:^, ~}."
              (for [F FLUX-v, T TYPE-v] [(name F) (name T)]) ))


; ===========================================================================================

; ((out3 [:e :i]) [:mae :map])
(defn out3 [TYPE-v]
  (fn [FLUX-v] (out2 TYPE-v FLUX-v)) )


; ===========================================================================================


(defn mono2many [MONO MANY< >MANY]
  {:data-tx (fn [L] (for [X L] ((get MONO :data-tx) X)))
   :format  (str MANY< (get MONO :format) >MANY)
   }
  )

(defn final [S DF]
"create the clojure function which outputs on stream S arguments ARGS formated by data-format DF"
  (fn [ARGS] (pp/cl-format S (get DF :format) ((get DF :data-tx) ARGS))) )


(def +format-vtom+
  {:data-tx  (fn [[F T]] [(name F) (name T)])
   :format   "~{ECL_~:@(~a~)_~:@(~a~)~}" })

(defn out2b [TYPE-v FLUX-v]
  ((final true (mono2many +format-vtom+ "~&~{" "~^, ~}."))
    (for [F FLUX-v, T TYPE-v] [F, T]) ))


(comment
  (out2b [:e :i] [:mae :map])
  (out2b [:e :i] +benelux-client-v+)
  (out2b [:c :d :m] [:gmo])

  )

























