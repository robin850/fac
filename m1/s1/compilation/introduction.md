---
layout: default
title: Compilation
section: m1/s1
---

# Introduction à la compilation

Il existe deux grands types de langages : les langages de haut niveau, se rapprochant
le plus du langage naturel et les langages de bas niveau, se rapprochant le plus du
langage machine.

## Justification

La justification est la distance qu'il y a entre le langage machine et le langage
symbolique / évolué. Cette distance entre les langages de bas niveau et ceux de
plus haut niveau implique :

* Une transformation du programme exprimé en langage symbolique vers un langage
  machine = traduction (compilation ou assemblage) suivi d'une édition des liens,

* Ou une exécution par interprétation du texte direct,

* Ou une combinaison des deux solutions.

## Définitions

* **Traducteur** : Programme qui traduit un texte source en un texte objet (sans
  effets de bord : l'algorithme sous-jacent est invariant, c'est à dire qu'il ne
  change pas).

* **Compilateur** : Si le texte source est dans un langage évolué, alors le traducteur
  est un compilateur.

* **Assembleur** : Si le texte source est dans un langage de bas niveau orienté
  machine alors le traducteur est un assembleur.

* **Accepteur** : Un accepteur d'un langage L est un programme qui
  vérifie si un texte appartient bien au langage L. Les résultats sont :
    - Un diagnostic d'erreurs.
    - Une analyse du texte source.

* **Analyseur** : Il resort un diagnostique d'erreurs s'il y en a et analyse le
  texte source.

* **Interpréteur** : Programme qui accepte (accepteur intégré) un texte écrit dans
  un langage L et qui l’exécute. Il ne traduit pas le texte source en texte objet.
  Chaque phrase est traduite avant d'être exécutée.

## Notations

* Traducteur : T

~~~
+----------------------------------+
| Langage source     Langage objet |
+-----+                       +----+
      | Langage du traducteur |
      +-----------------------+
~~~

A l'extrémité gauche, on retrouve le langage dans lequel le code est écrit,
en bas le langage dans lequel le traducteur est écrit et à l'extrémité droite
le langage que le traducteur produit.

* Interpréteur : I

~~~
+----+
| L  | Langage du texte source
|    |
|    |
|    |
| LM | Langage de l'interpréteur
+----+
~~~

## Compilation et édition de liens (reliure)

Traduction du texte source vers le langage machine avec une étape intermédiaire
qui est le texte objet (en binaire translatable). Le compilateur ne traduit pas
immédiatement du texte source vers le langage machine : texte source -> texte objet
(binaire) -> texte machine.

L'acceptation se fait entre le langage source et le langage objet.

L'édition de liens se fait entre le langage objet et le texte en langage machine.

### Remarques

* Différence entre langage machine et langage objet : la désignation des adresses
  mémoire en langage objet sont relatives à la base d'implantation du programme.
  Le texte objet contient un codage des références externes.

* L'édition de liens produit un exécutable en langage machine à partir de plusieurs
  modules objet. Elle traduit les adresses exprimées en relatif aux adresses
  réelles. L'intérêt de cette étape est de faciliter la compilation séparée et
  l'utilisation de bibliothèque de procédures. Cela permet d'avoir des compilateurs
  pour que le texte objet ne dépende pas d'une seule machine (le binaire translatable
  est universel).

## Traduction vs Interprétation

* Traduction : rapidité d’exécution, facilité de la compilation séparée.

* Interprétation : temps de production d'un programme rapide, portabilité, facilité
  de développement (pas d'étape de compilation, de reliure, etc.) mais exécution
  lente.

Les inconvénients de la traduction sont les avantages de l'interprétation.

> **Note** : Il existe des langages seulement compilés, des langages purement
  interprétés et des langages interprétés et compilés.

## Description des étapes de compilation

### Analyse du code source

L'analyse consiste à :

* Vérifier que la grammaire des textes est valide.
* Décomposer le texte en constructions élémentaires.
* Créer du texte objet à partir des constructions élémentaires.

  Cela dépend du lexique, de la syntaxe (règles de grammaire) et de la sémantique
  du langage.

Le noyau de l'analyse est un analyseur syntaxique. On utilise donc une construction
dirigée par la syntaxe.

Les actions sémantiques sont associées aux règles de la grammaire qui sont au
nombre de deux :

* La grammaire qui décrit le lexique : constructions des unités syntaxiques.
  * Tokens (catégorie de mots).
  * Les identificateurs et les constantes.
  * Les chaines de caractères.
  * Les séparateurs.
  * Les mots clés.
* La grammaire qui décrit la syntaxe : phrases.

L'analyseur lexicale utilise un flot de caractères en entrée. Il effectue une lecture
caractère par caractère jusqu'à rencontrer un séparateur (espace, opérateur ou
délimiteur). L'analyseur lexicale prend en charge tous les éléments relatifs à la
forme externe du texte source (commentaires, espaces, décalages, tabulations, retour
à la ligne). Il décharge certaines contraintes liées à la pagination pour simplifier
le travail de l'analyseur syntaxique.

### Syntaxe du texte objet

¯\\\_(ツ)\_/¯
