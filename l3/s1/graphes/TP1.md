---
layout: default
title: Graphes
section: l3/s1
---

## Sujet de TP

Consigne : coder et tester les fonctions dans l’ordre. L’objectif est de gérer un stock de produits. On dispose des déclarations de types suivantes :

~~~c
typedef char Chaine[15];

typedef struct _produit {
  Chaine idP; // Identifiant produit
  int nbP;    // Quantité en stock
  float PU;
  struct _produit *suiv, *prec ; // pointeurs vers le suivant et le précédent
} Produit, *Liste;

typedef struct { Liste debut, fin ; } Stock ;
~~~

Il est demandé d’écrire les fonctions suivantes en les testant au fur-et-à-mesure :

1. une fonction de création d’une cellule Produit, les données étant passées en paramètres ;
2. une fonction d’insertion en fin de liste d’un nouveau Produit dans un Stock ;
3. une fonction d’affichage du contenu d’un Stock ;
4. une fonction principale qui crée monMagasin de type Stock et y insère successivement et en fin de liste les Produits suivants : ("CORDE",4, 60.0), ("CHAUSSONS",52,35.5), ("MOUSQUETON",74, 12.9), ("CASQUE",12, 21.3), puis affiche le contenu de monMagasin. Vous complèterez ce main au fur-et-à mesure des questions suivantes par des exemples d’appel de fonctions.
5. une fonction qui recherche le Produit d’identifiant nomP et renvoie un pointeur vers ce Produit s’il existe dans le Stock, NULL sinon ;
6. une fonction qui décrémente de n la quantité en Stock du Produit d’identifiant nomP ;
7. une fonction de recherche et d’affichage des Produits dont la quantité en Stock est inférieure
à un seuil donné n.
8. une fonction qui "détache" du Stock le Produit pointé par aEnlever, c’est-à-dire l’enlève du Stock
mais sans le supprimer complètement ;
9. une fonction qui supprime (complètement, avec libération de l’espace mémoire) le Produit d’iden-
tifiant nomP d’un Stock ;
10. une fonction qui insère un Produit à sa place dans un Stock trié selon l’ordre des idP croissants ;
11. une fonction de tri des Produits du Stock selon les idP croissants (par ex., en "détachant" du stock les Produits non triés puis en les insérant à leur place).
12. Bonus 1 : améliorer le main de façon à présenter un menu à l’utilisateur, lui permettant de faire la saisie des données d’un Produit, de l’insérer dans la liste, etc.
13. Bonus 2 : à partir de la question 7, écrire une nouvelle fonction qui crée et renvoie une 2nde liste formée des produits de la 1ère liste dont la quantité en Stock est inférieure à un seuil donné n. Ces produits sont "détachés" de la 1ère liste pour être insérés dans la 2nde (en sortie de la fonction la 1ère liste ne contient plus que les produits tels que nbP ≥ n).
14. Bonus 3 : une fonction qui prend en entrée 2 listes triées de produits et renvoie la liste fusionnée (triée) issue de ces 2 listes. Les 2 listes d’entrée ne sont pas modifiées.

## Code

~~~c
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

typedef struct _produit {
  char *idP;                    // Identifiant produit
  int   nbP;                    // Quantité en stock
  float PU;                     // Prix unitaire
  struct _produit *suiv, *prec; // Pointeur vers le suivant et le précédent
} Produit, *Liste;

typedef struct {
  Liste debut, fin;
} Stock;

Produit *creation(char *idP, int nbP, float PU) {
  Produit *produit = (Produit *)malloc(sizeof(Produit));

  produit->idP = idP;
  produit->nbP = nbP;
  produit->PU  = PU;

  produit->suiv = NULL;
  produit->prec = NULL;

  return produit;
}

void insertionFin(Stock *stock, Produit *produit) {
  if (stock->debut == NULL) {
    stock->debut = produit;
    stock->fin   = produit;

    return;
  }

  produit->prec    = stock->fin;
  stock->fin->suiv = produit;
  stock->fin       = produit;
}


void affichageProduit(Produit *produit) {
  printf("* %s : %.2f euros (%d disponibles)\n", produit->idP,
                                                 produit->PU, produit->nbP);
}

void affichage(Stock *stock) {
  Produit *courant = stock->debut;

  while (courant) {
    affichageProduit(courant);
    courant = courant->suiv;
  }
}

