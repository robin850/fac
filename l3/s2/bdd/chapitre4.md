---
layout: default
title: BDD
section: l3/s2
---

# La théorie de la normalisation relationnelle

## Les dépendances fonctionnelles

Une dépendance fonctionelle est une certaine perception de la réalité. Par exemple:

~~~sql
-- Avec NVH = Numéro de véhicule
-- et   NSS = Numéro de sécurité sociale
NVH -> NSS
~~~

signifie dans le monde réel l'interdiction de copropriété.


### Axiomes d'Armstrong

* Réfléxivité:  Si Y c= X   =>   X -> Y
* Augmentation: Si X -> Y   =>   XZ -> YZ
* Transitivité: Si X -> Y et Y -> Z => X -> Z

### Règles déduites

* Union: Si X -> Y et X -> Z => X -> YZ
* Pseudo-transitivité: Si X -> Y et WY -> Z => WX -> Z
* Décomposition: Si X -> Y et Y c= Z => X -> Z

## Dépendance fonctionnielle élémentaire

Une dépendance fonctionnelle X -> A est élémentaire si:

- A est un attribut unique non inclus dans X.
- Il n'exite pas de DF X' tel que X' -> A et X' c X.

Dans le graphe de la représentation des DFs:

- Un arc représente une DF.
- Un noeud représente un attribut.

### Couverture minimale d'un ensemble de DFs

F est minimale si:

- X -> A appartient à F, A est un attribut unique.
- Il n'existe pas X -> A appartenant à F, F - { X->A } équivalent à F.

## Les formes normales

**1ère forme normale**

Une relation est en première forme normale si tout attribut est atomique.

**2ème forme normale**

Une relation est en deuxième forme normale si elle est en *1NF* et si tout
attribut n'appartenant pas à la clé ne dépend pas d'*une* partie de la clé.

**3ème forme normale**

Une relation est en troisième forme normale si elle est en *2NF* et si tout
attribut n'appartenant *pas* à la clé ne dépend pas d'un attribut *non* clé.

### Propriété des 3 premières formes normales

> **Théorème**:
>
> Toute relation R admet une décomposition (R1, ... , Rn) en 3NF telle que :
>
> - cette décomposition préserve les DFs
> - cette décomposition est sans perte


Décomposition en 3NF préservant les DFs

* entrée : le schéma R et l'ens F de DFs formant une couverture minimale
* Méthode:
  - si un attribut A de R n'est pas impliqué dans une DF de F, former une
  relation R’(A).
  - si une DF de F inclut tous les attributs de R sortir R comme résultat.
  - sinon, si X -> Ai1, X -> Ai2, X -> Ain sont dans F, former un relation
  R’(X, Ai1, Ai2, ... Ain)
