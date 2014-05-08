(ns extracli.collection-test
  (:require [clojure.test :refer :all]
            [extracli.collection :refer :all]))

(deftest collection
  (let [F     (fn [KB idX idY] (let [X (get KB idX)] (assoc KB idY {:value (inc (:value X))})))
        M-KB0 {1 {:isa :Action :in :?varX, :pre "todo", :out :?varY, :post "todo", :fn F},
               2 {}
               }
        KB0 {:isa {:isa :Relation}
             2 {:isa :extracli.collection/Collection, :value [:isa 2]}
             3 {}
             4 {}
             5 {:value 7}
             }]

    (testing "Mapping=>Rule-1"
      (let [KB1 (Mapping=>Rule-1 KB0 :isa 2 3 4)]
        (is (= {:isa :extracli.collection/Mapping
                :kb-relation :isa
                :input       2
                :output      4}
               (get KB1 3)))
        (is (= {:isa :extracli.collection/Collection
                :value [:Relation :extracli.collection/Collection]}
               (get KB1 4)))))

    (testing "Mapping=>Rule-2"
      (let [M-KB1 (Mapping=>Rule-2 M-KB0 1 2)
            A2    (get M-KB1 2)
            KB1   ((:fn A2) KB0 [5 3] [3 4]) ]
        (is (= :Action (get A2 :isa)))
        (is (= [:?varX] (get A2 :in)))
        (is (= "todo" (get A2 :pre)))
        (is (= [:?varY] (get A2 :out)))
        (is (= "todo" (get A2 :post)))
        (is (= 8 (get-in KB1 [3 :value])))
        (is (= 9 (get-in KB1 [4 :value]))) ))))