Produit *recherche(Stock *stock, char *nomP) {
  Produit *courant = stock->debut;

  while (courant) {
    if (strcmp(courant->idP, nomP) == 0)
      return courant;

    courant = courant->suiv;
  }

  return NULL;
}

void decremente(Stock *stock, char *nomP, int n) {
  Produit *produit = recherche(stock, nomP);

  if (produit != NULL)
    produit->nbP = produit->nbP - n;
}

void affichageRecherche(Stock *stock, int n) {
  Produit *courant = stock->debut;

  while (courant) {
    if (courant->nbP < n)
      affichageProduit(courant);

    courant = courant->suiv;
  }
}

void detacher(Stock *stock, Produit *aEnlever) {
  // S'il s'agit de la fin du stock, on change le
  // pointeur de fin, sinon on change le précédent
  // du pointeur suivant.
  if (stock->fin == aEnlever)
    stock->fin = aEnlever->prec;
  else
    aEnlever->suiv->prec = aEnlever->prec;

  // S'il s'agit du début du stock, on change le
  // pointeur de début, sinon on change le suivant
  // du pointeur précédent.
  if (stock->debut == aEnlever)
    stock->debut = aEnlever->suiv;
  else
    aEnlever->prec->suiv = aEnlever->suiv;
}

void supprimer(Stock *stock, char *nomP) {
  Produit *produit = recherche(stock, nomP);

  detacher(stock, produit);
  free(produit);
}

int comparer(Produit *courant, Produit *produit) {
  return strcmp(courant->suiv->idP, produit->idP) < 0;
}

void insertionOrdonne(Stock *stock, Produit *produit) {
  if (stock->debut == NULL) {
    stock->debut = produit;
    stock->fin   = produit;

    return;
  }

  Produit *courant = stock->debut;

  while (courant->suiv && comparer(courant, produit))
    courant = courant->suiv;

  produit->prec       = courant;
  produit->suiv       = courant->suiv;
  courant->suiv->prec = produit;
  courant->suiv       = produit;
}

void trier(Stock *stock) {
  Produit *courant = stock->debut;
  Stock *nouveau = (Stock *)malloc(sizeof(Stock));

  while (courant) {
    printf("%s\n", courant->idP);
    detacher(stock, courant);
    insertionOrdonne(nouveau, courant);

    courant = courant->suiv;
  }

  affichage(nouveau);
}

int main() {
  Stock *monMagasin = (Stock *)malloc(sizeof(Stock));

  monMagasin->debut = NULL;
  monMagasin->fin   = NULL;

  // +-----------------+
  // | Question 1 et 2 |
  // +-----------------+
  insertionFin(monMagasin, creation("CORDE", 4, 60.0));
  insertionFin(monMagasin, creation("CHAUSSONS", 52, 35.5));
  insertionFin(monMagasin, creation("CASQUE", 12, 21.3));
  insertionFin(monMagasin, creation("MOUSQUETON", 74, 12.9));

  // +-----------------+
  // |   Question  3   |
  // +-----------------+
  affichage(monMagasin);

  // +-----------------+
  // |   Question  5   |
  // +-----------------+
  affichageProduit(recherche(monMagasin, "CORDE"));

  // +-----------------+
  // |   Question  6   |
  // +-----------------+
  decremente(monMagasin, "CORDE", 2);
  affichageProduit(recherche(monMagasin, "CORDE"));

  // +-----------------+
  // |   Question  7   |
  // +-----------------+
  printf("\n\nProduits dont le stock est inferieur a 60 : \n\n");
  affichageRecherche(monMagasin, 60);

  // +-----------------+
  // | Question 8 et 9 |
  // +-----------------+
  // printf("\n\nApres detachement du produit CORDE :\n\n");
  // supprimer(monMagasin, "CORDE");
  // affichage(monMagasin);

  // +-----------------+
  // |   Question 10   |
  // +-----------------+
  printf("\n\nInsertion de LAVELINGE :\n\n");
  insertionOrdonne(monMagasin, creation("LAVELINGE", 10, 350.99));
  affichage(monMagasin);

  // +-----------------+
  // |   Question 11   |
  // +-----------------+
  printf("\n\nTri du stock :\n\n");
  trier(monMagasin);
  // affichage(monMagasin);

  return 0;
}
~~~
