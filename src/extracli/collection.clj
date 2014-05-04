(ns extracli.collection)


; (Mapping=>Rule-1 {1 {:isa :none}, :isa {:isa :Relation}, 3 {:isa ::Collection, :value [:isa 3]}, 4 {:isa :none}} 1 :isa 3 4)
(defn Mapping=>Rule-1
"args: ?R, ?C, ?M, ?C2
pre : (?R :isa :Relation)
      (?C :isa ::Collection), (?C :value [?V])
      (?V ?R _)
      (?M :isa :none), (?C2 :isa :none)
post: (?M :isa Mapping), (?M :kb-relation ?R), (?M :input ?C), (?M :output ?C2)
      (?C2 :isa ::Collection), (?C2 :value _)"
  [KB ?R ?C ?M ?C2]
  (let [C (get KB ?C)]
    (assoc KB ?M  {:isa ::Mapping, :kb-relation ?R, :input ?C, :output ?C2}
              ?C2 {:isa ::Collection, :value (map #(get-in KB [%1 ?R]) (:value C))} ))
  )


