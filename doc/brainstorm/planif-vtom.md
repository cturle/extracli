# Planification Vtom

## l'objectif

L'objectif de ce document est de trouver comment exprimer les règles de planification Vtom.

### En langage naturel
Pour chaque chaine Extracli, le 2 de chaque mois à l'heure de lancement souhaitée,
dès que, BW, BO-BI, le service web de formatage et le S3 sont opérationnels et que les données des cubes BW sont à jour
alors Vtom lance la chaine.

#### lever les ambiguités
L'heure de lancement souhaitée est spécifique à chaque chaine Extracli.

### En langage formel
<pre>
(add-rule
  {:fire   :until-desactivated
   :if   (and [?C isa ChaineECL] [horaire-de-lancement-souhaité ?C ?HO] [hour ?HO ?H] [minute ?HO ?M])
   :then (add-goal
           {:def   [member (comportement AGENT-VTOM) ?R]
            :where [?R (Rule
                         {:fire   :until-desactivated
                          :if-let [?NOW  (and [now ?NOW] [day-of-month ?NOW 2] [hour ?NOW ?H] [minute ?NOW ?M])]
                          :then   (add-rule
                                    {:fire :once
                                     :if   (and [up? BW] [up? DWH] [up? WS] [up? S3] [data-BW-ok?])
                                     :then (add-task
                                             {:action      [launch-chaineVtom ?C]
                                              :valid-until (last-day-of-month ?NOW)
                                             })
                                    })
                         })]
            })
  })
</pre>

### Examples

#### nouvelle chaine ECL-MAC-C
<pre>
+KB : [ECL-MAC-C isa ChaineECL] [horaire-de-lancement-souhaité ECL-MAC-C (horaire "13h")]

déductions (elaborations ...)
=> [hour (horaire "13h") 10] [minute (horaire "13h") 0]

déclenchement de la règle :
=> new-goal added:
{:isa Goal
 :def   [member (comportement AGENT-VTOM) ?R]
 :where [?R (Rule
              {:fire   :until-desactivated
               :if-let [?NOW  (and [now ?NOW] [day-of-month ?NOW 2] [hour ?NOW 10] [minute ?NOW 0])]
               :then   (add-rule
                         {:fire :once
                          :if   (and [up? BW] [up? DWH] [up? WS] [up? S3] [data-BW-ok?])
                          :then (add-task
                                  {:action      [launch-chaineVtom ECL-MAC-C]
                                   :valid-until (last-day-of-month ?NOW)
                                  })
                         })
              })]
}
</pre>























