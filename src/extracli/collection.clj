(ns extracli.collection)


; (Mapping=>Rule-1 {1 {:isa :none}, :isa {:isa :Relation}, 3 {:isa ::Collection, :value [:isa 3]}, 4 {:isa :none}} :isa 3 1 4)
(defn Mapping=>Rule-1
  "  in: [?R, ?C]
    pre: {?R {isa Relation}
          ?C {isa Collection, value [?V]}
          ?V {?R _} }
    out: [?M ?C2]
   post: {?M  {isa Mapping, kb-relation ?R, input ?C, output ?C2}
          ?C2 {isa Collection, value _} }"

  [KB ?R ?C ?M ?C2]
  (let [C (get KB ?C)]
    (assoc KB ?M  {:isa ::Mapping, :kb-relation ?R, :input ?C, :output ?C2}
              ?C2 {:isa ::Collection, :value (map #(get-in KB [%1 ?R]) (:value C))} ))
  )

(defn Mapping=>Rule-2
  "  in: ?A, ?R, [?I]
    pre: {?A :isa Action, :fn _, :in ?varX, :out ?varY, :post {:isa Constraint, :x ?varX, :kb-relation ?R, :y ?varY}}
    out: ?M, [?O]
   post: (?M :isa Mapping), (?M :kb-relation ?R), (?M :input [?I]), (?M :output [?O])"

[KB ?A]
  (let [A  (get KB ?A)
        FN (:fn A) ]
    (assoc KB ?M {:isa ::Mapping, :kb-relation ?R, :input ?C, :output ?C2})
    )
  )

