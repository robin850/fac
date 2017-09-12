---
layout: default
title: POO
section: l3/s2
---

## Notation lambda

Les lambdas sont des fonctions anonymes qui peuvent être passés en paramètre
d'autres fonctions / méthodes.

~~~java
/* Pour trier un tableau par exemple */
import java.util.ArrayList;
import java.util.Arrays;

// ...

ArrayList<Integer> valeurs = new ArrayList<Integer>(Arrays.asList(4, 2, 1, 3, 5));

// Trié par ordre décroissant
valeurs.sort((p1, p2) -> {
  return p2 - p1;
});
~~~

S'il n'y a qu'un argument, les parenthèses sont optionnelles et si le corps ne fait
qu'une ligne, pas besoin d'accolades ou de `return`. Par exemple :

~~~java
arrayList.forEach(element -> System.out.println(element));
~~~
