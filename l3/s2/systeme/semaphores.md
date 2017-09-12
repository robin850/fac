---
layout: default
title: P. Système
section: l3/s2
---

## Utilisation des sémaphores

S est un entier, initialisé avant le programme.

~~~c
// Pseudo code
S = 10;

// C
init(S, 10);
~~~~

Ensuite, on appel `P(S)` et `V(S)`.

* `P(S)` est une instruction bloquante (`S > 0 ? S-- : bloquer processus`).
  Il peut être un "laisser passer" s'il rese des jetons, sinon c'est une
  "barrière" pour bloquer les processus.
* `V(S)` est bloquant aussi (`S++; reveiller processus bloqué`)

Si le sémaphore est initialisé à 1 (un seul jeton), on parle d'exclusion
mutuelle.

Ressource critique (ressource à manipuler) : le code dans cette ressource
est appelée la section critique.
