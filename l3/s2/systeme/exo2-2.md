---
layout: default
section: l3/s2
title: P. Système
---

**Sujet**

Reprendre l'exercice précédent.

Synchroniser les processus A et B à l'aide de sémaphores de telle sorte que si A est dans A1 alors B n'est pas dans B1 et si A est dans A2, B n'est pas dans B2.

On utilise l'exlusion mutuelle.

`P(S)` et `V(S)` entoure la section critique lorsque l'on utilise l'exclusion mutuelle.

-------------------------------------------------------------------------------

~~~c
// Initialisation
S = 1;
S2 = 1;

// Programme A
while (true) {
  P(S);
  A1();
  V(S);
  P(S2)
  A2();
  V(S2);
}

// Programme B
while (true) {
  P(S);
  B1();
  V(S);
  P(S2);
  B2();
  V(S2);
}
~~~
