---
layout: default
section: l3/s2
title: P. Système
---

4 processus sont exécutés. Il y a deux catégories de processus :

> **Rappel** : Préemption = réquisition d'une ressource

- Catégorie 1 : SCHED_FIFO
  * Préemption
  * Priorité
- Catégorie 2 : SCHED_RR
  * Laps de temps (10 ms ici)
  * Tourniquet

P1 et P2 sont de catégorie 1. P3 et P4 sont de catégorie 2

Les ordres de priorité sont P1 > P2 > P3 > P4. Et on a l'exécution suivante :

* P1 :
  * Calcul pendant 40ms
  * Lecture pendant 50ms
  * Calcul pendant 30ms
  * Lecture pendant 40ms
  * Calcul pendant 10ms
* P2 :
  * Calcul pendant 30ms
  * Lecture pendant 80ms
  * Calcul pendant 70ms
  * Lecture pendant 20ms
  * Calcul pendant 10ms
* P3 :
  * Calcul pendant 40ms
  * Lecture pendant 40ms
  * Calcul pendant 10ms
* P4 :
  * Calcul : 100ms

Donner le chronogramme d'exécution des 4 processus en faisant appraître les 4 états.
