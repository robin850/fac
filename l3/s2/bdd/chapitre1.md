---
layout: default
title: BDD
section: l3/s2
---

# Objectif et définition des bases de données

## Définitions

### Base de données

Une collection d'informations inter-reliées, stockée de manière plus ou moins
permanente dans un système informatique. C'est une entité cohérente logiquement
et véhiculant une certaine sémantique.

### SGBD (Système de Gestion de Base de Données)

Un système qui permet à une ou plusieurs personnes de créer, manipuler ou de
modifier les données d'une base de données.

Le SGBD sépare description des données, des données elles mêmes.

## Objectifs

1. Administration **cohérente** des données.
  * Établissement d'une vue canonique des informations manipulées.
  * Possibilité de modélisations particulières incluant des données privées.
  * Développement incrémental.
  * On obtient une représentation des données proche du fonctionnement réel.

2. Indépendance **physique** des données.
  * La structure canonique des données doit être indépendante des structures de
    stockage en informatique (**séparation du monde réel et informatique**).
  * Non réécriture des programmes si changement de structure de stockage.

3. Indépendance **logique** des données.
  * Possibilité pour les applications d'ignorer les besoins des autres.
  * Possibilité de limiter les conséquences du partage (données confidentielles).

4. Contrôle de la **redondance** des données.
  * Maintient de la cohérence des données partagées par plusieurs applications.
  * Diminution des tâches de saisie.

5. Manipulation des données par des **non-informaticiens**.
  * Langage proche du langage naturel.
  * Intégration possible, cependant, dans des langages de programmation.

## Les différents types de base de données

* Les bases hiérarchiques : Ce sont les premières base de données. Elles utilisaient
  une gestion  de pointeurs entre des  enregistrements. Le schéma de la base était
  arborescent.

* Les bases réseaux : 20% des base de données actuelles. Ce sont les plus rapides.
  Même principe que les précédentes, mais le  schéma de la base est beaucoup plus
  ouvert.

* Les bases relationnelles : 75% des base de données actuelles. Données représentées
  dans des tables basées sur l’algèbre relationnel et un langage déclaratif (SQL).

* Les bases déductives : 2-3 % des base de données actuelles. Représentation en
  tables, et langage basé sur le calcul de prédicat (logique du premier ordre).

* Les bases objets : 2-3% des base de données actuelles. Données = instances de
  classes hiérarchisées.

## Les langages de la base de données

* LDD (Langage de Description des Données) : description du schéma et des règles.
* LMD (Langage de Manipulation des Données) : interrogation et mise à jour de la
  base de données.
* LCD (Langage de Contrôle des données).

## Cardinalité des associations

### One-to-One

~~~
    0, 1                                    0, 1
+---------+        /----------\        +-----------+
| Voiture |<-------| Conduite |------->| Chauffeur |
+---------+        \----------/        +-----------+
~~~

### Many to one

~~~
    0, 1                                       0, n
+----------+       /---------------\       +----------+
| Comments |-------| contenue dans |------>| Articles |
+----------+       \---------------/       +----------+
~~~

### Many to many

~~~
     0, n                                  0, n
+-----------+       /----------\       +---------+
| Documents |-------| Ecriture |-------| Auteurs |
+-----------+       \----------/       +---------+
~~~

## Associations bouclantes

~~~
         /---------\
  -------| Couple  |-------
  |      \---------/      |
  |                       |
  |      +----------+     |
  |----->| Personne |<----|
         +----------+
~~~

## Les entités faibles

Les entités faibles sont des entités qui ne peuvent être complètement identifiées
que par une seconde entité avec lesquelles elles sont en relation. Par exemple:

~~~
   0, n                                 1, 1
+--------+        /----------\       +-------+
| Cinéma |<-------| est dans |-------| Salle |
+--------+        \----------/       +-------+
~~~
