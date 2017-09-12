---
layout: default
title: POO
section: l3/s2
---

### Création d'un `enum`

Un `enum` agit un petit peu comme une classe mais a un jeu de valeur possible connu au moment de la compilation. On ne peut pas instancier de nouvelles valeurs.

~~~java
public enum Moyen {
  BUS(0.1, 40), TRAM(0.3, 50), TRAIN(1, 70);

  double cout;
  double v;

  Moyen(double _cout, double _v) {
    cout = _cout;
    v    = _v;
  }
}
~~~

Lorsque l'on fait `BUS(0.1, 40)`, c'est le constructeur de l'enum qui sera appelé avec les valeurs passés en paramètres. Ensuite, pour accéder à une entrée en particulier,
on fait :

~~~java
Moyen.BUS;
~~~

Ou en passant *via* des chaînes de caractères :

~~~java
Moyen.valueOf("BUS");
~~~
