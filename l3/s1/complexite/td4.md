---
layout: default
title: Complexité
section: l3/s1
---

## Complexité - Fiche de TD n°4

### Exercice 3

#### 1. T(n) = 2 * T(n/2) + n

> **Rappel**: Théorème de la méthode générale:
>
> T(n) = a * T(n / b) + f(n)
>

Ici on a, a = 2, b = 2, f(n) = n.

De plus, on a:

n<sup>log<sub>b</sub>(a)</sup> = n<sup>log<sub>2</sub>(2)</sup> = n = f(n)

On est donc dans le cas n°1. D'où:

T(n) = O(n<sup>log<sub>b</sub>(a)</sup> * log<sub>2</sub>(n))
