---
layout: default
section: l3/s2
title: P. Système
---

# Correction sujet de Programmation Système - Seconde session

## Exercice 1 - Sémaphores

Le repas des Chevaliers : Des chevaliers doivent déjeuner ensemble selon cette règle :
tant que les N chevaliers ne sont pas tous arrivés au lieu du repas, chacun d'eux doit
attendre qu'il soient invités par le dernier arrivant. Lorsque le N-ième chevalier
arrive, il invite tous ceux qui l'attendaient à prendre le repas et ces derniers
peuvent donc commencer leur repas. En utilisant des sémaphores dont vous donnerez la
signification et la valeur initiale, écrire en pseudo langage le programme qui sera
exécuté par chaque processus chevalier.

-------------------------------------------------------------------------------

Initialisation des sémaphores :

~~~c
// Sémaphore pour savoir combien de chevaliers
// vont prendre le repas
init(Schevaliers, N);

// Sémaphore pour savoir si on peut prendre le
// repas ou non.
init(Srepas, 0);
~~~

Pour chaque processus chevalier :

~~~pseudo
// Un chevalier arrive
P(Schevaliers)
compteur++;

// Le dernier chevalier est là
si (compteur == N)
  V(Srepas)
fin

P(Srepas)

// Code pour prendre le repas
~~~

## Exercice 2 - File de messages

Ecrire en langage C 2 programmes A et B séparés (avec chacun sa fonction `main`)
qui communiquent par file de message et s'exécutent en boucle infinie.

Le programme A répète l'itération suivante : il effectue la saisie au clavier d'une
chaîne de caractères qu'il envoie à B par l'intermédiaire de la file de message.
Le programme B calcul le nombre de caractères de la chaîne reçue et envoie ce
nombre à A qui l'affiche à l'écran.

En supposant que l'on ait les structures :

~~~c
struct chaine {
  long mtype;
  char valeur[255];
}

struct longueur {
  long mtype;
  int valeur;
}
~~~

Pour le programme A :

~~~c
int main() {
  int msqid, i, n;

  struct chaine chaine;
  struct longueur longueur;

  char tampon[255];

  msqid = msgget((key_t)42, 0750 | IPC_CREAT);

  chaine.mtype   = 123;
  longueur.mtype = 456;

  while (1) {
    // On lit l'entrée de l'utilisateur
    n = read(0, tampon, 255));

    for (i = 0; i < n; i++)
      chaine.valeur[i] = tampon[i];

    msgsnd(msqid, &chaine, sizeof(chaine), IPC_NOWAIT);

    // On attend un message du processus B
    msgrcv(msqid, &longueur, sizeof(longueur), (long)456, 0);

    write(1, "Taille de la chaine : ", 22);
    write(1, &(longueur.valeur), sizeof(int));
  }

  // Si jamais on sort du while, on nettoie la file.
  msgctl(msqid, IPC_RMID, NULL);
}
~~~

Pour le programme B :

~~~c
int main() {
  int msqid;
  struct chaine chaine;
  struct longueur longueur;

  msqid = msgget((key_t)42, 0);

  chaine.mtype   = 123;
  longueur.mtype = 456;

  while (1) {
    msgrcv(msqid, &chaine, sizeof(chaine), (long)123, IPC_NOWAIT);

    longueur.valeur = 0;

    while (chaine.valeur != '\0') {
      longueur.valeur++;
      chaine.valeur++;
    }

    msgsnd(msqid, &longueur, sizeof(longueur), IPC_NOWAIT);
  }
}
~~~

## Exercice 3 - Mémoire

Soit un programme P qui fait référence aux pages suivantes en mémoire virtuelle :

1, 2, 3, 1, 2, 3, 4, 5, 6, 7, 5, 4, 1, 3, 4, 2, 1

Le système dispose de 3 cases pour exécuter le programme P.

Donnez la suite des évictions de pages (virtuelles) qu'effectuera le gestionnaire
de mémoire virtuelle du système pour exécuter P. Vous donnerez pour les 3 politiques
suivantes d'éviction :

* LRU
* FIFO
* OPTIMAL

Vous donnerez le nombre de défauts de page.

-------------------------------------------------------------------------------

> Dans la correction je n'ai pas fait le `OPTIMAL` puisqu'on ne l'a pas vu en cours. :-)

Pour LRU :

~~~
  1   2   3   1   2   3   4   5   6   7   5   4   1   3   4   2   1
+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+
| 1 | 1 | 1 | 1 | 1 | 1 | 4 | 4 | 4 | 7 | 7 | 7 | 7 | 3 | 3 | 3 | 3 |
+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+
|   | 2 | 2 | 2 | 2 | 2 | 2 | 5 | 5 | 5 | 5 | 4 | 4 | 4 | 4 | 2 | 2 |
+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+
|   |   | 3 | 3 | 3 | 3 | 3 | 3 | 6 | 6 | 6 | 6 | 1 | 1 | 1 | 1 | 1 |
+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+
  d   d   d   /   /   /   d   d   d   d   /   d   d   d   /   d   /
~~~

**Nombre de défauts de page** : 11.

Pour FIFO :

~~~
  1   2   3   1   2   3   4   5   6   7   5   4   1   3   4   2   1
+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+
| 1 | 1 | 1 | 1 | 1 | 1 | 4 | 4 | 4 | 7 | 7 | 7 | 1 | 1 | 1 | 2 | 2 |
+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+
|   | 2 | 2 | 2 | 2 | 2 | 2 | 5 | 5 | 5 | 5 | 5 | 5 | 3 | 3 | 3 | 1 |
+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+
|   |   | 3 | 3 | 3 | 3 | 3 | 3 | 6 | 6 | 6 | 4 | 4 | 4 | 4 | 4 | 4 |
+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+
  d   d   d   /   /   /   d   d   d   d   /   d   d   d   /   d   /
~~~

**Nombre de défauts de page** : 11.
