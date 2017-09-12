---
layout: default
title: Archi
section: prrel
---

# Condensé des cours d'architecture

## Portes logiques

### Porte NOT

![](not.svg)

### Porte NAND

![](nand.svg)

### Porte OR

![](or.svg)

### Porte NOR

![](nor.svg)

## Circuits

* **Les circuits combinatoires** : Les mêmes valeurs d'entrée produiront les mêmes valeurs de sortie.
* **Les circuits séquentiels** : Les mêmes valeurs d'entrée ne produisent pas toujours les mêmes valeurs de sortie.

De manière très schématique, les circuits combinatoires permettent d'obtenir toujours le même résultat et les circuits séquentiels font intervenir des variables internes. Il y a une notion de mémoire qui fait qu'ils peuvent **se souvenir** d'évènements passés et changer la sortie en conséquence.

Un circuit séquentiel est donc un circuit combinatoire mais avec une capacité de mémoire en plus.

## Codeurs

Les codeurs permettent de coder une entrée donnée en binaire. Une entrée est composée de plusieurs éléments. On a :

* E le nombre d'éléments en entrée
* S le nombre d'éléments en sortie avec S = log<sub>2</sub>(E)

## Décodeurs

Les décodeurs réalisent l'opération inverse des codeurs ; ils décodent une donnée binaire.

Toute fonction logique peut être réalisée avec un décodeur. C'est le principe de la table de vérité.

## Sélecteurs

Les sélecteurs permettent de sélectionner une valeur. On lui fornit :

* N entrées (notées e<sub>0</sub>, e<sub>1</sub>, ...)
* et N commandes (notées e<sub>0</sub>, e<sub>1</sub>, ...)

**Note** : Il y a le même nombre d'entrées que de commandes.

Le sélecteur va copier l'entrée e<sub>i</sub> si la commandé associée (c'est à dire c<sub>i</sub>) est à 1.

Exemple avec 2 entrées :

## Composition d'un processeur

Un processeur de base est composé de :

* Une unité logique
* Une unité arithmétique
* Une unité de commande
