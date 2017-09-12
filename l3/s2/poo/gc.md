---
layout: default
title: POO
section: l3/s2
---

### Gestion du *garbage-collector*

* Lancer le GC automatiquement :

    ~~~java
    System.gc();
    ~~~

* Forcer un object à être désalloué (pour éviter les fuites de mémoires "légères") :

    ~~~java
    variable = null;
    ~~~

* Démarrer une JVM sans GC :

    ~~~
    $ java -noasyncgc Programme
    ~~~
