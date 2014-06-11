### Description en anglais :

You are given two empty jugs. One holds five gallons of water and the other holds three gallons.

There is a well that has unlimited water that you can use to completely fill the jugs.

You can also empty a jug or pour water from one jug to another.

There are no marks for intermediate levels on the jugs.

The goal is to fill the three-gallon jug with one gallon of water.


### Description en Soar

#### Model
{S

#### Instances
{?J5 {:isa jug, :nb-gallons 0, :capacity-gallons 5}
 ?J3 {:isa jug, :nb-gallons 0, :capacity-gallons 3}
 ?S1 {:isa state, :jug5 ?J5 : jug3 ?J3} }

#### Actions possibles
A0001 = remplir un jug
 ?S  {:jug J?}
 ?J  {:nb-gallons ?PRE, :capacity-gallons ?C}
-->
 ?J  {:nb-gallons ?C}
    -{:nb-gallons ?PRE}


A0002 = transvaser un jug
?S {:jug (J1, J2)}
?J1 {:isa jug, :nb-gallons ?PRE, :capacity-gallons ?C}
?J2 {:isa jug, :nb-gallons ?PRE, :capacity-gallons ?C}
-->
 ?J {:nb-gallons ?C}
   -{:nb-gallons ?PRE}

#### Propositions
P0001 = si un jug n'est pas plein, proposer de le remplir

#### Goal
; The goal is to fill the three-gallon jug with one gallon of water.

 {?S  :isa State, :jug3 ?J3}
 {?J3 :nb-gallons 1}
-->
 (println "tâche terminée")
 (halt)


