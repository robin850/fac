---
layout: default
section: l3/s2
title: P. Système
---

**Sujet**

Écrire un  programme C qui crée 2 processus qui communiquent par tube, le premier
ouvre un fichier donné en argument du programme et transmet par le tube le contenu
de ce fichier et le second copie le contenu dans un fichier passé en argument du
programme.

Utiliser les tubes pour la redirection des entrées/sorties.

Par exemple :

<pre style="background: #222; color: #ddd"><code>ls -l | grep .c | more</code></pre>

Il y a ici 2 tubes. Dessiner les tubes T1 et T2 et donner leurs extrémités.

On part du principe que l'on a père -> fils1 -> fils2 (c'est à dire, le père
crée un processus fils et le fils crée un autre processus fils).

Il faut utiliser les primitives :

* `dup`
* `execlp`

> **Note** : Utiliser deux pipes.

-------------------------------------------------------------------------------

**Indication pour la correction**

L'exercice a d'abord été corrigé pour exécuter les commandes `ls -l | grep .c` (avec un tube) puis pour `ls -l | grep .c | more` (avec deux tubes). La correction de l'algorithme utilisant deux tubes se base sur l'algorithme n'utilisant qu'un tube ; les différences sont surlignées.

**Avec un seul pipe (`ls -l | grep .c`)**

~~~c
/*
 *  • • • • • • • • • •    • • • • • • • • • • •
 *  •          T[1]   •    •   T[0]            •
 *  •    +-------|==============|-------+      •
 *  •    |            •    •            |      •
 *  •    |            •    •            |      •
 *  •  +--------+     •    •    +---------+    •
 *  •  | ls -l  |     •    •    | grep .c |    •
 *  •  +--------+     •    •    +---------+    •
 *  •                 •    •                   •
 *  •                 •    •                   •
 *  • • • • • • • • • •    • • • • • • • • • • •
 *
 *       Fils                      Père
 */
#include <stdlib.h>
#include <stdio.h>
#include <unistd.h>

int main() {
  int pid, T[2];

  if (pipe(T) == -1) {
    perror("Erreur de pipe");
    exit(1);
  }

  pid = fork();

  if (pid < 0) {
    perror("Erreur lors de la creation du processus");
    exit(1);
  }

  if (pid == 0) { // Fils
    close(T[0]);

    close(1);
    dup(T[1]);

    execlp("ls", "ls", "-l", NULL);
    perror("Echec de l'execution de `ls`.");
  } else {  // Père
    close(T[1]);

    close(0);
    dup(T[0]);

    execlp("grep", "grep", ".c", NULL);
    perror("Echec de l'execution de `grep`.");
  }

  exit(0);
}
~~~

**Avec deux pipes (`ls -l | grep .c | more`)**

~~~c?21,23..26,28,30,59,60,68..75
/*
 *  • • • • • • • • • •    • • • • • • • • • • • • •   • • • • • • • • • • • •
 *  •         T1[1]   •    •   T1[0]        T2[1]  •   •   T2[0]             •
 *  •    +-------|==============|----+   +----|=============|-------+        •
 *  •    |            •    •         |   |         •   •            |        •
 *  •    |            •    •         |   |         •   •            |        •
 *  •  +-------+      •    •      +---------+      •   •       +------+      •
 *  •  | ls -l |      •    •      | grep .c |      •   •       | more |      •
 *  •  +-------+      •    •      +---------+      •   •       +------+      •
 *  •                 •    •                       •   •                     •
 *  •                 •    •                       •   •                     •
 *  • • • • • • • • • •    • • • • • • • • • • • • •   • • • • • • • • • • • •
 *
 *     Fils du fils                  Fils                   Père
 */
#include <stdlib.h>
#include <stdio.h>
#include <unistd.h>

int main() {
  int pid2, T2[2];

  if (pipe(T2) == -1) {
    perror("Erreur de tube");
    exit(1);
  }

  pid2 = fork();

  if (pid2 == 0) {
    /**
     * Code avec 1 seul pipe pour faire ls | grep *c
     */
    int pid, T1[2];

    if (pipe(T1) == -1) {
      perror("Erreur de pipe");
      exit(1);
    }

    pid = fork();

    if (pid < 0) {
      perror("Erreur lors de la creation du processus");
      exit(1);
    }

    if (pid == 0) { // Fils du fils
      close(T1[0]);

      close(1);
      dup(T1[1]);

      execlp("ls", "ls", "-l", NULL);
      perror("Echec de l'execution de `ls`.");
    } else { // Fils
      close(T1[1]);

      close(1);
      dup(T2[1]);

      close(0);
      dup(T1[0]);
      execlp("grep", "grep", ".c", NULL);

      perror("Echec de l'execution de `grep`.");
    }
  } else { // Père
    close(T2[1]);
    close(0);
    dup(T2[0]);
    execlp("more", "more", NULL);

    perror("Echec de l'execution de `more`.");
  }

  exit(0);
}
~~~
