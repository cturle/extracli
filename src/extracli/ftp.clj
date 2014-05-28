(ns extracli.ftp
    (:require [clojure.string :refer [join]]
              [extracli.kb :as kb] ))


(def TrimParameter {:isa ::SiteParameter, :name "TRIM"})

(comment "Get=>trim-1"
  :in-v  [?GET]
  :pre   {?GET  {:isa ::GetCmd, :session ?S, :source ?F1 :cible ?F2, :pre-client-state ?PCS}
          ?S    {:isa ::Session, :server ?SRV}
          ?SRV  {:isa :system/As400}
          ?PCS  {:isa ::As400State, :param-value ?PV :mode :ascii}
          ?PV   {:isa ::ParameterValue, :parameter TrimParameter :value 0} }
  :out-v [?PP]
  :post  {?PP   {:isa :file/SameContent, :arg1 ?F1, :arg2 ?F2, :is true}}
  )

(comment "Get=>trim-2"
  :in-v  [?GET]
  :pre   {?GET {:isa ::GetCmd, :session ?S, :source ?F1 :cible ?F2, :pre-client-state ?PCS}
          ?S   {:isa ::Session, :server ?SRV}
          ?SRV {:isa :system/As400}
          ?PCS  {:isa ::As400State, :param-value ?PV :mode :ascii}
          ?PV   {:isa ::ParameterValue, :parameter TrimParameter :value 1} }
  :out-v [?PP]
  :post  {?PP  {:isa :file/TrailingBlanksTruncated, :arg1 ?F1, :arg2 ?F2, :is true}}
  )

(def default-trim-value  {:isa ::ParameterValue, :parameter TrimParameter :value 1})
(def default-As400-state {:isa ::As400State, :param-value-s [default-trim-value] :mode :ascii})

(comment "State=>set-trim-1"
  :in-v  [?C]
  :pre   {?C    {:isa ::Cmd, :to-string "QUOTE SITE TRIM 0", :session ?S :post-server-state ?PSS}
          ?S    {:isa ::Session, :server ?SRV}
          ?SRV  {:isa :system/As400}
          ?PSS  {:isa ::As400State, :param-value ?PV}
          ?PV   {:isa ::ParameterValue, :parameter TrimParameter} }
  :out-v []
  :post  {?PV {:value 0}}
  )

(comment "State=>set-parameter-value-1"
  :in-v  [?C]
  :pre   {?C    {:isa ::Cmd, :element-v ["QUOTE" "SITE" ?PN ?PVV], :session ?S :pre-server-state ?PRE :post-server-state ?POST}
          ?S    {:isa ::Session, :server ?SRV}
          ?SRV  {:isa :system/As400}
          ?POST {:isa ::As400State, :param-value ?PV}
          ?PV   {:isa ::ParameterValue, :parameter ?P}
          ?P    {:isa ::Parameter, :name ?PN :possible-values ?R}
          ?PP   {:isa :set/Member-of, :object ?PVV, :set ?R, :is :true} }
  :out-v []
  :post  {?PV   {:value ?PVV}
          ?POST {:based-on ?PRE} }
  )

;;; problème : quid des autres params value ?PV. Normalement, ils gardent leur valeur.


(comment "State=>create-param-trim-1"
  :in-v  [?S]
  :pre   {?S    {:isa ::As400State} }
  :out-v []
  :new-v [?PV]
  :post  {?S    {:param-value ?PV}
          ?PV   {:isa ::ParameterValue, :parameter TrimParameter} }
  )


