---
layout: default
title: Info
section: prrel
---

## Correction du TD n°1

### Exercice 1 - Boucles et optimisations

**1. Ecrire un programme `affDiviseurs()` qui affiche tous les diviseurs d’un nombre entier n lu au clavier en utilisant une boucle de 1 à n.**

~~~pseudo
programme affDiviseurs
  n, i : entier
début
  lire(n)

  pour i de 1 à n
    si (n % i == 0)
      afficher(i)
    fin
  fin
fin
~~~

**2. Ecrire un programme `affDiviseursVite()` qui affiche tous les diviseurs d’un nombre entier n lu au clavier mais avec une boucle courte de 1 à √(n)**

Ici c'est exactement la même chose que le programme précédent mais au lieu d'aller
de 1 à n, on va de 1 à √(n).

~~~pseudo?6
programme affDiviseursVite
  n, i : entier
début
  lire(n)

  pour i de 1 à √(n)
    si (n % i == 0)
      afficher(i)
    fin
  fin
fin
~~~

**3. Quel est le nombre de cycles utilisés pour calculer les diviseurs de 100000 pour chacune des fonctions ?**

Puisque qu'on va de 1 à √(100000), le nombre de cycles est de √(100000).

### Exercice 2 - Boucles et appels de procédure

**1. Ecrire un programme `affDiviseurs1a10()` qui affiche tous les diviseurs des entiers compris entre 1 et 10 (en utilisant deux boucles différentes).**

~~~pseudo
programme affDiviseurs1a10
  n, i : entier
début
  pour n de 1 à 10
    // Pour chaque nombre entre 1 et 10, on vérifie
    // s'il y a un diviseur pour n
    i <- 1

    tant que (i <= n)
      si (n % i == 0)
        afficher(i)
      fin

      i <- i + 1
    fin
  fin
fin
~~~

**2. Ecrire un programme `affDiviseursVite(n: entier)` qui affiche tous les diviseurs d’un nombre entier n passé en paramètre.**

Exactement la même chose que la question 2 de l'exercice 1 sauf qu'on ne lit pas `n`
au clavier, il est passé en paramètre du programme :

~~~pseudo
programme affDiviseursVite(n : entier)
  i : entier
début
  pour i de 1 à √(n)
    si (n % i == 0)
      afficher(i)
    fin
  fin
fin
~~~

**3. Ecrire un programme `affDiviseurs1a10Court()` qui affiche tous les diviseurs des entiers compris entre 1 et 10 (cette fois en faisant appel à `affDiviseursVite(n)`).**

Ici même chose que la question 1 mais pas besoin d'utiliser une boucle pour trouver
les diviseurs d'un nombre ; on appel `affDiviseursVite(n)` :

~~~pseudo?5
programme affDiviseurs1a10Court
  i : entier
début
  pour i de 1 à 10
    affDiviseursVite(n)
  fin
fin
~~~

**4. Ecrire un programme `affDiviseursDe(a : entier, b : entier)` qui affiche tous les diviseurs des entiers compris entre a et b (en faisant appel à affDiviseurs(n : entier)).**

Ici même chose que le programme du dessus mais plutôt que d'aller de 1 à 10, on va
de `a` à `b` :

~~~pseudo?4
programme affDiviseursDe(a : entier, b : entier)
  i : entier
début
  pour i de a à b
    affDiviseurs(i)
  fin
fin
~~~

### Exercice 3 - Fonctions et suites

**1. Ecrire une fonction qui `primalite(n: entier)` qui retourne vrai si l'entier `n` passé en paramètre est premier. Optimisez au mieux la fonction.**

Ici on cherche des diviseurs pour `n` ; s'il y en a, il n'est pas premier, sinon, il
est premier.

Pour la partie de la consigne "Optimisez au mieux la fonction", il faut juste aller
de 2 (car 1 divise tous les nombres) à √(n) car il n'existe aucun diviseur d'un
nombre entre sa racine et lui même.

