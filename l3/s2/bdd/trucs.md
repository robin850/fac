---
layout: default
section: l3/s2
title: BDD
---

## Trucs utiles

### Les indexes

Il existe différents types d'index mais les principaux sont les index primaires, créés
automatiquement sur les clés primaires.

Un index améliore réellement les performances s'il se trouve sur un attribut qui :

* Est utilisé souvent dans les clauses `WHERE`.
* Qui n'est pas utilisé par des fonctions dans les clauses `WHERE`.
* Qui a une bonne sélectivité.
* Dans une table où le volume de données est quand même suffisamment important.

### Étapes de traitement d'une requête

* Analyse
* Contrôle
* Optimisation
* Exécution
