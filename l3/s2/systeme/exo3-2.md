---
layout: default
section: l3/s2
title: P. Système
---

**Sujet**

Toujours les mêmes processus.

Synchroniser A et B avec des sémaphores de telle sorte que l'exécution soit A1 A2, B1 B2, A1 A2 ...

-------------------------------------------------------------------------------

~~~c
S1 = 0;
S2 = 1;

// Programme A
while (true) {
  P(S2);
  A1();
  A2();
  V(S1);
}

// Programme B
while (true) {
  P(S1);
  B1();
  B2();
  V(S2);
}
~~~
