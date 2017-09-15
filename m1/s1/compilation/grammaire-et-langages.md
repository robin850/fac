---
layout: default
title: Compilation
section: m1/s1
---

# Grammaires et langages

## Définitions

* L'ensemble des mots est contenu dans les *dictionnaires*.
* L'ensemble des règles est contenu dans les *grammaires*.
* Une phrase est correcte par rapport à une *syntaxe*.
* La *sémantique* est l'ensemble des connaissances sur les phrases.
* Un mot (ou chaîne) est une suite finie de V (vocabulaire). V* est l'ensemble
  des chaînes construites sur V y compris &epsilon; (i.e. le mot vide).

## Notion de grammaire

Soit V, un vocabulaire ; V* c'est l'ensemble V muni de la concaténation.

Par exemple:

* a, b appartiennent à V
* => ab appartient à V*

Grammaire formelle (algébrique) : G = (V<sub>n</sub>, V<sub>t</sub>, P, S) avec :

* V<sub>n</sub> : vocabulaire non terminal (conventionnellement des lettres
  majuscules).
* V<sub>t</sub> : vocabulaire terminal (conventionnellement des lettres
  minuscules).
* P : Règles de production (paires formées d'un non-terminal et d'une suite de
  terminaux et de non-terminaux).
* S : Axiome (élément de l'ensemble des non-terminaux).
