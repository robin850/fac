---
layout: default
title: P. Système
section: l3/s2
---

## Gestion de la mémoire

### Pagination

**Gestion de la mémoire virtuelle**

La mémoire est constituée de plusieurs segments (de tailles différentes) et chaque segment peut être divisé en page (toutes de même taille). Les segments regroupent des contenus qui ont un sens en commun.

Lorsque la mémoire est pleine, certaines parties du programme sont stockées sur le disque dur (Swap sous Linux). Certaines parties des processus sont directemet placées dans le disque dur. Lorsque l'on veut accéder à une page mais qu'elle n'est pas stockée en mémoire mais sur le disque dur, on parle de *défaut de page*.

Lorsque le système veut charger une page qui est stockée sur le disque en mémoire mais qu'il n'y a plus aucune page de libre, le système d'exploitation (l'ordonanceur en particulier) va retirer une page existante pour la placer.

> Une page = une case

### Conversion d'adresses

Les adresses sont données sous forme linéaire. Pour les convertir en adresse physique, il faut tout d'abord les transformer en adresse virtuelle. On a donc :

Adresse linéaire ~> Adresse virtuelle ~> Adresse physique

* Une adresse linéaire est representée sous forme de chiffre.
* Une adresse virtuelle est représentée par un triplet contenant le segment, la page et la différence entre le début de la page et la position de le donnée.
* Un adresse physique est constituée d'un doublet contenant la case mémoire et la différence entre la case et la position de la donnée dans cette case.

### Mémoire partagée en C

On crée tout d'abord un espace pour la mémoire partagé ; on utilise une clé afin
de pouvoir identifier cet espace dans les différents programmes. On précise ensuite
la taille et le chmod sur le fichier qui va être utilisé pour partager la mémoire.

~~~c
int id_de_lespace = shmget((key_t)CLE, 1000 , 0750 | IPC_CREAT | IPC_EXCL);

if (shmid == -1) {
  perror("Erreur de shmget.");
  exit(1);
}
~~~

Il faut ensuite attacher la mémoire partagée à une variable locale pour pouvoir
la modifier (ça a un petit peu le même rôle qu'un `malloc`) :

~~~c
char *ma_variable;

ma_variable = shmat(id_de_lespace, NULL, 0);
~~~

Et on peut ensuite utiliser la variable comme d'habitude :

~~~c
strcpy(ma_variable, "Eeeeh oui !");
~~~

Dans l'autre programme à partir duquel on veut récupérer l'espace de mémoire
partagé, on appelle de nouveau `shmget` mais cette fois ci sans avoir à préciser
le chmod puisque le fichier est déjà créé.

On recrée une nouvelle variable locale à ce programme en l'attachant à l'espace
de mémoire partagé :

~~~c
char *ma_variable;
int id_de_lespace;

id_de_lespace = shmget((key_t)CLE, 1000, 0);
ma_variable   = shmat(id_de_lespace, NULL, 0);

write(1, ma_variable, 11); // Affichera "Eeeeh oui !" à l'écran
~~~

Finalement, il faut détacher l'espace de mémoire partagé et supprimer le fichier
associé :

~~~c
shmdt(ma_variable);
shmctl(id_de_lespace, IPC_RMID, 0);
~~~
