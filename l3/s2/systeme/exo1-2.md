---
layout: default
section: l3/s2
title: P. Système
---

**Sujet**

<table>
  <tr>
    <th>Processus A</th>
    <th>Processus B</th>
  </tr>

  <tr>
    <td>
      {% highlight c %}
while (true) {
  A1();
  A2();
}
{% endhighlight %}
    </td>
    <td>
      {% highlight c %}
while (true) {
  B1();
  B2();
}
{% endhighlight %}
    </td>
  </tr>
</table>

Synchroniser les processus A et B à l'aide de sémaphores de telle sorte qu'il y ait plus d'actions A terminées que d'actions B commencées à *chaque instant*.

-------------------------------------------------------------------------------

~~~c
// Initialisation
S = 0;

// Programme A
while (true) {
  A1();
  A2();
  V(S)
}

// Programme B
while (true) {
  P(S)
  B1();
  B2();
}
~~~
