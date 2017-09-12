---
layout: default
title: POO
section: l3/s2
---

## Types génériques

On utilise le type `T` pour spécifier qu'un élément doit être de type generique.

### Création de tableaux avec type générique

Pour des tableaux avec des éléments qui doivent être comparés :

~~~java
public <T extends Comparable<T>> void fonction(T[] tab) {
  /* ... */
}
~~~

Ensuite, pour comparer les éléments :

~~~java
element.compareTo(autreElement);
~~~

La méthode `compareTo` renvoie :

* -1 quand `element < autreElement`
* 0 quand `element == autreElement`
* 1 quand `element > autreElement`

**Pitit moyen mnémotechnique**

<center>
  <code>-1 0 1</code><br>
  <code> <=>  </code>
</center>
