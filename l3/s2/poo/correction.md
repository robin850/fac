---
layout: default
section: l3/s2
title: POO
---

# Correction du DS de Java - Première session

## Questions de cours

**Calculez la complexité en temps relativement à n, du code C1 et C2, sachant que
la création d'un objet coûte 10ms, l'addition d'entiers 0.5ms, la comparaison d'entiers
0.1ms, l'accès à un élément d'un tableau 0.1ms, l'appel de la méthode `append` 3ms.**

### Pour le code C1

> **Note** : L'appel de `+` sur une chaîne de caractère crée un `StringBuffer`
> et va appeler sa méthode `append`.

~~~java
String ch = new String();          // | 10ms
int n = tab.length;                // Balec

for (int i = 0; i < n; i++)        // | Pour `i < n` : (n+1) * 0,1ms
                                   // | Pour `i++` : n * 0,5ms

                                   // Le tout multiplié par n ici
  ch += new String(tab[i]) + ",";  // | Pour `new String()` : 10ms
                                   // | Pour `tab[i]` : 0,1ms
                                   // | Pour `ch +=` : 10ms + 3ms
                                   // | Pour `+ ","` : 10ms + 3ms

System.out.println("tab = " + ch); // | Pour `"tab =" + ch` : 10ms + 3ms
~~~

On a donc une complexité temporelle

<center>
  10 + (n + 1) &times; 0.1 + n &times; 0.5 + n &times; (10 + 10 + 10 + 3 + 3 + 0.1)<br>
  =<br>
  10 + (n + 1) &times; 0.1 + n &times; 36.6
</center>


### Pour le code C2

~~~java
StringBuffer sb = new StringBuffer(); // | Pour `new StringBuffer()` : 10ms
String sep = new String(", ");        // | Pour `new String()` : 10ms
int n = tab.length;                   // Balec

for (int i = 0; i < n; i++)           // | Pour `i < n` : (n + 1) * 0,1ms
                                      // | Pour `i++` : n * 0,5ms

                                      // Le tout multiplié par n ici
  sb.append(new String(tab[i]))       // | Pour `.append` : 2 * 3ms
    .append(sep);                     // | Pour `new String()` : 10ms
                                      // | Pour `tab[i]` : 0,1ms

System.out.println("tab = " + ch);    // | Pour `"tab =" + ch` : 10ms + 3ms
~~~

On a donc en complexité temporelle :

<center>
  20 + (n + 1) &times; 0.1 + n &times; 0.5 + n &times; (6 + 10 + 0.1) + 13<br>
  =<br>
  20 + (n + 1) &times; 0.1 + n &times; 16.6 + 13
</center>

-------------------------------------------------------------------------------

**Pourquoi l'utilisation de la classe `Vector` est-elle plus lente que celle de
la classe `ArrayList` ?**

L'utilisation de la classe `Vector` est plus lente car la JVM va synchroniser les
accès à la classe `Vector` mais ne va pas le faire pour les instances de la classe
`ArrayList`. Ceci permet de faire de la programmation multi-threads avec `Vector`.

-------------------------------------------------------------------------------

**Dans la programmation par processus, le code suivant permet à plusieurs processus
de stocker un objet dans un tableau tampon. Que se passe-t-il si un processus appelle
ce code alors qu'un processus est déjà entré dedans (et qu'il est à la ligne
`nbObjetsCourants++`) ?**

~~~java
public synchronized void depose(Object obj) {
  while (nbObjetsCourant == (taille - 1)) {
    try   { wait(); }
    catch (InterruptedException e) { }
  }

  try {
    tampon[nbObjetsCourant] = obj;
  } catch (Exception e) {
    System.out.println(e);
  }

  nbObjetsCourant++;
  produits++;
  notify();
}
~~~

Le fait que la définition de la méthode ait le mot clé `synchronized` permet
d'indiquer à la JVM que cette méthode est susceptible d'être appelée par plusieurs
threads. L'appel à `notify` permet d'indiquer à Java qu'un autre processus peut
appeler cette méthode.

De ce fait, si un autre processus appel cette méthode alors qu'un autre processus
est à l'instruction `nbObjectCourants++`, ce processus va se bloquer jusqu'à ce que
`notify` soit appelé.

