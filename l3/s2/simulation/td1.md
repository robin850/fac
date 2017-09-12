---
layout: default
title: Simu
section: l3/s2
---

### Exercice 3

1) Les temps d'inter-arrivée suivent une loi exponentielle de moyenne = 5 minutes.
Les temps de services suivent une loi triangulaire. (avec min = 2, max = 6, mode = 3).

#### Pour l'exponentielle

La variable aléatoire X suit la loi exponentielle de moyenne 5 (min) et donc a un paramètre

<center>&lambda; = 1 / E(X) = 1 / 5 = 0,2 (pièce / min)</center>

> **Note** : &lambda; est le taux d'arrivée des pièces dans le système.

<center>X &isin; [0; +&infin;) &nbsp; &nbsp; &fnof;(x) = x / e<sup>&lambda;x</sup></center>

**Méthode des fonctions inverses**

<center>F(x) = <sub>-&infin;</sub>&int;<sup>x</sup> &fnof;(t)dt</center>
<center>F(x) = 1 - e<sup>- &lambda;x</sup></center>

<p></p>

<center>1 - e<sup>- &lambda; x</sup> = z</center>
<center>1 - z = e<sup>- &lambda; x</sup></center>
<center>ln(1 - z) = - &lambda; x</center>

<p></p>

<center>x = ln(1 - z) / (-&lambda;)</center>
<center>F<sup>-1</sup>(z) = ln(1 - z) / (-&lambda;)</center>

#### Pour la triangulaire

<center>F(x) = <sub>-&infin;</sub> &int; <sup>x</sup> &fnof;(t)dt</center>
<center><sub>-&infin;</sub> &int; <sup>+&infin;</sup> &fnof;(x)dx = 1</center>

Dans notre cas, en prenant min = 2, max = 6 et mode = 3, on a :

f (x) =

* 0.5 * (x - 2) si 2 &le; x < 3
* 0.5 * (2 - x/3) si 3 &le; x < 6
* 0 sinon

De plus, on a :

* X = uniforme distribuée entre 2 et 6.
* Y = uniforme distribuée entre 0 et 1 / 2.

C'est ce qui explique, dans le code de génération des temps de service le code suivant :

~~~c
if (y <= f(x))
  return x;
else
  return fct_gen_ts();
~~~

<center>E(x) = (a + b + c) / 3 = (2 + 3 + 6) / 3 = 3.66 min</center>

b) &lambda; = 0,2 clients / min

c) &mu; = 1 / E(x) = 1 / 3.66 = 0.27 clients / min


Pour déstabiliser le système, nous pourrions modifier la valeur de &mu;

<center>&lambda; = le taux des clients = 1 / E(X)</center>

<center>&mu; = le taux de service du serveur = 1 / E(Y)</center>

X est la variable aléatoire qui représente le temps d'inter-arrivées. Y est la variable aléatoire qui représente le temps de traitement (aussi appelé le temps de service).

Prenons la loi triangulaire : min = 3, max = 8, mode = 5

Puisqu'il s'agit d'une loi uniforme, l'aire = 1. Pour calculer la hauteur (= la maximale), il nous suffit d'utiliser la formulaire de l'aire qui est aire = base * hauteur / 2. On a donc :

<center>(8 - 2) &times; h / 2 = 1 &#8658; h = 2 / 6 = 1 / 3</center>

&Rho; &ge; 1 &#8658;

* On peut augmenter &lambda; (0,20 => diminuer E(X))
* On peut diminuer &mu; (0,27 => augmenter E(Y))

Par exemple, on choisit un temps d'inter-arrivées = 3 minutes.

<center>&#8658; &lambda;' = 1 / E(X) = 1 / 3 = 0,33 clients / min</center>

<center>&#8658; &Rho;' = &lambda;' / &mu; = 0,33 / 0,27 > 1</center>

<center>&#8658; Congestion, à priori</center>

* Le nombre de clients dans la file augmente continuellement.
* Le serveur transite "non-stop".
