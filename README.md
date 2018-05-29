# projet-pla
Projet Logiciel Niveau Application

<img src="http://cairnsdiveadventures.com.au/wp-content/uploads/2011/04/Humpback.jpg">


Le jeu est constitué d'une grande carte qui constitue le plateau de jeu.
La carte représente un océan composé de différents "blocs":
- Eau
- Rocher (délimitent la map, apparition aléatoire)
- Iles (Shop)
- Iceberg (Comme des rochers, sauf pour le sous-marin)

Les blocs d'eau peuvent contenir les entités suivantes :

# Entités
- Bateau du joueur
- Baleinier
- Destroyer
- Pétrole
- Baleine

Le but du joueur est de sauver les baleines en se rendant à coté d'elle.
Les baleiniers tentent de tuer les baleines.

Chaque baleine sauvée fait gagner des points.
Chaque baleine tuée par un baleinier à l'écran fait perdre des points.

Le bateau doit aussi gérer son approvisionnement en pétrole en se rendant régulièrement vers des nappes de pétrôle.

Les destroyers tentent de couler le bateau du joueur en le poursuivant.

Toutes les entités ont un niveau de vie.

# Abilité des navires

Le bateau du joueur possède les attaques suivantes :
* Hit : Tire en ligne droite (stoppé par un rocher ou par la première entité ou x cases max)
* Whizz : Mode Sous-Marin = Permet de passer en dessous des Icebergs
                            Invulnérabilité temporaire
                            Impossible de sauver les baleines
* Pop : Enflamme les nappes de pétroles à proximité



Destroyer :
* Tirent dans les 4 directions (Portée x cases)



* Deviennent plus fort (+resistants et + de dégats au fur et à mesure du jeu)
* Arme : Canon

Baleinier :
* Attaquent les baleines pour les tuer, les manger, les vendre aux japonais et faire un délicieux repas, miam !
* Arme : Harpon (Courte Portée)


# Iles / Comptoirs commerciaux

Amélioration armes :
* Portée
* Pouvoir paralysant
* Niveau global


# Autre idées
Pouvoir de Sonar pour cartographier la map ?
