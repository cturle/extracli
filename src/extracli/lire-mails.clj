(ns extracli.ksh
    (:require [extracli.kb :as kb]) )

(comment "plan 1"
  :task "traiter tous les mails"
  :plan "V ?M mails Task(traiter ?M)"
  :execution "Task(traiter mail-1)"
  :historique ["(def-plan T1) => P1", "(execute-plan P1)", "(traiter-mail mail-1)", "pb-detected"]
  :detected-pb "(traiter-mail mail-1) va être trop long") ; <== comment s'effectue cette detection ???

(comment "plan 2"
  :task "traiter tous les mails"
  :plan "V ?M mails Task(analyser-mail ?M), ordonner mails, traiter dans l'ordre les mails"
  :execution ""
  :historique [] )




