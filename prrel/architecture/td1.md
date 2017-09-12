---
layout: default
title: Archi
section: prrel
---

## Exercice 1

### Conversions en base 2

![](base2.svg)

### Conversions en base 8

![](base8.svg)

### Conversions en base 16

![](base16.svg)

## Exercice 3

### Conversions de la base 10 vers la base 2

![](base10to2.svg)

### Conversions de la base 2 vers la base 10

On peut retirer les premiers zéros, ça ne change pas le résultat. On a donc
(00110101)<sub>2</sub> = (110101)<sub>2</sub>.

![](base2to10.svg)

## Exercice 4

**1.**

~~~
0100 1001 0000 0000 0000 1101
0100 1nnn llll llll hhhh hhhh
LD R1, 1300
~~~

> **Note**
>
> * Pour LL :
>   * 0000 -> 0
>   * 0000 -> 0
> * Pour HH :
>   * 0000 -> 0
>   * 1101 -> 13

**2.**

~~~
0100 1010 0000 0000 0001 1000
0100 1nnn llll llll hhhh hhhh
LD R2, 1800
~~~

> **Note**
>
> * Pour LL :
>   * 0000 -> 0
>   * 0000 -> 0
> * Pour HH :
>   * 0001 -> 1
>   * 1000 -> 8

**3.**

~~~
100 01 010
100 nm mmm
ADD R1, R2
~~~

**4.**

~~~
0100 0001 0000 0001 0000 0000
0100 0nnn aaaa aaaa aaaa aaaa
MV R1, 256#
~~~

> **Note pour arg** : On retire les 0 devant le nombre
>
> 0000 0001 0000 0000 -> 1 0000 0000 -> 2<sup>8</sup> -> 256

Le programme complet est donc :

~~~
LD R1, 1300
LD R2, 1800
ADD R1, R2
MV R1, 256#
~~~

### Conversion des instructions de la base 2 vers la base 16

Puisque que l'on a 2^(4) = 16, pour passer de la base 2 à la base 16, on découpe le nombre par paquets de 4 et ensuite on convertit chaque partie de la base 2 vers la 10 puis de la 10 vers la 16 :

![](conversions.svg)

On a donc :

![](values.svg)
