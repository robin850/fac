---
layout: default
title: BDD
section: l3/s2
---

# Le modèle et l'algèbre relationnels

## Relation

Une relation est un tableau à deux dimensions où une ligne est un tuple.

On identifie les colonnes tu tableau par un nom afin d'éviter de dépendre
de l'ordre. Une colonne dans une relation est appelée un *attribut*.

## Clés étrangères

* Lors d'une insertion, la valeur des attributs doit exister dans la
  relation référencée.
* Lors d'une suppression dans la relation référencée, les tuples référençant
  doivent disparaîtres.
* Les clés étrangères définissent les associations du modèle E/A.

## Passage du modèle E/A au modèle relationnel

On transforme chaque association n-aire en association<strong><em>s</em></strong> binaire<strong><em>s</em></strong>.
