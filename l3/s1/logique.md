---
layout: default
title: P. Logique
section: l3/s1
---

Une clause est une formule de la forme <code>L1 &or; L2 &or; L3 ...</code> où `Li` est un littéral, qui est négatif s'il est de la forme `¬Pi` et positif s'il est de la forme `Pi`.

Une clause de Horn est une clause ayant **au plus** une clause positive.

## Définitions

* Fait: Clause ayant au plus un litéral (positif).
* Règle: Clause ayant un littéral positif et au moins un litéral négatif.
* Requête: Clause sans litéral positif.

## Partie 1 TP Prolog

~~~prolog
estVide([]).

appartient(X, [X|_]).
appartient(X, [_|Q]):- appartient(X, Q).

estUnEnsemble([]).
estUnEnsemble([X|Q]):- not(appartient(X, Q)), estUnEnsemble(Q).

estOrdonnee([]).
estOrdonnee([_|[]]).
estOrdonnee([X|[Y|Q]]):- X @=< Y, estOrdonnee([Y|Q]).

union([], L2, L2).
union([X|L1], L2, L3):- appartient(X, L2), !, union(L1, L2, L3).
union([X|L1], L2, [X|L3]):- union(L1, L2, L3).

intersection([], _, []).
intersection([X|L1], L2, [X|L3]):- appartient(X, L2), !, intersection(L1, L2, L3).
intersection([_|L1], L2, L3):- intersection(L1, L2, L3).

difference([], _, []).
difference([X|L1], L2, L3):- appartient(X, L2), !, difference(L1, L2, L3).
difference([X|L1], L2, [X|L3]):- difference(L1, L2, L3).
~~~
