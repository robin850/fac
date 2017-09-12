---
layout: default
section: l3/s2
title: P. Système
---

On reprend l'exercice précédent avec les processus A et B, des pages de tailles 4ko (**4096 octets**).

Faire la conversion des adresses linéaires suivantes en adresses physiques :

* Processus A : 4098
* Processus A : 12292
* Processus B : 8212

> C.f partie sur la conversion d'adresse sur [la page principale](/l3-s2/systeme.html)

-------------------------------------------------------------------------------

On utilise les réponses de l'exercice précédent pour déterminer à quelle case est associée une page de segment.

**Processus A - Adresse 4098**

4 098 = 1 &times; 4096 + 2

Cette adresse référence donc une donnée de la deuxième page du premier segment de A (puisqu'une page fait 4096 octets et 4098 > 4096).

On a donc :

* 4098 (@ linéaire)
* (S1A, p2, 4098 - (1 &times; 4096)) = (S1A, p2, 2) (@ virtuelle)
* (C5, 2) (@ physique)

**Processus A - Adresse 12292**

12 292 = 12 288 + 4 = 3 &times; 4096 + 4

Cette adresse référence donc la deuxième page du segment 2 de A (puisque, selon l'énoncé de l'exercie précédent, le premier segment ne possède que 2 pages et qu'au moins trois pages sont déjà remplies).

* 12292 (@ linéaire)
* (S2A, p2, 4) (@ virtuelle)
* (C10, 4) (@ physique)

**Processus B - Adresse 8212**

* 8212 (@ linéaire)
* (S1B, p3, 20) (@ virtuelle)
* (C2, 20) (@ physique)
