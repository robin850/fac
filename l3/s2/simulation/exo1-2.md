---
layout: default
section: l3/s2
title: Simu
---

**Sujet**

Estimer, à l'aide de la simulation, la valeur de &pi; (3,1415...), en sachant que la superficie d'un cercle de rayon 1 est égale à &pi;

----------------------------------------------------------------------------------

~~~c
#include <stdio.h>
#include <stdlib.h>
#include <time.h>

double point() {
  return rand() / (double)RAND_MAX;
}

int main() {
  double x, y;
  int i;
  int success = 0;

  srand(time(NULL));

  for (i = 0; i < 2000; i++) {
    x = point();
    y = point();

    if ((x*x + y*y) <= 1)
      success++;
  }

  double est_pi = ((double)success / i) * 4;

  printf("%lf", est_pi);

  return 0;
}
~~~
