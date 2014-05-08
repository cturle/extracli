(ns extracli.collection)



; (Mapping=>Rule-1 {1 {:isa :none}, :isa {:isa :Relation}, 3 {:isa ::Collection, :value [:isa 3]}, 4 {:isa :none}} :isa 3 1 4)
(defn Mapping=>Rule-1
  "  in: [?R ?C]
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
  "  in: ?A
    pre: {?A  {isa Action, in ?varX, pre ?PRE, out ?varY, post ?POST, fn ?F}}
    out: ?A2
   post: {?A2 {isa Action, in [?varX], pre ?PRE, out [?varY], post ?POST, fn _}}"

  [KB ?A ?A2]
  (let [A     (get KB ?A)
        A2-F  (fn [KB0 X-v Y-v]
                (reduce (fn [KB [X Y]] ((:fn A) KB X Y)) KB0 (map vector X-v Y-v)) )]
    (assoc KB ?A2 {:isa :Action, :in [(:in A)], :pre (:pre A), :out [(:out A)], :post (:post A), :fn A2-F}) ))























