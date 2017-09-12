---
layout: default
section: l3/s2
title: P. Système
---

**Sujet**

Faire un programme:

~~~
./a.out fichier1 fichier2
~~~

Il faut qu'il y ait deux processus qui travaillent sur le même tampon (tube) en parralèle.

-------------------------------------------------------------------------------

~~~c
#include <unistd.h>
#include <stdlib.h>
#include <stdio.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <fcntl.h>

/**
 *                  T[1]                T[0]
 *      +---------------|===============|----------+
 *      |                                          |
 *  +--------+                                 +--------+
 *  | Tampon |                                 | Tampon |
 *  +--------+                                 +--------+
 *     |                                           |
 * |Fichier 1|                                 |Fichier 2|
 *
 *     fils                                       père
 */
int main(int argc, char **argv) {
  int pid, n, x, des1, des2, T[2];
  char tampon[255];
  struct stat s1;

  if (argc < 3) {
    perror("Nombre d'arguments insuffisant.");
    exit(1);
  }

  if (pipe(T) == -1) {
    perror("Erreur de tube.");
    exit(1);
  }

  pid = fork();

  if (pid < 0) {
    perror("Erreur de fork.");
    exit(1);
  }

  des1 = open(argv[1], O_RDONLY);

  if (des1 == -1) {
    perror("Fichier non existant.");
    exit(1);
  }

  if (fstat(des1, &s1) == -1) {
    perror("Erreur de `fstat`");
    exit(1);
  }

  des2 = open(argv[2], O_WRONLY|O_CREAT|O_EXCL, s1.st_mode);

  if (pid == 0) {
    close(T[0]);

    while ((n = read(des1, tampon, 255)))
      write(T[1], tampon, n);

    close(T[1]);
    exit(0);
  } else {
    close(T[1]);

    while ((n = read(T[0], tampon, 255)))
      write(des2, tampon, n);

    close(T[0]);
    close(des1);
    close(des2);
    wait(&x);
  }
}
~~~
