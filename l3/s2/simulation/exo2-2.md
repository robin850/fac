---
layout: default
section: l3/s2
title: Simu
---

### Solution avec la méthode du rejet

~~~c
#include <stdio.h>
#include <stdlib.h>

/**
 * La fonction utilisée dans la formule de l'intégrale.
 */
double g(double x) {
  return x * x * x;
}

int main() {
  int z;
  int S = 0;
  int k = 0;
  int n = 25555555;
  double M = 1.01;
  double x, y;

  srand(42);

  while (k < n) {
    k++;

    x = rand() / (double)RAND_MAX;
    y = rand() / (double)RAND_MAX;

    if (y <= g(x))
      z = 1;
    else
      z = 0;

    S = S + z;
  }

  // Toutes les multiplications par v(G) ne sont pas faîtes car ici
  // G représente l'intervalle [0, 1] et v, son volume, serait donc
  // de 1 - 0 = 1.
  double est_p   = S/(double)n;
  double est_J   = est_p * M;
  double est_var = (1 / (double)n) * M * est_J *  (1 - est_p);

  printf("est_p : %lf\n", est_p);
  printf("est_J : %lf\n", est_J);
  printf("est_var : %lf\n", est_var);
}
~~~

### Solution par échantillonage

~~~c
#include <stdio.h>
#include <stdlib.h>

/**
 * La fonction utilisée dans la formule de l'intégrale.
 */
double g(double x) {
  return x * x * x;
}

int main() {
  int z;
  int k = 0;
  int n = 2555555;
  double S = 0.0;
  double q = 0.0;
  double x, y;

  srand(42);

  while (k < n) {
    k++;

    x = rand() / (double)RAND_MAX;
    y = g(x);

    S = S + y;
    q = q + (y * y);
  }

  // Toutes les multiplications par v(G) ne sont pas faîtes car ici
  // G représente l'intervalle [0, 1] et v, son volume, serait donc
  // de 1 - 0 = 1.
  double est_y   = S/(double)n;
  double est_J   = est_y * 1;
  double est_var = (1 / (double)n) * (q/(double)n - est_y * est_y);

  printf("est_y : %lf\n", est_y);
  printf("est_J : %lf\n", est_J);
  printf("est_var : %lf\n", est_var);
}
~~~
