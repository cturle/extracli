(ns extracli.kb
  (:require [clojure.set :refer :all]) )


(defn exist?
  "returns true iff it exists an object in knowledge base KB1 with properties PPs"
  [KB PPs]
  (some (fn [C] (subset? (set PPs) (set C)))
        (vals KB) ))


