---
layout: default
title: Complexité
section: l3/s1
---

## Complexité - Fiche de TD n°3

### Exercice 1

Recherche dichotomique / compléxité spatiale

#### 1. Passage par adresse (avec T(n) = O(1))

  S(n) = O(1) + O(1) + ... + O(1)

  S(n) = O(log<sub>2</sub>(n))

#### 2. Recopier le tableau valeur par valeur (avec T(n) = O(n))

  S(n) = O(n) + O(n) + ... + O(n)

  S(n) = O(n * log<sub>2</sub>(n))

#### 3. Copie du sous-intervalle `A[p..q]` (avec T(p, q) = O(q - p+1))

  S(n) = O(n) + O(n/2) + O(n/2^(2)) + ... + O(1)

  S(n) = O(log<sub>2</sub>(n))

### Exercice 2

Faire une fonction qui fait la somme des n termes d'un tableau `A[1..n]`
en version itérative et en version récursive.

> **Remarque**: Les indices des tableaux commencent à 1 en pseudo-code.

#### a. Version intérative

~~~pseudo
fonction sommei(A[1..n]: entier, n: entier) : entier
  var s, i: entier
début
  s <- 0;                               // O(1)
  i <- 1;                               // O(1)

  tant que i <= n faire                 // n + 1 fois
    s <- s + A[i];                      // n fois
  fin tant que

  retourner s;                          // O(1)
fin
~~~

#### b. Version récursive

~~~pseudo
fonction sommer(A[1..n]: entier, n: entier) : entier
début
  si (n = 0) alors                      // n + 1 fois
    retourner 0;                        // O(1)
  sinon
    retourner A[n-1] + sommer(A, n-1);  // O(n)
  fin si
fin
~~~

* Complexité temporelle:
  - Itérative: O(2n + 3)
  - Récursive: O(2n + 2)
* Complexité spatiale (par adresse, en C par exemple):
  - Itérative: O(1)
  - Récursive: O(1)
* Complexité spatiale (par copie, en Pascal par exemple):
  - Itérative: O(n)
  - Récursive: O(n^2)

### Exercice 3

Faire un algorithme qui recherche l'élément maximum d'une liste non-triée puis:

1. Trouver le nombre de comparaisons.
2. Trouver le nombre d'affectations.
3. Faire la même chose pour rechercher le deuxième élément maximum.

#### Algorithme

~~~pseudo
fonction recherche_max(A[1..n]: entiers, n: entier) : entier
  var i, j: entier;
début
  i <- 1;

  pour j de 2 à n
    si (A[j] > A[i])
      j <- i;
    fin si
  fin pour

  retourner A[i];
fin
~~~

#### 1. & 2. Nombre de comparaisons et affectations

| Instruction / Comparaison / Affectation | Comparaisons (défavorable) | Comparaisons (favorale) | Affectations (défavorable) | Affectations (favorable) |
|:-----------:|:---------------------------:|:-----------------------:|:--------------------------:|:------------------:|
| `i <- 1;` |  |  | 1 | 1 |
| `pour j de 2 à n` | n - 1 | n - 1 | n - 1 | n - 1 |
| `si (A[j] > A[i])` | n - 1 | n - 1 |  |  |
| `j <- i` |  |  | n - 2 | 0 |
| `retourner A[i]` |  |  |  |  |
| **Total** | 2n - 3 | 2n - 3 | 2n - 2 | n |

#### 3. Recherche du deuxième élément maximum

~~~pseudo
fonction deuxieme_max(A[1..n]: entier, n : entier) : entier
  var tmp1, tmp2 : entier
début
  tmp1 <- A[1];
  tmp2 <- A[2];

  si (tmp2 > tmp1) alors
    permuter(tmp1, tmp2);
  fin si

  pour i de 3 à n faire
    si (A[i] > tmp1)
      tmp2 <- tmp1;
      tmp1 <- A[i];
    sinon si (A[i] > tmp2)
      tmp2 <- A[i];
    fin si
  fin pour

  retourner tmp2;
fin
~~~

##### Comparaison de la complexité des deux fonctions:

| Fonction | Complexité | Comparaisons | Affectations |
|:--------:|:----------:|:------------:|:------------:|
| `recherche_max` | O(n) | 2n - 3 | n ou 2n - 2 |
| `recherche_dmax`| O(n) | 3n - 7 | 3n - 3 |

##### 1. & 2. Nombre de comparaisons et d'affectations pour la recherche du deuxième élément maximum

| Instruction / Comparaison / Affectation | Comparaisons (défavorable) | Affectations (défavorables) |
|:----------:|:-----------:|:-------------:|
| `tmp1 <- A[1]` | | 1 |
| `tmp2 <- A[2]` | | 1 |
| `si (tmp2 > tmp1)` | 1 | 0 |
| `permuter` | 0 | 3 |
| `pour i de 3 à n` | n - 3 + 1 | n - 3 + 1 |
| `si (A[i] > tmp1)` | n - 3 | |
| `tmp2 <- tmp1;` | | n - 3 |
| `tmp1 <- A[i];` | | n - 3 |
| `sinon si (A[i] > tmp2)` | n - 3 | |
| `tmp2 <- A[i];` |  | n -3 |
| `retourner tmp2`| | |

Pour réduire la complexité de cet algorithme, il faut modifier la structure
utilisée et se servir d'un arbre binaire plutôt que d'un tableau. Dans ce cas, on aurait une complexité:

* T(n) = O(log<sub>2</sub>(n))
* T<sub>max</sub>(n) = O(log<sub>2</sub>(n))
* T<sub>max2</sub>(n) = O(log<sub>2</sub>(n))

