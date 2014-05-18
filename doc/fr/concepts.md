# Les concepts utilisés

## Les suffixes des relations
Exemples : "-v", "-1", ...

Les suffixes des relations servent généralement à définir de nouvelles relations à partir d'une relation de base. Ces nouvelles relations sont uniquement différentes relativement au range.

### Définitions des suffixes

Si
'x R y' : indique que 'y' est un 'R' de 'x'

Alors
'x R-1 y' : indique que 'y' est le 'R' de 'x' et qu'il ne peut y en avoir d'autres.

'x R-s ys' : indique que 'ys' désigne un sous ensemble des 'R' de 'x'. Si un objet appartient à 'ys' alors c'est un 'R' de 'x'.

'x R-a ys' : indique que 'ys' désigne l'ensemble des 'R' de 'x'. Si un objet est un 'R' de 'x' alors il appartient à 'ys'.

'x R-v ys' : indique que 'ys' est la liste ordonnée des 'R' de 'x'. Cette relation est donc encore plus forte que 'R-s'.

## Les Actions

Les actions correspondent aux fonctions en clojure à la différence que c'est une définition déclarative qui est donnée et jamais le code. Cette définition explicite permet au système de comprendre ce que fait une action. Il peut ainsi manipuler les actions afin de les combiner et de générer de nouvelles actions. Cela lui permet aussi de générer le code qui permettra d'exécuter cette action.

Les actions sont définies par leurs propriétés 'in, pre, out et post'. Elles prennent en argument une base de données et les entrées déclarées dans la propriété 'in'. Ces entrées sont censées remplir les conditions définies dans la propriété 'pre'. C'est de la responsabilité de l'appelant de s'assurer que c'est bien le cas. En sortie, la base de données maj est retournée ainsi que les sorties déclarées dans la propriété 'out'. La base retournée vérifie les conditions définies dans la propriété 'post'.

## Les Primitives

Les primitives sont des actions dont le code est fourni par le développeur. Le système peut s'en servir afin de créer le code des actions dont les pre/post sont données.

