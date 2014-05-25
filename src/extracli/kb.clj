(ns extracli.kb
  (:require [clojure.set :refer :all]) )



; (new-kb)
; (new-kb {1 {:isa :concept}, 2 {:isa :vector}})
(defn new-kb
  "returns a new knowledge base with INIT as initial concepts if provided.
INIT is a map of concepts, where keys are numbers (ids) and values are map of properties."
  ([]
   {0 {:isa ::KB, :index-next-concept 1}} )
  ([INIT]
   (assoc INIT 0 {:isa ::KB, :index-next-concept (inc (apply max (keys INIT)))}) ))


; (exist? (new-kb {1 {:isa :class, :pp1 1, :pp2 "pp2", :pp3 [1 2], :pp4 :other-property}}) {:isa :class, :pp1 1, :pp2 "pp2", :pp3 [1 2]})
(defn exist?
  "returns true iff it exists an object in knowledge base KB with properties PPs"
  [KB PPs]
  (some (fn [C] (subset? (set PPs) (set C)))
        (vals KB) ))


; (get-by-properties (new-kb {1 {:isa :class, :pp1 1, :pp2 "pp2", :pp3 [1 2], :pp4 :other-property}}) {:isa :class, :pp1 1, :pp2 "pp2", :pp3 [1 2]})
(defn get-by-properties
  "returns the id of an object in knowledge base KB matching properties PPs"
  [KB PPs]
  (let [match?       (fn [[C_ID C_PPs]] (subset? (set PPs) (set C_PPs)))
        [C_ID C_PPs] (first (filter match? KB))]
    C_ID ))


; (add-concepts (new-kb) 2)
(defn add-concepts
  "return a knowledge base and NB new ids. The knowledge base is the knowledge base KB0 where NB empty concepts have been added."
  [KB0 NB]
  (let [idx_pre  (get-in KB0 [0 :index-next-concept])
        idx_post (+ idx_pre NB)
        ids      (range idx_pre idx_post)
        KB1      (assoc-in KB0 [0 :index-next-concept] idx_post)
        KB_post  (reduce (fn [KB IDX] (assoc KB IDX {})) KB1 ids)]
    (cons KB_post ids) ))


; (add-properties (new-kb {1 {}}) 1 {:pp1 1})
(defn add-properties
  "adds properties to an existing concept."
  [KB0 & C_PPs]
  (reduce (fn [KB [idC PPs]]
            (let [C_pre  (get KB idC)
                  C_post (merge C_pre PPs)]
              (assoc KB idC C_post) ))
          KB0 (partition 2 C_PPs)) )



