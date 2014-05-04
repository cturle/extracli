(ns extracli.collection-test
  (:require [clojure.test :refer :all]
            [extracli.collection :refer :all]))

(deftest collection
  (let [KB {:isa {:isa :Relation}
            2 {:isa :extracli.collection/Collection, :value [:isa 2]}
            3 {:isa :none}
            4 {:isa :none}
           }]
    (testing "Mapping=>Rule-1"
      (let [KB2 (Mapping=>Rule-1 KB :isa 2 3 4)]
        (is (= {:isa :extracli.collection/Mapping
                :kb-relation :isa
                :input       2
                :output      4}
               (get KB2 3)))
        (is (= {:isa :extracli.collection/Collection
                :value [:Relation :extracli.collection/Collection]}
               (get KB2 4)))))))



