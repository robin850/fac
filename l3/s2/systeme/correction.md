---
layout: default
title: P. Système
section: l3/s2
---

# Correction sujet de Programmation Système - Première session

## Exercice 1 &mdash; Sémaphores

Une zone d'embarquement d'un arrêt de bus contient `MAX` places. Un usager
qui arrive après le bus doit attendre le bus suivant. L'embarquement dans un bus se déroule de la façon suivante :

* A l'arrivée du bus, les usagers déjà présents essaient de monter à bord (ceux qui se présentent à l'arrivé du bus doivent attendre le suivant)
* Lorsque tous les usagers qui attendaient sont montés à bord, le bus démarre.
* Si aucune candidat à l'embarquement n'est présent à l'arrivé du bus, celui-co repart à vide.

On considère qu'à chaque fois qu'un usager est monté à bord, il en fait monter un autre. Le bus initialise cette opération d'embarquement en faisant monter un premier passager.

**1.Ecrire en pseudo langage le code des processus `usager` et `bus` en utilisant les sémaphores.**

Initialisation :

~~~c
// Sémaphore pour indiquer le nombre d'usagers
// maximum.
init(Su, MAX)

// Sémaphore pour faire passer un à un les usagers
// qui montent dans le bus.
init(Sp, 0)

// Sémaphore pour faire attendre le processus `usager`
// avant que le bus arrive.
init(Sbus, 0)
~~~

Pour le processus `usager` :

~~~pseudo
// On ajoute tous les usagers
tant que (usagers) faire
  P(Su)
  nb_usagers++;
fin

// On attend que le bus arrive
P(Sbus)

// On fait monter chaque usager dans le bus
tant que (nb_usagers > 0) faire
  nb_usagers--;
  V(Su)

  si (nb_usagers != 0)
    V(Sp)
  fin
fin
~~~

Pour le processus `bus` :

~~~pseudo
// Le bus arrive
V(Sbus)

// Des passagers attendent, on fait monter
// le premier usager
si (nb_usagers != 0)
  P(Sp)
sinon
  // Le bus peut partir
  V(Sbus)
fin
~~~

**2. On considère à présent que c'est le bus qui fait monter tous les usagers l'un après l'autre. Écrire les 2 processus `usager` et `bus` en pseudo langage en utilisant les sémaphores.**

Dans ce cas, on n'a plus besoin du sémaphore `Sp` car il n'y a plus besoin de
synchronisation entre les deux processus puisque les usagers montent tous dans
le même.

Pour le processus `usager`

~~~pseudo
// On ajoute tous les usagers
tant que (usagers) faire
  P(Su)
  nb_usagers++;
fin

P(Sbus)
~~~

Pour le processus `bus` :

~~~pseudo
// On fait monter chaque utilisateur dans le bus
tant que (nb_usagers > 0) faire
  nb_usagers--;
  V(Su);
fin

// Le bus peut partir ; d'autres usagers peuvent
// attendre.
V(Sbus)
~~~

## Exercice 2 &mdash; IPC - file

**1. Choisir la ou les réponses correctes pour les questions suivantes.**

Une file de message :

* [X] Préserve la structure des messages
* [X] Permet une communication bidirectionnelle
* [ ] Ne peut être utilisée qu'entre un père et un fils

Un segment de mémoire partagée :

* [ ] Est une région de la mémoire accessible à n'importe quel processus
* [X] Est une région de mémoire partageable entre processus qui connaissent
      la clé et l'ont attaché à leur espace d'adressage.

**2. Soient 2 programmes, chacun avec son `main()`, qui communiquent par une file de message. Le premier programme transmet via la file 2 nombres flottants au 2^(ème) programme qui effectue le calcul de la moyenne des 2 nombres et renvoie le resultat au programme 1. Écrire en C le programme 2**

~~~c
// En supposant que l'on ait les structures
struct message {
  long mtype;
  float nombres[2];
};

struct resultat {
  long mtype;
  float valeur;
};

int main() {
  int msqid;
  struct message message;
  struct resultat resultat;

  msqid = msgget((key_t)42, 0);

  // On suppose ici que le type du message est le même
  // que la clé.
  msgrcv(msqid, &message, sizeof(message), (long)42, IPC_NOWAIT);

  // On renvoie le resultat
  resultat.mtype  = 150;
  resultat.valeur = (message.nombres[0] + message.nombres[1]) / 2.0;

  msgsnd(msqid, &resultat, sizeof(resultat), IPC_NOWAIT);

  return 0;
}
~~~

## Exercice 4 &mdash; Mémoire

Étant donné une mémoire composée de 4 cases initialement vides. Soit la suite de références suivante : 1 2 3 4 1 5 3 6 1 3 7.

Donner l'évolution de la mémoire centrale au fur et à mesure de l'accès aux pages ainsi que le nombre de défauts de page en utilisant pour le remplacement de pages :

* L'algorithme FIFO
* L'algorithme LRU

-------------------------------------------------------------------------------

Avec FIFO :

~~~
  1   2   3   4   1   5   3   6   1   3   7
+---+---+---+---+---+---+---+---+---+---+---+
| 1 | 1 | 1 | 1 | 1 | 5 | 5 | 5 | 5 | 5 | 7 |
+---+---+---+---+---+---+---+---+---+---+---+
|   | 2 | 2 | 2 | 2 | 2 | 2 | 6 | 6 | 6 | 6 |
+---+---+---+---+---+---+---+---+---+---+---+
|   |   | 3 | 3 | 3 | 3 | 3 | 3 | 1 | 1 | 1 |
+---+---+---+---+---+---+---+---+---+---+---+
|   |   |   | 4 | 4 | 4 | 4 | 4 | 4 | 3 | 3 |
+---+---+---+---+---+---+---+---+---+---+---+
  d   d   d   d   /   d   /   d   d   d   d
~~~

**Nombre de défauts de page** : 9.

Avec LRU :

~~~
  1   2   3   4   1   5   3   6   1   3   7
+---+---+---+---+---+---+---+---+---+---+---+
| 1 | 1 | 1 | 1 | 1 | 1 | 1 | 1 | 1 | 1 | 1 |
+---+---+---+---+---+---+---+---+---+---+---+
|   | 2 | 2 | 2 | 2 | 5 | 5 | 5 | 5 | 5 | 7 |
+---+---+---+---+---+---+---+---+---+---+---+
|   |   | 3 | 3 | 3 | 3 | 3 | 3 | 3 | 3 | 3 |
+---+---+---+---+---+---+---+---+---+---+---+
|   |   |   | 4 | 4 | 4 | 4 | 6 | 6 | 6 | 6 |
+---+---+---+---+---+---+---+---+---+---+---+
  d   d   d   d   /   d   /   d   /   /   d
~~~

**Nombre de défauts de page** : 7.
