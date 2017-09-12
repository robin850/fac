---
layout: default
section: l3/s2
title: P. Système
---

On utilise une mémoire à 3 cases. Montrer le fonctionnement de l'algorithme optimal qui consiste à enlever la page qui sera référencée le plus tard possible.

On utilise l'ordre :

0 1 2 3 0 1 4 0 1 2 3

-------------------------------------------------------------------------------

~~~
  0   1   2   3   0   1   4   0   1   2   3
+---+---+---+---+---+---+---+---+---+---+---+
| 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 3 |
+---+---+---+---+---+---+---+---+---+---+---+
|   | 1 | 1 | 1 | 1 | 1 | 1 | 1 | 1 | 2 | 2 |
+---+---+---+---+---+---+---+---+---+---+---+
|   |   | 2 | 3 | 3 | 3 | 4 | 4 | 4 | 4 | 4 |
+---+---+---+---+---+---+---+---+---+---+---+
  d   d   d   d           d           d   d
~~~
