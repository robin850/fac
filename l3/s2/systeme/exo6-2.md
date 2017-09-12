---
layout: default
section: l3/s2
title: P. Système
---

**Sujet**

Il y a N processus qui exécutent tous :

~~~c
while (true) {
  access_tampon(i)
  // Utiliser le tampon
  liberer_tampon(i)
}
~~~

Écrire des procédures `access_tampon(i)` et `liberer_tampon(i)` de sorte que un seul processus utilise le tampon à la fois.

Faire ensuite en sorte que les processus utilisent le tampon de manière circulaire.

Faire ensuite en sorte qu'un seul exécute à la fois en partant du principe que les processus paires sont plus prioritaires que les processus impaires.

-------------------------------------------------------------------------------

**Un seul processus utilise le tampon à la fois**

~~~c
// On utilise un sémaphore ; que l'on initialise
// dans le programme principal et non dans les fonctions
S = 1

void acces_tampon(int i) {
  P(S)
}

void liberer_tampon(int i) {
  V(S)
}
~~~

**Les processus utilisent un tampon de manière circulaire**

~~~c
// Sémaphore via un tableau int T[N]

T[1] = 1

for (i = 2; i < N; i++)
  T[i] = 0;

void acces_tampon(int i) {
  P(T[i])
}

// Chaque processus va réveiller le suivant
void liberer_tampon(int i) {
  if (i < N)
    V(T[i+1])
  else
    V(T[1])
}
~~~

**Un seul à la fois avec plus grande priorité pour les processus paires**

~~~c
// On utilise une variable qui va compter le nombre
// de processus père
compteur = 0;
S1 = 0;
S2 = 1;
S3 = 1;

void access_tampon(int i) {
  if (i % 2 == 1)
    P(S2);
  else {
    P(S3);
    compteur++;
    V(S3);
    P(S2);
  }
}

void liberer_tampon(int i) {
  V(S2);

  if (i % 2 == 0) {
    P(S3);
    compteur--;

    if (compteur == 0)
      V(S1);

    V(S3);
  } else
    V(S1);
}
~~~
