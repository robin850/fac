---
layout: default
title: P. Système
section: l3/s2
---

**Sujet**

Écrire un programme en C qui prend en paramètre un fichier, le lit puis affiche
son contenu sur la sortie standard. Si aucun fichier n'est passé en paramètre, lire
sur l'entrée standard et afficher ce que l'utilisateur a écrit.

--------------------------------------------------------------------------------

~~~c
#include <unistd.h>
#include <fcntl.h>

#define MAX 1024

int main(int argc, char **argv) {
  int des, i, n;
  char tampon[MAX];

  if (argc != 1) {
    for (i = 1; i < argc; i++) {
      des = open(argv[i], O_RDONLY);

      while ((n = read(des, tampon, MAX))) {
        write(1, tampon, n);
      }

      close(des);
    }
  } else {
    while ((n = read(0, tampon, MAX)))
      write(1, tampon, n);
  }
}
~~~
