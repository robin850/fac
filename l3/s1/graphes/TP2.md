---
layout: default
title: Graphes
section: l3/s1
---

## Sujet

Consigne: coder et tester les fonctions dans l’ordre.

Un arbre binaire de recherche permettant de stocker des entiers est déclaré ainsi :

~~~c
typedef struct _noeud {
  int val;                // valeur stockee
  struct _noeud *fg, *fd; // pointeurs vers les noeuds fils
} Noeud, *Abr;
~~~

1. Ecrire une fonction main dans laquelle vous déclarerez un `Abr` a initialisé à NULL et un tableau t de 9 entiers, initialisé avec les valeurs `{4, 2, 1, 8, 6, 7, 3, 9, 5}` . Ce tableau sera utilisé pour tester les fonctions suivantes sur a.
2. Ecrire une fonction permettant de créer un nouveau `Noeud` stockant une valeur n.
3. Ecrire une fonction permettant d’ insérer une nouvelle valeur n à sa place dans a.
4. Ecrire une fonction permettant d’ afficher par ordre croissant les valeurs présentes dans a.
5. Ecrire une fonction permettant d’ afficher par ordre décroissant les valeurs présentes dans a.
6. Ecrire une fonction permettant d’afficher a sous une forme d’arbre. Par exemple, l’abre obtenu avec la suite de valeurs de t serait affiché ainsi :
7. Ecrire une fonction qui recherche la valeur n dans a et renvoie 1 si trouvée, 0 sinon.
8. Ecrire une fonction qui renvoie un pointeur vers le noeud stockant la plus petite des valeurs contenues dans a.
9. Ecrire une fonction qui renvoie la somme et le nombre de valeurs contenues dans a.
10. Ecrire une fonction qui renvoie la hauteur de l’arbre.
11. Ecrire une fonction qui permet de supprimer une valeur n de a tout en maintenant la propriété d’arbre binaire de recherche. Tester en supprimant 1, puis 8, puis 4.
12. Bonus : les valeurs à insérer sont lues à partir d’un fichier (fichier texte contenant des entiers séparés par des espaces) et l’arbre obtenu est sauvegardé dans un autre fichier (texte) sous le format du point 6.

## Code

~~~c
#include <stdio.h>
#include <stdlib.h>

typedef struct _noeud {
  int val;                // Valeur
  struct _noeud *fg, *fd; // Pointeurs fils gauche/droit
} Noeud, *Arbre;

Noeud *creerNoeud(int val) {
  Noeud *noeud = (Noeud *)malloc(sizeof(Noeud));

  noeud->val = val;
  noeud->fg  = NULL;
  noeud->fd  = NULL;

  return noeud;
}

Arbre inserer(Arbre arbre, int n) {
  if (arbre == NULL)
    return creerNoeud(n);

  if (arbre->val > n)
    arbre->fg = inserer(arbre->fg, n);
  else
    arbre->fd = inserer(arbre->fd, n);

  return arbre;
}

void affichageCroissant(Arbre arbre) {
  if (arbre == NULL)
    return;

  affichageCroissant(arbre->fg);
  printf("%d\n", arbre->val);
  affichageCroissant(arbre->fd);
}

void affichageDecroissant(Arbre arbre) {
  if (arbre == NULL)
    return;

  affichageDecroissant(arbre->fd);
  printf("%d\n", arbre->val);
  affichageDecroissant(arbre->fg);
}

void affichage(Arbre arbre, int p) {
  int i;

  if (arbre == NULL)
    return;

  affichage(arbre->fd, p+1);

  for (i = 1; i < p; i++)
    printf("--");

  printf("%d\n", arbre->val);

  affichage(arbre->fg, p+1);
}

int recherche(Arbre arbre, int n) {
  if (arbre == NULL)
    return 0;

  if (arbre->val == n)
    return 1;
  else if (arbre->val > n)
    return recherche(arbre->fg, n);
  else
    return recherche(arbre->fd, n);
}

Arbre plusPetiteValeur(Arbre arbre) {
  if (arbre->fg == NULL)
    return arbre;

  return plusPetiteValeur(arbre->fg);
}

void sommeEtNbValeurs(Arbre arbre, int *somme, int *nbValeurs) {
  if (arbre == NULL)
    return;

  *somme = *somme + arbre->val;
  *nbValeurs = *nbValeurs + 1;

  sommeEtNbValeurs(arbre->fg, somme, nbValeurs);
  sommeEtNbValeurs(arbre->fd, somme, nbValeurs);
}

int hauteur(Arbre arbre) {
  if (arbre == NULL)
    return 0;

  int gauche = 1 + hauteur(arbre->fg);
  int droite = 1 + hauteur(arbre->fd);

  if (gauche > droite)
    return gauche;
  else
    return droite;
}

void supprimerMax(Arbre arbre) {
  Noeud *plusGrand = arbre->fd;
  Noeud *precedent = arbre;

  while (plusGrand->fd) {
    precedent = precedent->fd;
    plusGrand = plusGrand->fd;
  }

  // On ecrase la valeur du noeud où était
  // la plus grand valeur.
  precedent->fd = NULL;

  // On modifie la valeur de la racine de l'arbre
  arbre->val = plusGrand->val;
}

void supprimer(Arbre arbre, int n) {
  if (arbre == NULL)
    return;

  if (arbre->val == n)
    supprimerMax(arbre);
  else if (arbre->val > n)
    supprimer(arbre->fg, n);
  else
    supprimer(arbre->fd, n);
}

int main() {
  int i, n, somme = 0, nbValeurs = 0;

  // +-----------------+
  // |   Question  1   |
  // +-----------------+
  Arbre arbre   = NULL;
  int tableau[] = {4, 2, 1, 8, 6, 7, 3, 9, 5};

  // +-----------------+
  // |   Question  3   |
  // +-----------------+
  for (i = 0; i < 9; i++)
    arbre = inserer(arbre, tableau[i]);

  // +-----------------+
  // | Question 4 et 5 |
  // +-----------------+
  // affichageCroissant(arbre);
  // affichageDecroissant(arbre);

  // +-----------------+
  // |   Question  6   |
  // +-----------------+
  affichage(arbre, 1);

  // +-----------------+
  // |   Question  7   |
  // +-----------------+
  printf("Rechercher une valeur : ");
  scanf("%d", &n);

  if (recherche(arbre, n) == 1)
    printf("La valeur %d est presente dans l'arbre.\n\n", n);
  else
    printf("La valeur %d n'est pas presente dans l'arbre.\n\n", n);

  // +-----------------+
  // |   Question  8   |
  // +-----------------+
  printf("Plus petite valeur : %d.\n\n", plusPetiteValeur(arbre)->val);

  // +-----------------+
  // |   Question  9   |
  // +-----------------+
  sommeEtNbValeurs(arbre, &somme, &nbValeurs);
  printf("Le tableau contient %d valeurs dont la somme est %d.\n\n", nbValeurs, somme);

  // +-----------------+
  // |   Question 10   |
  // +-----------------+
  printf("Hauteur de l'arbre : %d.\n\n", hauteur(arbre));

  // +-----------------+
  // |   Question 11   |
  // +-----------------+
  supprimer(arbre, 6);
  printf("Affichage de l'arbre une fois la valeur 6 supprime : \n");
  affichage(arbre, 1);

  return 0;
}
~~~