**Que se passe-t-il si un processus entre dans la méthode et que `nbObjetsCourant` est au maxime (`== taille - 1)`?**

Dans ce cas, l'appel de cette méthode va bloquer le processus puisque la condition
du `while` sera évaluée a `true` et qu'il y aura un appel à `wait`.

**Quel est le principe du garbage collector (ramasse miettes) ?**

Le principe du garbage collector est de libérer l'espace mémoire occupé par les objets
qui ne sont plus utilisés dans le programme.

**Quel est le principe de JavaFX pour la création d'applications graphiques ?**

Le principe de JavaFX est de créer des applications graphiques en manipulant des
objets qui représentent des formes (ligne, cercle, etc.). Une application JavaFX
hérite de la classe `javafx.application.Application` et sa méthode `start` permet
d'accéder à une scène principale (`Stage`) pour dessiner les éléments. À cette scène
est associé une ou plusieurs scène possèdant une troupe d'objets qui gardent en
mémoire les éléments dessinés sur cette scène.


## Exercices

### Exercice 1

Créer une classe représentant une `Plage`.

Un object `Page` est composé d'un nom, de deux coordonées GPS réelles (`gpsx`,
`gpsy`) et d'un numéro unique d'instance (`no`). La classe possède un membre de
classe indiquant le nombre d'instances créées.

Le *constructeur par défaut* de `Page` initiliase les coordonnées à 0.
Vous écrirez également un *constructeur* qui prend en paramètre son nom et
ses coordonées. Surcharger la méthode `toString` pour retourner une représentation
d'un objet `Plage` par son nom, ses coordonées et son numéro d'instance.

~~~java
class Plage {
  static int nbInstances = 0;

  protected double gpsx;
  protected double gpsy;
  protected String nom;
  protected int no;

  /**
   * Constructeur par défaut.
   */
  Plage() {
    this.gpsx = 0;
    this.gpsy = 0;
    this.no   = this.nbInstances;

    this.nbInstances++;
  }

  /**
   * Constructeur avec paramètres
   */
  Plage(String nom, double gpsx, double gpsy) {
    this.nom  = nom;
    this.gpsx = gpsx;
    this.gpsy = gpsy;
    this.no   = this.nbInstances;

    this.nbInstances++;
  }

  /**
   * Surchage de la méthode `toString`
   */
  public String toString() {
    return "Plage " + this.nom + "(n°" + this.no + ", "
           + "cordonnées : x = " + this.gpsx
           + ", y = " + this.gpsy + ").";

  }
}
~~~

### Exercice 2

Créer une classe `PlageSurveillee` qui étend `Plage`. Un objet `PlageSurveillee`
possède en plus une variable de type `Categorie`. Le type `Categorie` est une
énumration à définir, contenant les constantes `FAMILLE`, `SURFEURS` et
`NATURISTES`. Le *constructeur par défaut* de `PlageSurveillee` initialise la
catégorie `cat` à `FAMILLE`. Un constructeur de `PlageSurveillee` prenant en
paramètre le nom et la valeur de sa catégorie *qui doit être définie*. Il doit
utiliser un constructeur de la classe mère.

*Rédéfinir* la méthode `toString` pour cette classe pour retourner une
représentation avec son nom, ses coordonnées, son numéro d'instance et sa catégorie.

~~~java
public enum Categorie {
  FAMILLE("familles"), SURFEURS("surfeurs"), NATURISTES("naturistes");

  protected String texte;

  Categorie(String texte) {
    this.texte = texte;
  }

  public String toString() {
    return texte;
  }
}

class PlageSurveillee extends Plage {
  protected Categorie cat;

  /**
   * Constructeur par défaut.
   */
  PlageSurveillee() {
    super();
    this.cat = Categorie.FAMILLE;
  }

  /**
   * Constructeur avec paramètres
   */
  PlageSurveillee(String nom, double gpsx, double gpsy, String categorie) {
    super(nom, gpsx, gpsy);

    try {
      this.cat = Categorie.valueOf(categorie);
    } catch (IllegalArgumentException e) {
      System.out.println("La catégorie spécifiée n'existe pas");
    }
  }

  /**
   * Surchage de la méthode `toString`
   */
  public String toString() {
    return "Plage " + this.nom + " pour " + this.cat
           + "(n°" + this.no + ", "
           + "cordonnées : x = " + this.gpsx
           + ", y = " + this.gpsy + ").";

  }
}
~~~

-------------------------------------------------------------------------------

Soit le code suivant :

~~~java
ArrayList<Plage> lesPlages = new ArrayList<>();

lesPlages.add(new Plage("l'Anse de la Baie", 49.13, 6.66));
lesPlages.add(new Plage("La baie de l'Anse", 42.53, 3.08));

ArrayList<PlageSurveillee> lesSurveillees = new ArrayList<>();

lesSurveillees.add(new PlageSurveillee("Santissu", 42.23, 6.66, Categorie.NATURISTES));
lesSurveillees.add(new Plage("ValenciennesPlage", 50.34, 3.5));

