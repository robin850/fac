---
layout: default
section: l3/s2
title: P. Système
---

**Sujet**

Écrire un programme en C qui crée deux processus communiquant par **tube**.
Le premier processus lit un fichier donné en paramètre, le second processus
affiche le contenu du fichier.

-------------------------------------------------------------------------------

~~~c
#include <stdlib.h>
#define MAX 1024

int main(int argc, char **argv) {
  int pid, T[2], n, i;
  char tampon[MAX];

  if (pipe(T) == -1) {
    perror("Erreur de pipe");
    exit(1);
  }

  if (argc != 3) {
    perror("Nombre d'arguments incorrect");
    exit(1);
  }

  pid = fork();

  if (pid < 0) {
    perror("Erreur lors de la creation du processus");
    exit(1);
  }

  if (pid == 0) {
    close(T[0]);

    while (n = read(argv[1], tampon, MAX))
      write(T[1], tampon, n);
  } else {
    close(T[1]);

    while (n = read(T[0], tampon, MAX))
      write(argv[2], tampon, n);

    close(T[0]);
    close(T[1]);
  }
}
~~~
