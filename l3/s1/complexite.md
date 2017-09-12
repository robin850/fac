---
layout: default
title: Complexité
section: l3/s1
---

## Trucs à savoir

### Théorème de la méthode générale / master theorem

Pour résoudre les récurrence du genre T(n) = a &middot; T(n/b) + f(n) avec a &ge; 1 et b &gt; 1.

* **Cas 1**: n<sup>log<sub>b</sub>(a)</sup> > f(n)
* **Cas 2**: n<sup>log<sub>b</sub>(a)</sup> = f(n)
* **Cas 3**: n<sup>log<sub>b</sub>(a)</sup> < f(n)

Une fois le cas trouvé, on détermine la complexité:

* **Cas 1**: T(n) = O(n<sup>log<sub>b</sub>(a)</sup>).
* **Cas 2**: T(n) = O(n<sup>log<sub>b</sub>(a)</sup> &middot; log<sub>2</sub>(n))
* **Cas 3**: T(n) = O(f(n)).

### Propriétés du logarithmes

* log<sub>n</sub>(m<sup>p</sup>) = p &middot; log<sub>n</sub>(m)
* log<sub>n</sub>(n) = 1

## Documents

* [Fiche de TD n°3](complexite/td3.html)
* [Fiche de TD n°4](complexite/td4.html)
* [Correction DS](complexite/ds.html)