~~~pseudo
fonction primalite(n : entier) : booléen

début
  pour i de 2 à √(n)
    si (n % i == 0)
      retourner faux
    fin

    // Il ne faut surtout par retourner vrai si jamais i
    // ne divise pas n car sinon, dès qu'on trouve un nombre
    // qui n'est pas multiple, la fonction renvoie vrai alors
    // qu'il y a peut être un diviseur plus loin dans la liste.
  fin

  retourner vrai
fin
~~~

**3. Ecrire une fonction `racineCarree(a : réel, precision : entier)` qui retourne une approximation de la racine carrée du réel 'a' passé en paramètre. Vous utiliserez la suite dé nie par r<sub>0</sub> = 1 et r<sub>n</sub> = (r<sub>n-1</sub> + a / r<sub>n-1</sub>) / 2**

Ici, pour calculer la racine carré d'un nombre, il suffit de définir une fonction qui
représente la suite r. Le `a` de la suite représente le paramètre `a` de la fonction
`racineCarree` et le `n` de la suite représente le paramètre `precision` de la fonction.

~~~pseudo
fonction racineCarree(a : réel, precision : entier) : réel
  precedent : réel
début
  // +------------------+
  // | Définition de r0 |
  // +------------------+
  si (precision == 0)
    retourner 1
  fin

  // +------------------+
  // | Définition de rn |
  // +------------------+

  // Calcul de rn-1
  precedent = racineCarree(a, precision - 1)

  // Calcul de rn
  retourner (precedent + (a / precedent)) / 2
fin
~~~

### Exercice 4 - Tableau

**1. Définir un type, appelé TabReel représentant un tableau de réels.**

~~~pseudo
type TabReel tableau de réel;
~~~

**2. Définissez le tableau `achats` qui est un tableau de type TabReel de longueur 52. Il contient le montant des sommes dépensées chaque semaine par une personne.**

~~~pseudo
achats : TabReel[52]
~~~

**3. Ecrire la fonction `total(achats : TabReel, n : entier)` qui retourne le total dépensé sur les n première semaines.**

Ici, on fait une simple somme des éléments du tableau.

~~~pseudo
fonction total(achats : TabReel, n : entier)
  s : réel
  i : entier
début
  s <- 0

  pour i de 1 à n
    s <- s + achats[i]
  fin

  retourner s
fin
~~~

**4. Ecrire la fonction `ecartType(achats : TabReel, n : entier)` qui retourne l'écart type entre les achats sur les `n` premières semaines. L'écart type est la distance moyenne entre le prix d'un achat et la moyenne. Un écart proche de 0 signifie que les achats ont les mêmes coûts. Ecart-type = √(moyenne(x<sup>2</sup>) - moyenne(x)<sup>2</sup>)**

Ici, on doit calculer :

* La moyenne des éléments du tableau : ce qui revient à prendre le total et à le
  diviser par le nombre d'éléments.
* La moyenne des élements du tableau mis au carré : ce qui revient à refaire une
  somme comme dans la fonction précédente mais en mettant les éléments au carré.

~~~pseudo
fonction ecartType(achats : TabReel, n : entier)
  moyenne, total_carrés, moyenne_carrés : réel
  i : entier
début
  // +----------------------+
  // | Calcul de la moyenne |
  // +----------------------+

  // On appelle la fonction précédente pour avoir la moyenne
  moyenne = total(achats, n) / n

  // +---------------------------------+
  // | Calcul de la moyenne des carrés |
  // +---------------------------------+

  // On calcul le total des éléments mis au carré sur le
  // même modèle que la fonction précédente.
  total_carrés <- 0
  pour i de 1 à n
    total_carrés <- total_carrés + (achats[i] * achats[i])
  fin

  // On divise par n comme pour la moyenne
  moyenne_carrés <- total_carrés / n

  retourner √(moyenne_carrés - (moyenne * moyenne))
fin
~~~
