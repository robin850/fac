---
layout: default
title: Info
section: prrel
---

## Correction du TD n°3

On doit gérer par ordinateur les notes d’un groupe de N (N <= 100) étudiants qui suivent une formation composée de M matières (M <= 15).

###### Définissez les types suivants

**1. Un type structure appelée `Matière` contenant un champ nom de type chaîne, un champ moyenne de type réel et un champ coef de type réel.**

~~~pseudo
structure Matière {
  nom : chaîne;
  moyenne : réel;
  coef : réel;
}
~~~

**2. Définissez un type tableau de réels appelé TabReels. Ce tableau contiendra les notes d’un étudiant.**

~~~pseudo
type TabReels : tableau de réels;
~~~

**3. un type structure appelée Etudiant contenant un champ nom de type chaîne, un champ moyenne de type réel, et un champs notes de type TabReels.**

~~~pseudo
structure Etudiant {
  nom : chaîne;
  moyenne : réel;
  notes : TabReels;
}
~~~

**4. Un type tableau de Matière, appelé TabMat ; un type tableau d’étudiants appelé TabEtu.**

~~~pseudo
type TabMat : tableau de Matière;
type TabEtu : tableau de Etudiant;
~~~

**5. Définissez un type tableau de réels à deux dimensions appelé MatNotes. Ce tableau contiendra les notes des étudiants (étudiants classés en ligne et notes des matières en colonnes).**

> La consigne est pas très claire puisque se sont des réels que l'on stocke ici
> mais une ligne est associée à un étudiant et chaque élément de cette ligne
> représente une de ses notes.
>
> Par exemple, avec le tableau :
>
> | Matière 1 | Matière 2 |
> |-----------|-----------|
> | 13        | 18        |
> | 9         | 17        |
>
> L'étudiant n°1, a 13 et 18 dans ses notes et l'étudiant n°2 a 9 et 17.

~~~pseudo
type MatNotes : tableau de TabReels;
~~~

-------------------------------------------------------------------------------

**3. Ecrire la procédure `saisieLaMatiere(matiere : Matiere)` qui permet la saisie d’une matière (son nom et son coef).**

~~~pseudo
procédure saisieLaMatiere(matiere : Matiere)
  nom : chaîne;
  coef : réel;
début
  lire(nom)
  lire(coef)

  matiere.nom <- nom
  matiere.coef <- coef
fin
~~~

**4. Ecrire la procédure `saisieMatieres(matieres : TabMat, nbMat : Entier)` qui permet la saisie de nbMat matières qui sont ensuite stockées dans le tableau tabMatieres.**

~~~pseudo
procédure saisieMatieres(matieres : TabMat, nbMat : Entier)
  i : entier
début
  pour i de 1 à nbMat
    saisieLaMatiere(matieres[i])
  fin
fin
~~~

**5. Ecrire la procédure `saisieEtu(etu : Etudiant, nbMat : Entier)` qui permet la saisie d’un étudiant, et de ses notes sur `nbMat` matières.**

~~~pseudo
procédure saisieEtu(etu : Etudiant, nbMat : Entier)
  nom : chaîne:
  i : entier;
  note, somme : réel
début
  lire(nom)
  etu.nom <- nom

  // Ensuite on saisie les notes de l'étudiants
  pour i de 1 à nbMat
    lire(note)

    etu.notes[i] <- note
  fin
fin
~~~

**6. Ecrire la procédure `afficheEtu(etu : Etudiant)` qui affiche le nom et la moyenne d’un etudiant.**

~~~pseudo
procédure afficheEtu(etu : Etudiant)
début
  afficher(etu.nom)
  afficher(etu.moyenne)
fin
~~~

**7. Ecrire la procédure `saisieEtudiants(etudiants : TabEtu, nbEtu : Entier, nbMat : Entier)` qui permet la saisie de `nbEtu` etudiants et leur stockage dans le tableau `etudiants`.**

