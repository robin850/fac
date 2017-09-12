---
layout: default
title: Graphes
section: l3/s1
---

## Sujet

Consigne: coder et tester les fonctions dans l’ordre.
On déclare une constante `N` qui vaut 9 et un arbre binaire de recherche équilibré :

~~~c
typedef  struct _produit {
  int val, equ;             // valeur stockee et facteur d’ equilibre
  struct _produit *fg, *fd; // pointeurs vers les noeuds fils
} Produit, *Avl ;
~~~

Ecrire les fonctions suivantes:

1. une fonction `main` dans laquelle vous déclarerez un `Avl` `a` initialisé à `NULL` et un tableau t de `N` entiers, initialisé avec les valeurs `{4, 2, 1, 8, 6, 7, 3, 9, 5}`. Ce tableau sera utilisé pour tester les fonctions suivantes sur `a`.
2. une fonction permettant de créer un nouveau `Produit` stockant une valeur `n`.
3. une fonction qui insère une nouvelle valeur `n` à sa place dans `a` sans réaliser de rééquilibrage.
4. une fonction permettant d’afficher `a` sous une forme d’arbre. Les facteurs d’équilibre sont affichés entre parenthèses après la valeur du `Produit`.
5. une fonction qui renvoie la hauteur d’un `Avl`.
6. une fonction qui met à jour les facteurs d’équilibre d’un `Avl`, récursivement à partir de la racine.
7. une fonction de rotation à droite de l’arbre de racine r.
8. une fonction de rotation à gauche de l’arbre de racine r.
9. une fonction d’ insertion d’un `Produit` qui met à jour les facteurs d’équilibre et rééquilibre l’arbre si nécessaire. L’affichage de l’arbre obtenu avec la suite de valeurs de t insérées dans l’ordre avec rééquilibrage donne ceci :
      <pre><code>----9 (0)
--8 (0)
----7 (0)
6 (1)
------5 (0)
----4 (0)
------3 (0)
--2 (-1)
----1 (0)</code></pre>
10. Bonus :
  - a. Ecrire une fonction qui recherche la valeur n dans `a`. La fonction affiche "valeur trouvée" ou "non trouvée" et renvoie le nombre de comparaisons réalisées.
  - b. Créer un arbre équilibré b contenant 1000 valeurs aléatoires (utiliser la fonction rand ; ne pas oublier d’initialiser avec `srand (time(NULL))`). Afficher la hauteur de b .
  - c. Ecrire une fonction de test qui recherche 100 valeurs aléatoires dans b et affiche le nombre maximal de comparaisons réalisées. Exécuter cette fonction plusieurs fois et visualiser les résultats. Quel pourrait être le nombre maximal de comparaisons au pire si l’arbre n’était pas équilibré.

## Code

~~~c
#include <stdlib.h>
#include <stdio.h>

#define N 9

typedef struct _produit {
  int val, equ;
  struct _produit *fg, *fd;
} Produit, *Avl;

Produit *creerProduit(int n) {
  Produit *produit = (Produit *)malloc(sizeof(Produit));

  produit->val = n;
  produit->equ = 0;
  produit->fg  = NULL;
  produit->fd  = NULL;

  return produit;
}

Avl inserer(Avl arbre, int n) {
  if (arbre == NULL)
    return creerProduit(n);

  if (arbre->val > n)
    arbre->fg = inserer(arbre->fg, n);
  else
    arbre->fd = inserer(arbre->fd, n);

  return arbre;
}

void affichage(Avl arbre, int p) {
  int i;

  if (arbre == NULL)
    return;

  affichage(arbre->fd, p+1);

  for (i = 1; i < p; i++)
    printf("--");

  printf("%d (%d)\n", arbre->val, arbre->equ);

  affichage(arbre->fg, p+1);
}

int hauteur(Avl arbre) {
  if (arbre == NULL)
    return 0;

  int gauche = 1 + hauteur(arbre->fg);
  int droite = 1 + hauteur(arbre->fg);

  return gauche > droite ? gauche : droite;
}

void majFacteurEquilibre(Avl arbre) {
  if (arbre == NULL)
    return;

  majFacteurEquilibre(arbre->fd);
  majFacteurEquilibre(arbre->fg);

  arbre->equ = hauteur(arbre->fg) - hauteur(arbre->fd);
}

Avl rotationDroite(Avl r) {
  if (r == NULL)
    return r;

  Avl fg    = r->fg;
  Avl fd_fg = NULL;

  if (fg != NULL) {
    fd_fg  = fg->fd;
    fg->fd = r;
  }

  r->fg = fd_fg;

  return fg;
}

Avl rotationGauche(Avl r) {
  if (r == NULL)
    return r;

  Avl fg_fd = NULL;
  Avl fd    = r->fd;

  if (fd != NULL) {
    fg_fd  = fd->fg;
    fd->fg = r;
  }

  r->fd = fg_fd;

  return fd;
}

Avl rgd(Avl arbre) {
  arbre->fg = rotationGauche(arbre->fg);
  arbre     = rotationDroite(arbre);

  return arbre;
}

Avl rdg(Avl arbre) {
  arbre->fd = rotationDroite(arbre->fd);
  arbre     = rotationGauche(arbre);

  return arbre;
}

Avl reequilibrer(Avl arbre) {
  if (arbre == NULL || arbre->fd == NULL || arbre->fg == NULL)
    return arbre;

  switch (arbre->equ) {
    case  1:
    case  0:
    case -1: // Aucun déséquilibre
      break;
    case -2: // Déséquilibre à droite
      if (arbre->fd->equ == 0 || arbre->fd->equ == -1)
        arbre = rotationGauche(arbre);
      else if (arbre->fd->equ == 1)
        arbre = rdg(arbre);

      break;
    case 2: // Déséquilibre à gauche
      if (arbre->fg->equ == -1)
        arbre = rgd(arbre);
      else if (arbre->fg->equ == 0 || arbre->fg->equ == 1)
        arbre = rotationDroite(arbre);

      break;
  }

  return arbre;
}

Avl insertionEquilibree(Avl arbre, int n) {
  arbre = inserer(arbre, n);

  majFacteurEquilibre(arbre);
  arbre = reequilibrer(arbre);
  majFacteurEquilibre(arbre);

  return arbre;
}

int main() {
  // +-----------------+
  // |   Question  1   |
  // +-----------------+
  Avl arbre = NULL;
  int tab[] = {4, 2, 1, 8, 6, 7, 3, 9, 5};
  int i;

  // +-----------------+
  // | Question 3 et 9 |
  // +-----------------+
  for (i = 0; i < N; i++)
    // arbre = inserer(arbre, tab[i]);
    arbre = insertionEquilibree(arbre, tab[i]);

  // +-----------------+
  // |   Question  4   |
  // +-----------------+
  affichage(arbre, 1);

  // +-----------------+
  // |   Question  5   |
  // +-----------------+
  printf("Hauteur de l'arbre %d.\n", hauteur(arbre));

  // +-----------------+
  // |   Question  6   |
  // +-----------------+
  majFacteurEquilibre(arbre);
  // affichage(arbre, 1);

  // +-----------------+
  // | Question 7 et 8 |
  // +-----------------+
  arbre = rotationDroite(arbre);
  // affichage(arbre, 1);
}
~~~