for (Plage p : lesPlages)
  System.out.println(p);

for (PlageSurveillee ps : lesSurveillees)
  System.out.println(ps);

lesPlages.add(new PlageSurveillee("Les Rouleaux", 43.95, -1.36, Categorie.SURFEUR));
~~~

**Quelle(s) ligne(s) déclenche(nt) une erreur, pourquoi ? Proposez une correction.**

La ligne suivant va lever une exception car le type que peut contenir l'ArrayList
ne correspond pas à celui que l'on essaie d'insérer :

~~~java
lesSurveillees.add(new Plage("ValenciennesPlage", 50.34, 3.5));
~~~

La solution consiste donc à utiliser un *wildcard* pour spécifier que cette
ArrayList peut accepter des éléments de type `Plage` ou des classes filles. Il
faut ensuite changer la boucle `for` pour refléter ceci :

~~~java?6,14,15
ArrayList<Plage> lesPlages = new ArrayList<>();

lesPlages.add(new Plage("l'Anse de la Baie", 49.13, 6.66));
lesPlages.add(new Plage("La baie de l'Anse", 42.53, 3.08));

ArrayList<? super Plage> lesSurveillees = new ArrayList<>();

lesSurveillees.add(new PlageSurveillee("Santissu", 42.23, 6.66, Categorie.NATURISTES));
lesSurveillees.add(new Plage("ValenciennesPlage", 50.34, 3.5));

for (Plage p : lesPlages)
  System.out.println(p);

for (Object ps : lesSurveillees)
  System.out.println((PlageSurveillee)ps);

lesPlages.add(new PlageSurveillee("Les Rouleaux", 43.95, -1.36, Categorie.SURFEUR));
~~~

### Exercice 3

Afin de pouvoir trier les collections d'objets de type `Plage` et d'objets de
type `PlageSurveillee`, ces classes doivent implémenter l'interface `Comparable`.

Réécrivez l'entête de ces classes et écrivez la fonction `int compareTo(Object o)`
nécessaire dans ces deux classes pour que le tri de `Plage` se fasse par nom en
ordre alphabétique et que le tri de `PlageSurveillee` se fasse par catégorie
puis par nom.

Aucune méthode de tri n'est demandée, la méthode `Collection.sort(liste)` de Java effectue ce tri.

Pour la réécriture des entêtes :

~~~java
class Plage implements Comparable {

class PlageSurveillee extends Plage implements Comparable {
~~~

> **Rappel** : [Fonctionnement de `compareTo`](generic.html)

Pour l'implémentation des méthodes `int compareTo(Object o)` :

~~~java
// Dans la classe Plage
public int compareTo(Object o) {
  return this.nom.compareTo(((Plage)o).nom);
}

// Dans la classe PlageSurveillee
public int compareTo(Object o) {
  PlageSurveillee autre = (PlageSurveillee)o;
  int resultat = this.cat.texte.compareTo(autre.cat.texte);

  // Si deux éléments sont de la même catégorie, on les
  // classe par nom.
  if (resultat == 0)
    return this.nom.compareTo(autre.nom);
  else
    return resultat;
}
~~~

-------------------------------------------------------------------------------

~~~java
Collections.sort(lesPlages);
Collections.sort(lesSurveillees);
~~~

Utilisez la notation lambda pour trier une liste de `Plage` par nom en ordre
alphabétique inverse, en utilisant la fonction `Collections.sort(liste, comparateur)` :

> **Note** : Il y a une erreur dans le code de l'énoncé ; `Collections.sort` ne
> renvoie rien et modifie directement la liste donnée en paramètre. Le code original
> de l'énoncé était :
>
> ~~~java
> ArrayList<Plage> plagesTriees = Collections.sort(lesPlages);
> ArrayList<PlageSurveillee> surveilleesTriees = Collections.sort(lesSurveillees);
> ~~~

En utilisant la notation lambda on a donc :

~~~java
// Il suffit de reprendre le code des méthodes compareTo mais ici
// on n'a pas accès au `this` mais à deux éléments à comparer.

Collections.sort(lesPlages, (p1, p2) -> {
  return p1.nom.compareTo(p2.nom);
});

Collections.sort(lesSurveillees, (ps1, ps2) -> {
  int resultat = ps1.cat.texte.compareTo(ps2.cat.texte);

  // Si deux éléments sont de la même catégorie, on les
  // classe par nom.
  if (resultat == 0)
    return ps1.nom.compareTo(ps2.nom);
  else
    return resultat;
});
~~~
