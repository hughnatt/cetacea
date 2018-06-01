# Contrat principal:

Notre jeu est en 2D. Le plateau est constitué d'une grande carte représentant un océan composé de blocs pouvant contenir deux types d'entités : les entités mobiles et les entités immobiles.

Les entités immobiles :
- Rochers (les falaises délimitent la map, les rochers au milieu de l'eau sont des obstacles dont l'apparition est aléatoire)
- Iles (Shop)
- Iceberg (Comme des rochers, mais franchissable en mode sous-marin)

Les entités mobiles (controlé par des automates) :
- Bateau du joueur
- Baleinier
- Destroyer
- Pétrole
- Baleine
- Projectiles

Un bloc peut contenir différentes entités.

Le but du joueur est de sauver les baleines et d'obtenir le meilleur score.
Le joueur sera cependant soumis à différents adversaires :
  - Les baleiniers qui tentent de tuer les baleines.
  - Les destroyers qui tentent de tuer le joueur.
  
Le joueur possède une barre de vie et une barre de pétrole. Si une de ces barres atteint le niveau 0, le jeu se termine.
 
Pour augmenter son score, le joueur doit sauver les baleines, détruire ses adversaires.
Pour augmenter sa barre de vie, le joueur doit se rendre sur les îles et acheter un moyen de réparation.
Pour augmenter sa barre de pétrole, le joueur doit récupérer les nappes de pétrole.

Le score diminue si une baleine meurt.
La barre de vie diminue si le joueur se fait toucher par un projectile destroyer.
La barre de pétrole diminue continuellement au fil du temps.

Pour sauver les baleines, le joueur doit se rendre à côté d'elles et faire remonter leur barre de capture. Cette barre de capture indique le temps restant avant que la baleine soit sauvée ou capturée (par défaut, barre à zéro). Une fois la baleine sauvée, elle replonge dans l'eau et disparaît.

Les baleiniers tentent de tuer les baleines, c'est à dire font augmenter la barre de capture.

## Habilité des entités

Le bateau du joueur possède les attaques suivantes :
* Hit : Tire en ligne droite à certaine portée (stoppé par la première entité rencontrée) 
* Whizz : Mode Sous-Marin = Permet de passer en dessous des Icebergs
                            Eviter les projectiles
                            Invulnérabilité temporaire
                            Impossible de sauver les baleines
* Pop : Enflamme les nappes de pétroles à proximité 

Destroyer :
* Tirent dans les 4 directions (Portée x cases)
* Deviennent plus fort (+ resistants et + de dégats au fur et à mesure du jeu)
* Arme : Canon
* Whizz : Accélération
* Pop : Double projectile

Baleinier :
* Attaquent les baleines pour les tuer, les manger, les vendre aux japonais et faire un délicieux repas, miam !
* Arme : Harpon (Courte Portée, limite de 1-2 cases de proximité, peut échouer)
* Whizz : Augmente la barre de capture des baleines plus rapidement
* Pop : Baleinier coule

Pétrole :
* Whizz : La plaque s'enflamme toute seule
* Pop : La plaque s'aggrandit

Baleine : 
* Whizz : Disparait et réapparait plus loin
* Pop : Jet d'eau (animation)

Projectiles :
* Whizz : Change de direction aléatoirement
* Pop : Accélération du projectile

## Iles / Comptoirs commerciaux

Il y a plusieurs îles, qui sont à différents endroits de la carte. 
Les îles permettent au joueur de réparer son bateau (remonte la barre de vie) ou améliorer les armes en échange de pétrole.

Amélioration armes :
* Portée
* Pouvoir paralysant
* Nombre de dégat

# Extension:

* Le mode sous-marin a une limite de 5 secondes maximum sous l'eau (on peut remonter à la surface avant la fin) et un cooldown de 5 secondes avant un nouveau passage en mode sous-marin.
* La caméra bordure/caméra pas totalement centrée
* Difficulté incrémentale
* On peut acheter des bateaux alliés dans les shops
* Activité métérologique
* Les baleines que l'on sauve rejoignent au fur et à mesure une armée de baleines qu'on peut commander
