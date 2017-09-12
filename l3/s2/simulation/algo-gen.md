---
layout: default
section: l3/s2
title: Simu
---

Ces deux algorithmes permettent d'appliquer la méthode du rejet ou par échantillonage de manière générale en changeant les valeurs souhaitées dans les `#define` en début de programme.

Avec :

* `G` : La fonction à intégrer.
* `A` : Borne inférieure.
* `B` : Borne supérieure.
* `V` : Volume de l'intervalle ; calculé automatiquement.
* `M` : Majorant de la fonction.
* `N` : Nombre d'itérations.

-------------------------------------------------------------------------------

### Méthode du rejet

~~~c
#include <stdio.h>
#include <stdlib.h>

#define G(x) x * x + x
#define A 1
#define B 3
#define V (B - A)
#define M 12.01
#define N 25555555

int main() {
  int z;
  int S = 0;
  int k = 0;
  double x, y;

  srand(42);

  while (k < N) {
    k++;

    x = (rand() / (double)RAND_MAX) * (int)V + (int)A;
    y = (rand() / (double)RAND_MAX) * (double)M;

    if (y <= G(x))
      z = 1;
    else
      z = 0;

    S = S + z;
  }

  double est_p   = S/(double)N;
  double est_J   = est_p * (double)M * (int)V;
  double est_var = (1 / (double)N) * ((double)M * (int)V) * ((double)M * (int)V) * est_p *  (1 - est_p);

  printf("est_p : %lf\n", est_p);
  printf("est_J : %lf\n", est_J);
  printf("est_var : %3.10lf\n", est_var);
}
~~~

### Méthode par échantillonage


~~~c
#include <stdio.h>
#include <stdlib.h>

#define G(x) x * x + x
#define A 1
#define B 3
#define V (B - A)
#define N 999999999

int main() {
  int z;
  int k = 0;
  double S = 0.0;
  double q = 0.0;
  double x, y;

  srand(42);

  while (k < N) {
    k++;

    x = ((rand() / (double)RAND_MAX) * (int)V) + (int)A;
    y = G(x);

    S = S + y;
    q = q + (y * y);
  }

  double est_y   = S/(double)N;
  double est_J   = est_y * V;
  double est_var = (1 / (double)N) * V * V * (q/(double)N - est_y * est_y);

  printf("est_y : %lf\n", est_y);
  printf("est_J : %lf\n", est_J);
  printf("est_var : %lf\n", est_var);
}
~~~
