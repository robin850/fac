---
layout: default
section: l3/s2
title: P. Système
---

1\. Des véhicules traversant un pont sont représentés par des processus. Faire en sorte qu'un seul véhicule traverse le pont à la fois.

~~~c
S = 1

P(S)
// traverser le pont
V(S)
~~~

-----------------------------------------------------------------------------

2\. Maintenant, faire en sorte que les processus paires soient prioritaires et que les processus impaires ne traversent le pont que si tous les processus paires l'ont traversés.

~~~

----------------------

                        <-  <-  <-  <-
->  ->  ->  ->  ->  ->

----------------------

~~~

Les processus qui attendent ne peuvent traverser le pont que si tous les processus ont quitté le pont.

Il n'y a pas d'exclusion mutuelle car chaque voiture représente un processus.

~~~c
// Initialisation
compteur = 0;
S1 = 1; // Acces
S2 = 0; // Inverse
S3 = 1; // Compteur

P(S3);
compteur++;

if (compteur == 1)
  P(S1);

V(S3);

// Traverser le pont

P(S3)

compteur--;

if (compteur == 0)
  V(S1);

V(S3);
~~~

-------------------------------------------------------------------------------

3\. Un seul véhicule à la fois sur le pont. Une fois arrivé au bout du pont, il y a 5 cabines de péages.

~~~
v                            z
                            /
---------------------------/
                             |
            ->               |
                             |
---------------------------\
                            \
~~~

Code :

~~~c
S1 = 1
S2 = 5
~~~

-------------------------------------------------------------------------------

4\. Des camions et des bus traversent le pont. Arrivé au bout, les camions doivent se peser avant de payer. Les bus doivent obligatoirement payé mais ne sont pas obliger de se peser avant le paiment. Un seul véhicule peut paye à la fois, et un seul véhicule peut se peser à la fois.

~~~
                                 /
--------------------------------/ B

               ->

--------------------------------\ P
                                 \
~~~

Code :

~~~c
/**
 * Initialisation
 */
SAcc   = 1;
Sbal   = 1;
Speage = 1;

/**
 * Camion V -> Z
 */
P(Sacc);
// Traver le pont
V(Sacc);
P(Sbal);
P(Speage);
// Se peser
// Payer
V(Speage);
V(Sbalance);


/**
 * Bus V -> Z
 */
P(Sacc);
// Traverser le pont
V(Sacc);
P(Speage);
P(Sbalance);
// Payer
// Se peser
V(Sbal);
V(Speage);
~~~

> Pour qu'il n'y ait pas interblocage des processus, il faut prendre les jetons dans le même ordre ou n'avoir qu'un seul jeton pour toutes les ressources ou séparer les actions.
>
> ~~~c
> P(Sbalance)
> // Se peser
> V(Sbalancer)
>
> P(Speage)
> // Payer
> V(Speage)
> ~~~
