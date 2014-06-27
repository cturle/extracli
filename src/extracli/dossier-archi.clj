; finaliser le document d'architecture applicative d'Extracli

; connaissances générales
{
 DocArchiApplicative {:genl Document}
 ?D1 {:isa DefRange :dom DocArchiApplicative :rel application :range Application}
 ; un doc est finalisé <=> ce doc satisfait toutes les contraintes qu'il doit satisfaire
 ; un document d'architecture d'une application a comme contrainte-à-satisfaire (qu'il contient une description de l'architecture de l'application)
 ?DDs1 {:genl DefDescription}
 ?PP1  {:isa DefRange= :dom ?DDs1 :rel view :range= ApplicativeArchitecture}
}

; connaissances spécifiques aux domaines de la tâche (Extracli)
{

}


; connaissances spécifiques à la tâche

sp {dossier-archi-extracli*init
   (state <s> ^type state)
-->
   (<s> ^goal <g>)
   (<g> ^type CreerDocGoal ^doc <d>)
   (<d> ^type DescriptiveDocument ^domain Extracli ^view ApplicativeArchitecture ^finalized true)
}

