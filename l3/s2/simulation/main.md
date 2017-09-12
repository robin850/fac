---
layout: default
title: Simu
section: l3/s2
---

~~~c
#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#include <math.h>

/**
 * Fonction permettant de générer de manière aléatoire
 * des temps d'inter-arrivées pour les pièces du système,
 * en suivant une loi exponentielle.
 */
double fct_gen_tia() {
  double z = (double)rand() / (double)RAND_MAX;

  // On choisit de modifier le lambda
  // return -1 / 0.2 * log(z)
  if (z != 0)
    return -1 / 0.33 * log(z);
  else
    return fct_gen_tia();
}

/**
 * Fonction `f` représentant le calcul des valeurs pour
 * la loi triangulaire.
 */
double f(double x) {
  if (x >= 2 && x < 3)
    return 0.5;
  else if (x >= 3 && x < 6)
    return 0.5 * (2 - (x / 3));
  else
    return 0;
}

/**
 * Fonction permettant de générer des temps de services
 * aléatoires en suivant une loi triangulaire dans le
 * service.
 */
double fct_gen_ts() {
  double x, y;

  x = ((double)rand() / (double)RAND_MAX) * 4 + 2;
  y = (double)rand() / (double)RAND_MAX * (0.5);

  if (y <= f(x))
    return x;
  else
    return fct_gen_ts();
}

int main() {
  double t_occ;            // Temps d'occuptation du serveur
  double t_cum_file = 0.0; // Cumul des temps d'attente en file
  double t_cum = 0.0;      // Cumul des temps de séjour dans le système
  double t_arr = 0.0;
  double t_fin = 200.0;
  double t_dep = t_fin;
  double t = 0.0;
  double delta;

  int n = 0;          // Compteur du nombre de pièces arrivées dans le système
  int q = 0;          // Nombre de pièce présentes dans le système.
  int etat_serv = 0;  // Serveur vide ou plein

  while (t < t_fin && t_arr <= t_fin && t_dep <= t_fin) {
    /*******************************
     * Code pour gérer une arrivée *
     *******************************/
    if (t_arr < t_dep) {
      delta = t_arr - t;
      t_cum += delta * (q + etat_serv);
      t_cum_file += delta * q;
      n++;
      t = t_arr;
      t_arr = t + fct_gen_tia();

      if (etat_serv == 0) {
        etat_serv = 1;
        // Was: t_dep = t + fct_gen_ts();
        t_dep = t_arr + fct_gen_ts();
      } else {
        q = q + 1;
        t_occ = t_occ + delta;
      }
    }

    /*****************************
     * Code pour gérer un départ *
     *****************************/
    if (t_dep <= t_arr) {
      delta = t_dep - t;
      t_cum += delta * (q + etat_serv);
      t_cum_file += delta * q;
      t = t_dep ;

      if (q > 0) {
        t_dep = t + fct_gen_ts();
        q--;
      } else {
        etat_serv = 0;
        t_dep = 2 * t_fin;
      }

      t_occ = t_occ + delta;
    }
  }

  // Calcul des résultats de la simulation
  double t_moy_sejour  = (double)t_cum / n;
  double t_moy_attente = t_cum_file / n;

  double nb_moy_clients_systeme = t_cum / t_fin;
  double nb_moy_clients_attente = t_cum_file / t_fin;

  double taux_occ_serveur = (t_occ / t_fin) * 100;

  printf("Temps moyen passe par un client dans le systeme = %lf\n", t_moy_sejour);
  printf("Temps moyen passe par un client a attendre = %lf\n\n", t_moy_attente);

  printf("Nombre moyen de clients dans le systeme = %lf\n", nb_moy_clients_systeme);
  printf("Nombre moyen de clients en attente = %lf\n\n", nb_moy_clients_attente);

  printf("Taux d'occuptation du serveur = %lf\n", taux_occ_serveur);

  return 0;
}
~~~
