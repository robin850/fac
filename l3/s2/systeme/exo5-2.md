---
layout: default
title: P. Système
section: l3/s2
---

**Sujet**

Ordonnancement de 5 travaux A, B, C, D, E (qui sont exécutés en même temps). Le temps
d'exécution est estimé à 10, 6, 2, 4, 8 minutes. Les processus ont respectivement la priorité 3, 5, 2, 1, 4 (5 est la plus élevée).

Déterminer le temps moyen **d'attente** (pas d'exécution) avec les algorithmes d'ordonnancement :

* FIFO (A, B, C, D, E)
* Le plus court d'abord
* L'algo avec priorité
* Ordonnancement circulaire (tourniquet)

Il n'y a pas d'entrées / sorties.

-------------------------------------------------------------------------------

### Pour FIFO

* att<sub>A</sub> = 0
* att<sub>B</sub> = 10
* att<sub>C</sub> = 16
* att<sub>D</sub> = 18
* att<sub>E</sub> = 22

t<sub>moy</sub> = (0 + 10 + 16 + 18 + 22) / 5 = 13.2

### Le plus court d'abord

* att<sub>C</sub> = 0
* att<sub>D</sub> = 2
* att<sub>B</sub> = 6
* att<sub>E</sub> = 12
* att<sub>A</sub> = 20

t<sub>moy</sub> = (0 + 2 + 6+ 12 + 20) / 5 = 8

### Avec l'algo par priorité

* att<sub>B</sub> = 0
* att<sub>E</sub> = 6
* att<sub>A</sub> = 14
* att<sub>C</sub> = 24
* att<sub>D</sub> = 26

t<sub>moy</sub> = (0 + 6 + 14 + 24 + 26) / 5 = 14

## Avec tourniquet

On part du principe que le laps de temps de 2 minutes.

On exécute tout d'abord A deux minutes, puis B deux minutes, et ainsi de suites. On se réfère aux données de l'énoncé pour savoir quand un processus a terminé.

| A | B | C | D | E |
|:-:|:-:|:-:|:-:|:-:|
| X | X | X | X | X |
| X | X |   | X | X |
| X | X |   |   | X |
| X |   |   |   | X |
| X |   |   |   |   |

A<sub>i</sub> = Nombre de laps de temps = Exi / l (l = 2 min).

t<sub>i</sub> = Ai &times; somme (de i-1 à j = 1)

t<sub>m</sub> = somme
