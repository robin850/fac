---
layout: default
title: Info
section: prrel
---

## Semestre 2

* [Corrigé du TD n°1](informatique/correction-td1.html)
* [Corrigé du TD n°3](informatique/correction-td3.html)

## Semestre 1

* [Sujet de DS 2014-2015](https://drive.google.com/uc?export=download&id=0B1b6pH21vC4eM3hhRjNnLTRWRWc)
* [Correction exercice 2 DS première session](informatique/correction-exercice2.html)
* [Correction sujet de DS 2014-2015](informatique/correction-ds.html)

## Mémo pour les langages

<div id="memo">

<ul class="nav-tabs">
  <li class="active"><a href="#pseudo">Pseudo code</a></li>
  <li><a href="#scala">Scala</a></li>
</ul>

<div class="tab-content">
  <div class="tab-pane active" id="pseudo">
  <br>
    <table class="table">
      <thead>
        <tr>
          <th>Instruction</th>
          <th>Code</th>
        </tr>
      </thead>

      <tr>
        <td>Types de base</td>
        <td>{% highlight pseudo %}
1, vrai, faux, "chaîne", 'c'
{% endhighlight %}
        </td>
      </tr>
      <tr>
        <td>Affectation</td>
        <td>{% highlight pseudo %}
a <- 15;
{% endhighlight %}</td>
      </tr>
      <tr>
        <td>Lecture</td>
        <td>{% highlight pseudo %}
lire(variable)
{% endhighlight %}</td>
      </tr>
      <tr>
        <td>Écriture</td>
        <td>{% highlight pseudo %}
écrire("Hello world")
{% endhighlight %}</td>
      </tr>
      <tr>
        <td>Fonction</td>
        <td>{% highlight pseudo %}
fonction nom(Entrée: a : entier, Sortie: entier)
  var b, c, d : entier;
début
  // ...
fin
{% endhighlight %}</td>
      </tr>
      <tr>
        <td>Procédure</td>
        <td>{% highlight pseudo %}
procédure nom(Entrée a : entier)
  VAR b, c, d : entier;
début
  // ...
fin
{% endhighlight %}</td>
      </tr>
      <tr>
        <td>Condition</td>
        <td>{% highlight pseudo %}
si (a > b) alors
  // ...
sinon
  // ...
fin
{% endhighlight %}</td>
      </tr>
      <tr>
        <td>Boucle pour</td>
        <td>{% highlight pseudo %}
pour i allant de 1 à 10 pas 2
  // ...
fin
{% endhighlight %}</td>
      </tr>
      <tr>
        <td>Boucle tant que</td>
        <td>{% highlight pseudo %}
tant que (i <= a) faire
  // ...
fin
{% endhighlight %}</td>
      </tr>
    </table>
  </div>

    <div class="tab-pane" id="scala">
      <br>
      <table class="table">
        <thead>
          <tr>
            <th>Instruction</th>
            <th>Code</th>
          </tr>
        </thead>
        <tr>
          <td>Types de base</td>
          <td>{% highlight scala %}
1, true, false, "chaîne", 'c'
{% endhighlight %}
          </td>
        </tr>
        <tr>
          <td>Affectation</td>
          <td>{% highlight scala %}
// Pour définir
var a = 10;

// Puis plus loin dans le programme
a = 20;
{% endhighlight %}</td>
        </tr>
        <tr>
          <td>Lecture</td>
          <td>{% highlight scala %}
val variable = readInt();  /* Pour lire un entier  */
val variable = readLine(); /* Pour lire une chaîne */
{% endhighlight %}</td>
        </tr>
        <tr>
          <td>Écriture</td>
          <td>{% highlight scala %}
println("Hello world")
{% endhighlight %}</td>
        </tr>
        <tr>
          <td>Fonction</td>
          <td>{% highlight scala %}
def nom(a: Int) : Int = {
  // ...
}
{% endhighlight %}</td>
        </tr>
        <tr>
          <td>Condition</td>
          <td>{% highlight scala %}
if (a > b) {
  // ...
} else {
  // ...
}
{% endhighlight %}</td>
        </tr>
        <tr>
          <td>Boucle pour</td>
          <td>{% highlight scala %}
for (a <- 1 to 10) {
  // ...
}
{% endhighlight %}</td>
        </tr>
        <tr>
          <td>Boucle tant que</td>
          <td>{% highlight scala %}
while (i <= a) {
  // ...
}
{% endhighlight %}</td>
        </tr>
      </table>
    </div>
  </div>

</div>
