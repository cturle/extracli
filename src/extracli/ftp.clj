(ns extracli.ftp
    (:require [clojure.string :refer [join]]
              [extracli.kb :as kb] ))


(def TrimParameter {:isa ::SiteParameter, :name "TRIM" :possible-values #{0,1,2}})
(def ModeParameter {:isa ::Parameter, :name "MODE", :possible-values #{"ascii", "binary"}})


(comment "Get=>trim-1"
  :in-v  [?GET]
  :pre   {?GET  {:isa ::GetCmd, :session ?S, :source ?F1 :cible ?F2, :pre-client-state ?PCS}
          ?S    {:isa ::Session, :server ?SRV}
          ?SRV  {:isa :system/As400}
          ?PV1  {:isa :parameter/Value, :context ?PCS :parameter TrimParameter :value 0}
          ?PV2  {:isa :parameter/Value, :context ?PCS :parameter ModeParameter :value "ascii"} }
  :out-v [?PP]
  :post  {?PP   {:isa :file/SameContent, :arg1 ?F1, :arg2 ?F2, :is true}}
  )

(comment "Get=>trim-2"
  :in-v  [?GET]
  :pre   {?GET  {:isa ::GetCmd, :session ?S :source ?S1 :cible ?S2 :pre-client-state ?PCS}
          ?S    {:isa ::Session, :server ?SRV}
          ?SRV  {:isa :system/As400}
          ?PV1  {:isa :parameter/Value, :state ?PCS :parameter ModeParameter :value "ascii"}
          ?PV2  {:isa :parameter/Value, :state ?PCS :parameter TrimParameter :value 1} }
  :out-v [?PP]
  :post  {?PP  {:isa :file/TrailingBlanksTruncated, :arg1 ?F1, :arg2 ?F2, :is true}}
  )

(comment "default-As400-trim-parameter-value"
  :in-v  [?S]
  :pre   {?S    {:isa ::Session, :server ?SRV :first-server-state ?FS}
          ?SRV  {:isa :system/As400}
          ?TPV  {:isa :parameter/Value, :context ?FS :parameter TrimParameter} }
  :out-v []
  :post  {?TPV  {:value 1}}
  )

(comment "State=>set-trim-1"
  :in-v  [?C]
  :pre   {?C    {:isa ::Cmd, :to-string "QUOTE SITE TRIM 0", :session ?S :post-server-state ?PSS}
          ?S    {:isa ::Session, :server ?SRV}
          ?SRV  {:isa :system/As400}
          ?PV   {:isa :parameter/Value, :context ?PSS :parameter TrimParameter} }
  :out-v []
  :post  {?PV   {:value 0}}
  )

****(comment "State=>set-parameter-value-1"
  :in-v  [?C]
  :pre   {?C    {:isa ::Cmd, :element-v ["QUOTE" "SITE" ?PN ?PVV], :session ?S :exec :success :pre-server-state ?PRE :post-server-state ?POST}
          ?S    {:isa ::Session, :server ?SRV}
          ?SRV  {:isa :system/As400}
          ?PV   {:isa :parameter/Value, :context ?POST :parameter ?P}
          ?P    {:isa ::SiteParameter, :name ?PN :possible-values ?R}
          ?PP   {:isa :set/Member-of, :object ?PVV, :set ?R, :is :true} }
  :out-v []
  :post  {?PV   {:value ?PVV}
          ?POST {:based-on ?PRE} }
  )

;;; problème : quid des autres params value ?PV. Normalement, ils gardent leur valeur. ex: mode = "ascii ?"

(comment "State=>create-host-1"
  :in-v  [?PSS]
  :pre   {?CMD  {:isa ::Cmd, :session ?S :post-server-state ?PSS}
          ?S    {:isa ::Session :server ?SRV}
          ?PSS  {:isa ::State} }
  :out-v []
  :new-v []
  :post  {?PSS  {:host ?SRV}}
  )


(comment "State=>create-param-trim-1"
  :in-v  [?S]
  :pre   {?S    {:isa ::State, :host ?H}
          ?H    {:isa :system/As400} }
  :out-v []
  :new-v [?PV]
  :post  {?PV   {:isa :parameter/Value, :context ?S :parameter TrimParameter}}
  )

(comment

  ;; "old-bad-knowledge"
  :in-v  [?GET]
  :pre   {?GET  {:isa ::GetCmd, :source ?F1 :cible ?F2}}
  :out-v [?PP]
  :post  {?PP   {:isa :file/SameContent, :arg1 ?F1, :arg2 ?F2, :is true}}

  KB-1 new-data   {?C   {:isa :system/PC}
                   ?SRV {:isa :system/As400}
                   ?S   {:isa ::Session, :client ?C :server ?SRV}
                   ?F1  {:isa :file/File}
                   ?F2  {:isa :file/File}
                   ?C   {:isa ::GetCmd, :session ?S, :source ?F1 :cible ?F2} }
       new-facts  {?PP1 {:isa :file/TrailingBlanksTruncated, :arg1 ?F1, :arg2 ?F2}
                   ?F1  {:isa :Fact, :relation ?PP1, :is true}
                   ?PP2 {:isa :file/SameContent, :arg1 ?F1, :arg2 ?F2}
                   ?F2  {:isa :kb/UserFact, :relation ?PP2, :is false} }

  KB-2 new-data   {?F1  {:isa :file/File}
                   ?F2  {:isa :file/File}
                   ?C   {:isa ::GetCmd, :source ?F1 :cible ?F2}}
       with       {?PP  {:isa :file/TrailingBlanksTruncated, :arg1 ?F1, :arg2 ?F2}}
       new-facts  {?F   {:isa :kb/UserFact, :relation ?PP, :is true}}


;;; 1- si "old-bad-knowledge" pour agent, simuler l'incohérence au niveau de l'agent.
;;; 2- si "old-bad-knowledge" pour le user et pas agent, simuler question du user et explication de l'agent.

  )

