~~~pseudo
procédure saisieEtudiants(etudiants : TabEtu, nbEtu : Entier, nbMat : Entier)
  i : entier
  etu : Etudiant
début
  pour i de 1 à nbEtu
    saisieEtu(etu, nbMat)
    etudiants[i] <- etu
  fin
fin
~~~

**8. Ecrire la procédure `afficheTabEtu(etudiants : TabEtu)` qui affiche les étudiants du tableau.**

> **Note** : Ici, la consigne ne donne pas la taille du tableau ; c'est
> sûrement une erreur de l'énnoncé du coup elle est dans la correction.

~~~pseudo
procédure afficheTabEtu(etudiants : TabEtu, nbEtu : Entier)
  i : entier
début
  pour i de 1 à nbEtu
    afficheEtu(etudiants[i])
  fin
fin
~~~

**9. Ecrire la procédure `calculMoyennesEtu(etudiants : TabEtu, nbEtu : Entier, matieres : TabMat, nbMat : Entier, grille : MatNotes)` qui calcule la moyenne de chaque étudiant, stocke cette moyenne dans le champ moyenne de l’étudiant, et qui remplit la matrice de notes `grille`.**

La difficulté ici est qu'on a deux boucles avec une qui permet de parcourir
le tableau d'étudiants et l'autre qui permet de parcourir le tableau de notes
associé à un étudiant.

~~~pseudo
procédure calculMoyennesEtu(etudiants : TabEtu, nbEtu : Entier,
                            matieres : TabMat, nbMat : Entier, grille : MatNotes)
  netu, nmat : entier
  total, moyenne somme_coef : réel
début
  pour netu de 1 à nbEtu
    moyenne <- 0
    somme_coef <- 0

    pour nmat de 1 à nbMat
      // +------------------------------------+
      // | Calcul de la moyenne de l'étudiant |
      // +------------------------------------+
      total <- total + (matieres[nmat].coef * etu.notes[nmat])
      somme_coef <- matieres[nmat].coef

      // +--------------------------+
      // | Remplissage de la grille |
      // +--------------------------+
      grille[netu][nmat] = etu.notes[nmat]
    fin

    moyenne <- total / somme_coef
    etudiants[nbetu].moyenne = moyenne
  fin
fin
~~~

**10. Ecrire la procédure `calculMoyennesMatieres (nbEtu : Entier, matieres : TabMat, nbMat : Entier, grille : MatNotes)` qui calcule la moyenne des notes obtenues dans chaque matière et qui stocke cette moyenne dans le champ moyenne des matières.**

La difficulté ici c'est qu'on fait la somme des éléments d'une colonne plutôt que
d'une ligne.

~~~pseudo
procédure calculMoyennesMatieres (nbEtu : Entier, matieres : TabMat, nbMat : Entier,
                                   grille : MatNotes)
  netu, nmat : entier
  total : réel
début
  pour nmat de 1 et nbMat
    total <- 0

    // On somme toutes les notes de cette matière
    pour netu de 1 à nbEtu
      total <- total + grille[netu][nmat]
    fin

    matieres[nmat].moyenne = total / nbEtu
  fin
fin
~~~

**11. Ecrire la fonction `meilleurMoyenne(etudiants : TabEtu, nbEtu : Entier)` qui retourne l’étudiant ayant la moyenne la plus élevées.**

Ici, on part du principe que le premier étudiant du tableau a la moyenne la plus
élevée et on parcours le reste du tableau en essayant de trouver une meilleure
moyenne.

~~~pseudo
fonction meilleurMoyenne(etudiants : TabEtu, nbEtu : Entier) : Etudiant
  meilleur : Etudiant
  i : entier
début
  meilleur <- etudiants[1]

  pour i de 2 à nbEtu
    si (etudiants[i].moyenne > meilleur.moyenne)
      meilleur <- etudiants[i]
    fin
  fin

  retourner meilleur
fin
~~~
