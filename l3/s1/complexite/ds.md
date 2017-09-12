---
layout: default
title: Complexité
section: l3/s1
---

## Examen Complexité - 1<sup>ère</sup> session 2015-2016

### Exercice 1

On a `T(n) = 2 * T(n/2) + O(1)`. Consignes :

1. Donner la complexité en utilisant la méthode générale.
2. Donner la complexité en utilisant la méthode de substitution.
3. Quelle est l'autre appelation du théorème de la méthode générale ?

#### Réponses:

1. Ici on a `a = 2`, `b = 2` et `f(n) = n^(0)`.

    De plus <code>n<sup>log<sub>2</sub>(2)</sup> = n<sup>1</sup> = n </code> d'ou ε = 1 (on est dans le cas n°1)

    T(n) = O(n<sup>log<sub>2</sub>(2)</sup>) = O(n)

2. T(n) = T(&lfloor;n/2&rfloor;) + T(&lceil;n/2&rceil;) + 1 (*&lfloor; x &rfloor; représente la partie entière de x*)

    T(n) = 2 * T(n/2) + O(1)

3. Le théorème de la méthode générale est aussi appelé théorème maître ou théorème master.



### Exercice 2

On cherche ici à trouver l'élément maximal avec des listes **triées** par ordre croissant.

On possède 3 listes A1, A2, A3 avec |A1|, |A2|, |A3| le nombre d'élément des 3 listes. Consignes :

1. Proposer un tel algorithme.
2. Donner la complexité asymptotique.
3. Donner la complexité pour k listes.

#### Réponses

**1.**

~~~pseudo
fonction min(A1[1..|A1|]: entier, A2[1..|A2|]: entier, A3[1..|A3|]: entier): entier
var min : entier;
début
  min <- A1[1]

  si (min > A2[1]) alors
    min <- A2[1];
  fin si

  si (min > A3[1]) alors
    min <- A3[1];
  fin si

  renvoyer min;
fin
~~~

**2.** Complexité: T(n) = O(1), le coup est toujours constant quelque soit la taille des listes.

**3.** Même complexité, le coup sera aussi constant pour k listes.

### Exercice 3


On souhaite réaliser un tri par paquets.

En entrée, on a un ensemble A à n éléments et on a 0 ≤ A[i] < 1. On possède également un tableau auxiliaire B[0], l[n-1] de listes chaînées. Voici l'algorithme utilisé:


~~~pseudo
début
  pour i <- 1 à n faire
    // insérer A[i] dans B[|_ n * A[i] _|]
  fin

  pour i <- 0 à (n-1) faire
    // trier la liste B[i] via tri par insértion
  fin

  // concaténer les listes B[i]
fin
~~~


Consignes:


1. Illustrer l'algorithme avec:
   A = {0.77; 0.15; 0.17; 0.63; 0.38; 0.21; 0.89; 0.53; 0.71; 0.62}
2. Donner la complexité.

#### Réponses

**1.**

~~~
         B
     +-------+
   0 |       | -> NIL
     +-------+
   1 |       | -> [0.15] -> [0.17] -> NIL
     +-------+
   2 |       | -> [0.21] -> NIL
     +-------+
   3 |       | -> [0.38] -> NIL
     +-------+
   4 |       | -> NIL
     +-------+
   5 |       | -> [0.53] -> NIL
     +-------+
   6 |       | -> [0.63] -> [0.62] -> NIL
     +-------+
   7 |       | -> [0.77] -> [0.71] -> NIL
     +-------+
   8 |       | -> [0.89] -> NIL
     +-------+
   9 |       | -> NIL
     +-------+
~~~

**2.** <code>T(n) = O(n<sup>2</sup>)</code> (dans le cas défavorable) et <code>T(n) = Ω(n)</code>


### Exercice 4

On possède un ensemble S ayant n ≥ 2 entiers distincts (non trié). On souhaite trouver un algorithme qui affiche tous les couples x,y ∈ S<sup>2</sup> | |x-y| ≤ 1/(n-1) * (max(S) - min(S)). Consignes :

1. Proposer un algorithme naïf et sa complexité.
2. Propoer un algorithme en O(n).

#### Réponses

**1.**

~~~pseudo
Naïf(S: tableau[1..MAX] d'entiers, n: entier)
  var i, max, min : entier;
début
  max <- S[1];
  min <- S[1];

  pour i = 2 à n faire
    si (S[i] > Max) alors max <- S[i];
    si (S[i] < Min) alors min <- S[i];

    // L'algorithme a une complexité en O(n^2) car il
    // y a deux boucles imbriquées.
    pour i <- 1 à n faire
      pour j <- 1 à n faire
        si abs(x - y) <= 1 / (n - 1) + (max - min)
        alors écrire(x, y);
        fin si
    fin pour
  fin pour
fin
~~~

**2.** Chercher soit même. :trollface:
