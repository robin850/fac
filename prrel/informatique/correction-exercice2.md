---
layout: default
title: Info
section: prrel
---

On souhaite écrire un algorithme permettant le gestion d'un panier sur un site
marchand de grande distribution tel que ChronoDrive ou LeclercDrive. Pour cela,
pour chaque article ajouté, la catégorie du produit (représentée par un caractère,
avec 'A' pour les produits alimentaires, 'B' pour les produits d'hygiène et 'C'
pour les autres catégories), le prix du produit et la quantité seront précisés.

**1.** Écrire un algorithme permettant de saisir la catégorie, le prix et la quantité
pour un article ajouté au panier et d'afficher le montant total à payer (prix &times;
quantité).

~~~pseudo
programme panier
  var categorie : caractère;
      prix, total : décimal;
      quantite : entier;
début
  categorie <- 'D';

  tant que (categorie != 'A' et categorie != 'B' et categorie != 'C')
    écrire("Catégorie du produit (A, B, ou C): ");
    lire(categorie);
  fin

  écrire("Prix du produit : ");
  lire(prix);

  écrire("Quantité : ");
  lire(quantite);

  total <- prix * quantite;

  écrire("Total à payer : ");
  écrire(total);
fin
~~~

**2.** Écrire un algorithme permettant de saisir la catégorie, le prix et la
quantité pour les **n** articles ajoutés au panier et d'afficher le montant
total à payer. Le nombre **n** sera saisi par l'utilisateur en début d'algorithme.

~~~pseudo?4,6..9,11,27,28
programme panier
  var categorie : caractère;
      prix, total : décimal;
      quantite, n, i : entier;
début
  écrire("Nombre de produits : ");
  lire(n);

  total <- 0;

  pour i allant de 1 à n faire
    // Important de le mettre dans la boucle car sinon la valeur
    // serait la même que celle du produit précédemment rentré.
    categorie <- 'D';

    tant que (categorie != 'A' et categorie != 'B' et categorie != 'C')
      écrire("Catégorie du produit (A, B, ou C): ");
      lire(categorie);
    fin

    écrire("Prix du produit : ");
    lire(prix);

    écrire("Quantité : ");
    lire(quantite);

    total <- total + (prix * quantite);
  fin

  écrire("Total à payer : ");
  écrire(total);
fin
~~~

**3.** Modifier l'algorithme précédent en changeant la boucle utilisée.

~~~pseudo?10,12,27
programme panier
  var categorie : caractère;
      prix, total : décimal;
      quantite, n, i : entier;
début
  écrire("Nombre de produits : ");
  lire(n);

  total <- 0;
  i <- 1;

  tant que (i <= n) faire
    categorie <- 'D';

    tant que (categorie != 'A' et categorie != 'B' et categorie != 'C')
      écrire("Catégorie du produit (A, B, ou C): ");
      lire(categorie);
    fin

    écrire("Prix du produit : ");
    lire(prix);

    écrire("Quantité : ");
    lire(quantite);

    total <- total + (prix * quantite);
    i     <- i + 1;
  fin

  écrire("Total à payer : ");
  écrire(total);
fin
~~~

**4.** Écrire un algorithme permettant de saisir la catégorie, le prix et
la quantité pour des articles ajoutés au panier jusqu'à ce que le nombre
de produits ajoutés soit égal à 10 ou que le montant total de la commande
dépasse 70 euros.

~~~pseudo?12
programme panier
  var categorie : caractère;
      prix, total : décimal;
      quantite, n, i : entier;
début
  // Ici, on ne demande plus le nombre de produit car ce n'est
  // plus nécessaire, la boucle ne dépend plus de `n`.

  total <- 0;
  i <- 1;

  tant que (i <= 10 et total <= 70) faire
    categorie <- 'D';

    tant que (categorie != 'A' et categorie != 'B' et categorie != 'C')
      écrire("Catégorie du produit (A, B, ou C): ");
      lire(categorie);
    fin

    écrire("Prix du produit : ");
    lire(prix);

    écrire("Quantité : ");
    lire(quantite);

    total <- total + (prix * quantite);
    i     <- i + 1;
  fin

  écrire("Total à payer : ");
  écrire(total);
fin
~~~

**5.** Écrire un algorithme permettant de saisir la catégorie, le prix et
la quantité pour des articles ajoutés au panier tant que le nombre de produits
ajoutés de catégorie 'A' est inférieur ou égal à 20.

~~~pseudo?4,10,12,20..22
programme panier
  var categorie : caractère;
      prix, total : décimal;
      quantite, n, compteura : entier;
début
  écrire("Nombre de produits : ");
  lire(n);

  total <- 0;
  compteura <- 0;

  tant que (compteura < 20) faire
    categorie <- 'D';

    tant que (categorie != 'A' et categorie != 'B' et categorie != 'C')
      écrire("Catégorie du produit (A, B, ou C): ");
      lire(categorie);
    fin

    si (categorie = 'A')
      compteura <- compteura + 1;
    fin

    écrire("Prix du produit : ");
    lire(prix);

    écrire("Quantité : ");
    lire(quantite);

    total <- total + (prix * quantite);
  fin

  écrire("Total à payer : ");
  écrire(total);
fin
~~~

**6.** Écrire un algorithme permettant de saisir la catégorie, le prix HT
et la quantité pour les n articles ajoutés au panier et d'afficher le
montant total TTC à payer. Pour cela, le montant de la TVA à appliquer
sera de 5,5% pour les produits de la catégorie 'A' et 20% pour les produits
de la catégorie 'B' et 'C'.

~~~pseudo?6,12,13,23,29..33,37
// Pour cet algorithme ci, on ne se base pas sur celui de la
// question précédente mais sur celui de la question 2 car
// il est plus similaire vu que l'on réintroduit `n`.
programme panier
  var categorie : caractère;
      prix, totala, totalbc : décimal;
      quantite, n : entier;
début
  écrire("Nombre de produits : ");
  lire(n);

  totala  <- 0;
  totalbc <- 0;

  pour i allant de 1 à n faire
    categorie <- 'D';

    tant que (categorie != 'A' et categorie != 'B' et categorie != 'C')
      écrire("Catégorie du produit (A, B, ou C): ");
      lire(categorie);
    fin

    écrire("Prix HT du produit : ");
    lire(prix);

    écrire("Quantité : ");
    lire(quantite);

    si (categorie = 'A')
      totala <- totala + ((prix + (prix * 0.055)) * quantite);
    sinon
      totalbc <- totalbc + ((prix + (prix * 0.2)) * quantite);
    fin
  fin

  écrire("Total à payer : ");
  écrire(totala + totalbc);
fin
~~~
