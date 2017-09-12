---
layout: default
title: P. Système
section: l3/s2
---

**Sujet**

Écrire un programme en C qui prend en paramètre deux fichiers et copie le contenu
du premier vers le deuxième avec les mêmes droits d'accès.

-------------------------------------------------------------------------------

~~~c
#include <stdlib.h>
#include <stdio.h>
#include <unistd.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <fcntl.h>

#define MAX 1024

int main(int argc, char **argv) {
  int des1, des2, n;
  char tampon[MAX];
  struct stat s1;

  if (argc == 3) {
    des1 = open(argv[1], O_RDONLY);

    if (des1 < 0) {
      perror("Fichier inexistant");
      exit(1);
    }

    fstat(des1, &s1);

    if (!S_ISREG(s1.st_mode)) {
      perror("Fichier non regulier");
      exit(1);
    }

    des2 = open(argv[2], O_WRONLY|O_CREAT|O_EXCL, s1.st_mode);

    while ((n = read(des1, tampon, MAX))) {
      write(des2, tampon, n);
    }

    close(des1);
    close(des2);
  } else {
    perror("Nombre d'arguments incorrect");
    exit(1);
  }
}
~~~
