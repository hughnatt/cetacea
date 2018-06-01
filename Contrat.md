# On promet:

Notre jeu est un mix entre un jeu infini type runner et ? . Il est en 2D, le plateau est constitué d'une grande carte représentant un océan composé de différents "blocs":
- Eau
- Rochers (les falaises délimitent la map, les rochers au milieu de l'eau sont des obstacles dont l'apparition est aléatoire)
- Iles (Shop)
- Iceberg (Comme des rochers, mais franchissable en mode sous-marin)

Les blocs d'eau peuvent contenir les entités suivantes :
- Bateau du joueur
- Baleinier
- Destroyer
- Pétrole
- Baleine

Le but du joueur est de sauver les baleines en se rendant à côté d'elles et en faisant remonter leur barre de vie si besoin. Une fois la baleine sauvée, elle replonge dans l'eau et disparaît.
Les baleiniers tentent de tuer les baleines, mais fuient lorsque le joueur les attaque car il n'ont pas de moyen de se défendre contre le joueur.
Chaque baleine sauvée fait gagner des points et chaque baleine tuée fait perdre des points. Si la baleine est tuée, le baleinier l'embarque et elle disparaît.
Les destroyers eux tentent de faire couler le bateau du joueur en le poursuivant et en l'attaquant.

Le bateau doit gérer son approvisionnement en pétrole en se rendant régulièrement vers des nappes de pétrôle générées lorsque le joueur détruit un bateau. 
Le pétrole s'étend au fur et à mesure sur la map et est inflammable avec l'action Pop du joueur (cause des dommages à toutes les entités présentes dessus: joueur, baleine, ennemi). Le feu s'éteint au fur et à mesure et fini par se dissiper.

Toutes les entités ont un niveau de vie.

## Abilité des navires

Le bateau du joueur possède les attaques suivantes :
* Hit : Tire en ligne droite (stoppé par un rocher ou par la première entité ou x cases max)
* Whizz : Mode Sous-Marin = Permet de passer en dessous des Icebergs
                            Eviter les projectiles
                            Invulnérabilité temporaire
                            Impossible de sauver les baleines
* Pop : Enflamme les nappes de pétroles à proximité (limite de 1-2 cases de proximité)

Destroyer :
* Tirent dans les 4 directions (Portée x cases)
* Deviennent plus fort (+ resistants et + de dégats au fur et à mesure du jeu)
* Arme : Canon

Baleinier :
* Attaquent les baleines pour les tuer, les manger, les vendre aux japonais et faire un délicieux repas, miam !
* Arme : Harpon (Courte Portée, limite de 1-2 cases de proximité, peut échouer)

## Iles / Comptoirs commerciaux

Il y a plusieurs îles, qui sont à des endroits fixes de la carte et ne bougent pas. 
La majorité des îles permettent au joueur de réparer son bateau (remonte la barre de vie) ou de l'améliorer (augmente la taille maximale de la barre de vie) en échange de pétrole.
L'île placée au centre de la carte, entourée par des icebergs et de nombreux destroyers permettent au joueur d'améliorer ses armes.

Amélioration armes :
* Portée
* Pouvoir paralysant
* Niveau global

# On promet peut être:

* Le mode sous-marin a une limite de 5 secondes maximum sous l'eau (on peut remonter à la surface avant la fin) et un cooldown de 10 secondes si 5 secondes sous l'eau (plus court si on reste moins longtemps)
* La caméra bordure/caméra pas totalement centrée
* Difficulté incrémentale
* On peut acheter des bateaux alliés dans les shops

# On est vraiment pas sûrs de promettre:

* Les baleines que l'on sauve rejoignent au fur et à mesure une armée de baleines qu'on peut commander
