---
layout: default
title: P. Système
section: l3/s2
---

**Sujet**

Écrire un programme en C qui crée deux processus communiquant par **tube**.
Le processus fils lit sur l'entrée standard et le processus père affiche ce
qui a été lu sur l'entrée standard en remplaçant les majuscules par des
minuscules.

-------------------------------------------------------------------------------

~~~c
#include <stdlib.h>
#include <stdio.h>
#include <unistd.h>

#define MAX 64

char majmin(char c) {
  if (c >= 'A' && c <= 'Z') {
    return c + ('a' - 'A');
  } else {
    return c;
  }
}

/**                          Tube
 *             T[1]  |===================| T[0]
 *              |                            |
 *              |                            |
 *    +-----|Tampon|                      |Tampon|
 *    |                                      +---------|
 * +-----+                                             +
 * |-|-|-| <------- Clavier           Écran --------> |o|
 * +-----+
 *
 *  fils                                     père
 */
int main() {
  int pid, T[2], n, i, status;
  char tampon[MAX];

  if (pipe(T) == -1) {
    perror("Erreur de pipe");
    exit(1);
  }

  pid = fork();

  if (pid < 0) {
    perror("Erreur lors de la creation du processus");
    exit(1);
  }

  if (pid == 0) {
    close(T[0]);

    while ((n = read(0, tampon, MAX)))
      write(T[1], tampon, n);
  } else {
    close(T[1]);

    while ((n = read(T[0], tampon, MAX))) {
      for (i = 0; i < n; i++)
        tampon[i] = majmin(tampon[i]);

      write(1, tampon, n);

      // Simplement utilisé pour récupérer le code retour
      // de l'exécution du processus fils.
      wait(&status);
    }

    close(T[0]);
  }
}
~~~
