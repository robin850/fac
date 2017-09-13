---
layout: default
title: Graphes
section: l3/s1
---

## Ressources utiles (Hum, ça va ?)

* [À la découverte des algorithmes de graphes](https://zestedesavoir.com/tutoriels/681/a-la-decouverte-des-algorithmes-de-graphe/)
* [Théorie des graphes : Fondements](https://fr.wikiversity.org/wiki/Th%C3%A9orie_des_graphes/Fondements)
* [Sujet d'examen 2014-2015](https://drive.google.com/uc?export=download&id=0B1b6pH21vC4edFRGVXBSN2tXNm8)
* [Sujet deuxième session 2013-2014](https://drive.google.com/uc?export=download&id=0B1b6pH21vC4eM0ZvR3pXUkVibHM)
* TPs de structure de données de l'année dernière (si ça peut être utile):
  - [TP n°1](TP1.html)
  - [TP n°2](TP2.html)
  - [TP n°3](TP3.html)

## Explications de bases

### Vocabulaire

Un graphe est **non orienté** quand les sommets sont reliés entre eux par de simples traits (des **arcs**).

* Tous les noeuds reliés à un sommet sont appelés ses **voisins**.
* Le **degrés** (noté `d(a)` pour le sommet a) d'un sommet correspond à son nombre de voisins.
*

Un graphe est **orienté** lorsque les sommets sont reliés entre eux par des flèches
(des **arêtes**).

* Tous les noeuds incident (arrivant dessus) à un même noeud consituent ses **prédécesseurs**.
* Tous les noeuds reliés à un autre noeud par le biais d'une arête constituent
ses **successeurs**.
* Le nombre de prédécesseurs d'un noeud correspond à son **degrès entrant** (noté <code>d<sup>-</sup>(a)</code> pour le sommet a) et le nombre de successeurs correspond à son **degrés sortant** (noté <code>d<sup>+</sup>(a)</code> pour le sommet a).

### Matrice et liste d'adjacence

Par exemple, pour le graphe:

<pre style="background: white"><code>
                +-----+                    +-----+
                |  1  |------------------->|  2  |
                +-----+                    +-----+
                   |
                   |
                   |
                   v
                +-----+
                |  3  |
                +-----+
</code></pre>

La matrice d'adjacence correspondante est (la **première** ligne est **le point de départ**):

<table>
  <tr>
    <th>Graphe orienté</th>
    <th>Graphe non-orienté</th>
  </tr>

  <tr>
    <td>
<pre class="highlight" style="border: none"><code>
          +---+-----------+
          |   | 1 | 2 | 3 |
          |---|-----------|
          | 1 | 0 | 0 | 0 |
          | 2 | <span class="nf">1</span> | 0 | 0 |
          | 3 | <span class="nf">1</span> | 0 | 0 |
          +---+-----------+
</code></pre>
    </td>

    <td>
<pre class="highlight" style="border:none"><code>
          +---+-----------+
          |   | 1 | 2 | 3 |
          |---|-----------|
          | 1 | 0 | <span class="nf">1</span> | <span class="nf">1</span> |
          | 2 | <span class="nf">1</span> | 0 | 0 |
          | 3 | <span class="nf">1</span> | 0 | 0 |
          +---+-----------+
</code></pre>
    </td>
  </tr>
</table>

Et la liste d'adjacence correspondante est:

<table>
  <tr>
    <th>Graphe orienté</th>
    <th>Graphe non orienté</th>
  </tr>

  <tr>
    <td>
<pre><code>
    +-----+   _____    _____
    |  1  |->|  2  |->|  3  |->Nil
    +-----+   -----    -----
    |  2  |-> Nil
    +-----+
    |  3  |-> Nil
    +-----+
</code></pre>
    </td>
    <td>
<pre><code>
    +-----+   _____    _____
    |  1  |->|  2  |->|  3  |->Nil
    +-----+   -----    -----
    |  2  |->|  1  |->Nil
    +-----+   -----
    |  3  |->|  1  |->Nil
    +-----+   -----
</code></pre>
    </td>
  </tr>
</table>

## Algorithmes utiles

> **Coloration**: Pour représenter le parcours, l'algorithme utilise
> la coloration avec la convention:
>
>   * Blanc: Le sommet x n'est pas visité.
>   * Gris: Le sommet x est visité pour la première fois ou
>     bien un de ses voisins n'est pas visité.
>   * Noir: Sommet et voisins visités.

### Parcours en Largeur (PeL)

~~~pseudo
procédure PeL(Entrée: G = <S, A> : Graphe)
  var p, d
début
  // p et d sont des tableaux et stockent la liste
  // des prédécesseurs de x et la date à laquelle x
  // a été traité.
  pour x dans S faire
    c(x) = Blanc
    p(x) = Nil
    d(x) = +∞
  fin

  // c(x) représente la couleur de x
  d(S) = 0
  c(S) = Gris
  F    = {S}

  tant que F != Ø faire
    x = tete(F)

    pour y dans Adj(x) faire
      si c(y) = Blanc alors
        p(y) = x
        d(y) = d(x) + 1
        c(y) = Gris
        Enfiler(F, y)
      fin
    fin

    c(x) = Noir
    Defiler(F)
  fin tant que
fin
~~~

### Parcours en Profondeur (PeP)

~~~pseudo
procédure PeP(Entrée: G = <S, A> : Graphe)
  var p, d, f
début
  pour x dans S faire
    p(x) = Nil
    c(x) = Blanc
  fin

  temps = 0; // Variable globale

  pour x dans S faire
    si c(x) = Blanc alors
      visiter(x);
    fin
  fin
fin

procédure visiter(Entrée: S : Sommet)
début
  c(x) = Gris
  temps = temps + 1;
  d(x) = temps

  pour y dans Adj(x) faire
    si c(y) = Blanc alots
      p(y) = x
      visiter(y)
    fin
  fin

  c(x) = Noir
  temps = temps + 1;
  f(x) = temps
fin
~~~
