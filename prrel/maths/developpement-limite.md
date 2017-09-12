---
title: Mathématiques
layout: default
section: prrel
---

# Le développement limité

Le développement limité consiste à transformer une fonction en un polynôme qui est
une somme d'éléments. Ces éléments sont trouvés à partir de la formule de
Taylor-Young.

Le but du développement limité est de trouver un polynôme qui se rapproche le plus
possible d'une fonction. Pour certaines fonctions, il est difficile de :

* Les dériver
* Trouver les limites
* Prouver qu'elles sont intégrables

Si on arrive à trouver le développement limité d'une fonction, on peut ensuite
dériver, trouver les limites, etc.

## Formule de Taylor-Young

C'est cette formule qui permet de trouver le développement limité d'une fonction :

<center>Si on a une fonction f dérivable n fois au point x<sub>0</sub>, le
développement limité en ce point est :</center>

<img src="taylor.svg">

Où :

* `k!` est la factorielle de k (e.g. 3! = 1 &times; 2 &times; 3).
* <code>f<sup>(k)</sup></code> est la k-ième dérivée de la fonction f.

Plus on calcul d'éléments de cette formule, et plus on s'approche de la fonction
initiale.

### Exemple

Prenons par exemple, la fonction :

![](1.svg)

#### Première itération

On applique la formule de Taylor-Young une première fois. Ici, on va prendre x<sub>0</sub> = 0.

On a donc :

![](3.svg)

<div id="sin_1" style="height: 300px;"></div>

> **Petite aide** : Pour dériver les fonctions `sin` et `cos` plusieurs fois
> facilement, on peut se représenter le cercle trigonométrique, placer les fonctions
> sur les extrémités des axes et tourner dans le sens des aiguilles d'une montre pour
> avoir la fonction dérivée.
>
> Par exemple, la dérivée de `sin(x)` est `cos(x)`, la dérivée de `cos(x)` est `-sin(x)`, etc.
>
> <img src="cercle.svg" style="width: 300px">

#### Deuxième itération

C'est vraiment pas terrible, du coup on calcule la deuxième itération :

![](4.svg)

Puisque la fonction est paire, on tombe sur un zéro et on retrouve la même formule que précédemment, on applique donc une nouvelle itération :

![](5.svg)

<div id="sin_2" style="height: 300px;"></div>

### Troisième itération

C'est déjà mieux mais on peut essayer de refaire une itération (ou deux puisque le
prochain membre de la formule de Taylor-Young produira un zéro ici) pour se
rapprocher encore un peu plus :

![](6.svg)

<div id="sin_3" style="height: 300px;"></div>

<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
<script type="text/javascript">
  google.charts.load('current', {'packages':['corechart', 'geochart']});
  google.charts.setOnLoadCallback(draw_sin1);
  google.charts.setOnLoadCallback(draw_sin2);
  google.charts.setOnLoadCallback(draw_sin3);

  function sin_values() {
    var values = [];

    for (var i = -5; i < 5; i += 0.1)
      values.push([i, Math.sin(i)]);

    return values;
  }

  function f2(x) {
    return x - ((Math.pow(x, 3)) / 6);
  }

  function f3(x) {
    return x - ((Math.pow(x, 3)) / 6) + ((Math.pow(x, 5)) / 120);
  }

  var values = sin_values();

  function draw_sin3() {
    var new_values = values.map(t => [t[0], t[1], f3(t[0])]);
    new_values.unshift(['x', 'sin(x)', 'f(x)']);

    var data = google.visualization.arrayToDataTable(new_values);

    var options = {
      title: '',
      curveType: 'function',
      legend: { position: 'bottom' },
      vAxis: {
        viewWindow: {max: 2, min: -2}
      }
    };

    var chart = new google.visualization.LineChart(document.getElementById('sin_3'));

    chart.draw(data, options);
  }

  function draw_sin2() {
    var new_values = values.map(t => [t[0], t[1], f2(t[0])]);
    new_values.unshift(['x', 'sin(x)', 'f(x)']);

    console.log(new_values);

    var data = google.visualization.arrayToDataTable(new_values);

    var options = {
      title: '',
      curveType: 'function',
      legend: { position: 'bottom' },
      vAxis: {
        viewWindow: {max: 2, min: -2}
      }
    };

    var chart = new google.visualization.LineChart(document.getElementById('sin_2'));

    chart.draw(data, options);
  }

  function draw_sin1() {
    var new_values = values.map(t => [t[0], t[1], t[0]]);
    new_values.unshift(['x', 'sin(x)', 'f(x)']);

    var data = google.visualization.arrayToDataTable(new_values);

    var options = {
      title: '',
      curveType: 'function',
      legend: { position: 'bottom' },
      vAxis: {
        viewWindow: {max: 2, min: -2}
      }
    };

    var chart = new google.visualization.LineChart(document.getElementById('sin_1'));

    chart.draw(data, options);
  }
</script>
