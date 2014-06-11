(ns extracli.ksh
    (:require [extracli.kb :as kb]) )



(comment "=== TASKS ==="
  ;; comment est créée la tâche ci-dessous ?
  {TASK-001 {:description    "mettre à jour le projet extraction-client sous PS-Next de la semaine"
             :isa            Extracli-Update-PSNext-Project-Goal-Task
             :start-date-min "10/06/2014"
             :end-date-max   "10/06/2014 12h"
             :semaine        ["02-06-2014" "08-06-2014"]
             }
   TASK-002 {:description    "suivi-re7 du 09/06/2014"
             :isa            Suivi-re7-Goal-Task
             :jour           "09/06/2014"
             }
   TASK-003 {:description    "suivi-re7 du 10/06/2014"
             :isa            Suivi-re7-Goal-Task
             :jour           "10/06/2014"
             }
   })


(comment "=== FACTS ==="
  { FACT-001 {:isa ::Fact, :relation :state, :arg1 Task-001, :arg2 :finished}
    FACT-002 {:isa ::Fact, :relation :state, :arg1 Task-002, :arg2 :finished}
    FACT-003 {:isa ::Fact, :relation :state, :arg1 Task-003, :arg2 :finished}
    })


(comment "=== AGENT-STATES ==="
  { STATE-001 {:isa  ::Agent-State, :date "10/06/2014 9h",
               :task-v []
               }
    STATE-002 {:isa  ::Agent-State, :date "10/06/2014 10h", :previous-state STATE-001
               :task-v [TASK-001]
               }
    STATE-003 {:isa  ::Agent-State, :date "10/06/2014 14h", :previous-state STATE-002
               :task-v []
               :added-fact-s  #{FACT-001}
               }
    STATE-004 {:isa  ::Agent-State, :date "10/06/2014 16h30", :previous-state STATE-003
               :task-v [TASK-002]
               }
    STATE-005 {:isa  ::Agent-State, :date "10/06/2014 17h15", :previous-state STATE-004
               :task-v []
               :added-fact-s  #{FACT-002}
               }
    STATE-006 {:isa  ::Agent-State, :date "10/06/2014 17h20", :previous-state STATE-005
               :task-v [TASK-003]
               }
    STATE-007 {:isa  ::Agent-State, :date "10/06/2014 18h00", :previous-state STATE-006
               :task-v []
               :added-fact-s  #{FACT-003}
               }
    })



