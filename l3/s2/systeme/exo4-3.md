---
layout: default
section: l3/s2
title: P. Système
---

On considère une mémoire paginée et segmentée. Une case dans la mémoire vaut 4ko (4096). La mémoire centrale comporte 15 cases numérotées de 1 à 15. On considère deux processus A et B.

Le processus A a 3 segments qui sont S1A, S2A, S3A. Ils ont respectivement la taille suivante, 8ko, 12ko, 4ko.

Le processus B a 2 segments qui sont S1B, S2B. Ils ont respectivement la taille suivante, 16ko et 8ko.

Pour le procesus A (**en mémoire**):

* La page 1 et 2 de S1A (cases 4 et 5)
* la page 2 de S2A (case 10)
* et la page 1 de S3A (case 6).

Pour le processus B (**en mémoire**):

* Les pages 2 et 3 de S1B (cases 11 et 2)
* et la page 1 de S2B (case 15)

Questions :

Représenter sur un dessin / schéma, les tables de segments, les tables de pages et la mémoire centrale pour les deux processus.

-------------------------------------------------------------------------------

> **Note** : Normalement la deuxième colonne des tables de segments pointe *via* une flèche sur la table des pages correspondante mais pas le courage de faire une image pour représenter ça. :-)

### Table de segments

Dans la table des segments, on précise la taille de la page dans la première colonne et on fait pointer vers la table de la page la seconde colonne.

#### Processus A

<table style="width: 200px">
  <tr>
    <td>8</td>
    <td></td>
  </tr>
  <tr>
    <td>12</td>
    <td></td>
  </tr>
  <tr>
    <td>4</td>
    <td></td>
  </tr>
</table>

#### Processus B

<table style="width: 200px">
  <tr>
    <td>16</td>
    <td></td>
  </tr>
  <tr>
    <td>8</td>
    <td></td>
  </tr>
</table>

### Table de pages

Dans les tables de pages, la première colonne comporte un bit de validité (V ou I). La page est valide si elle pointe sur une case en mémoire, sinon elle est invalide. On sait combien de pages comporte un segment par rapport à sa taille. Une page = une case et ici la taille d'une case est de 4ko. On divise donc la taille du segment par 4 pour avoir son nombre de pages.

#### Processus A

**Segment S1A** (8ko)

<table style="width: 200px">
  <tr>
    <td>V</td>
    <td>Case 4</td>
  </tr>
  <tr>
    <td>V</td>
    <td>Case 5</td>
  </tr>
</table>

**Segment S2A** (12ko)

<table style="width: 200px">
  <tr>
    <td>I</td>
    <td></td>
  </tr>
  <tr>
    <td>V</td>
    <td>Case 10</td>
  </tr>
  <tr>
    <td>I</td>
    <td></td>
  </tr>
</table>

**Segment S3A** (4ko)

<table style="width: 200px">
  <tr>
    <td>V</td>
    <td>Case 6</td>
  </tr>
</table>

#### Processus B

**Segment S1B** (16ko)

<table style="width: 200px">
  <tr>
    <td>I</td>
    <td></td>
  </tr>
  <tr>
    <td>V</td>
    <td>Case 11</td>
  </tr>
  <tr>
    <td>V</td>
    <td>Case 2</td>
  </tr>
  <tr>
    <td>I</td>
    <td></td>
  </tr>
</table>

**Segment S2B** (8ko)

<table style="width: 200px">
  <tr>
    <td>V</td>
    <td>Case 15</td>
  </tr>
  <tr>
    <td>I</td>
    <td></td>
  </tr>
</table>

### Mémoire centrale

Pour représenter la mémoire centrale, on utilise un seul tableau en remplissant les cases qui sont présentes dans les tables de pages. On remplit chaque case avec le numéro de page et le segment la référençant.

<table>
  <tr>
    <th>n°</th>
    <th>Référencé par</th>
  </tr>
  <tr>
    <td>1</td>
    <td></td>
  </tr>
  <tr>
    <td>2</td>
    <td>Page 3 de S1B</td>
  </tr>
  <tr>
    <td>3</td>
    <td></td>
  </tr>
  <tr>
    <td>4</td>
    <td>Page 1 de S1A</td>
  </tr>
  <tr>
    <td>5</td>
    <td>Page 2 de S1A</td>
  </tr>
  <tr>
    <td>6</td>
    <td>Page 1 de S3A</td>
  </tr>
  <tr>
    <td>7</td>
    <td></td>
  </tr>
  <tr>
    <td>8</td>
    <td></td>
  </tr>
  <tr>
    <td>9</td>
    <td></td>
  </tr>
  <tr>
    <td>10</td>
    <td>Page 2 de S2A</td>
  </tr>
  <tr>
    <td>11</td>
    <td>Page 2 de S1B</td>
  </tr>
  <tr>
    <td>12</td>
    <td></td>
  </tr>
  <tr>
    <td>13</td>
    <td></td>
  </tr>
  <tr>
    <td>14</td>
    <td></td>
  </tr>
  <tr>
    <td>15</td>
    <td>Page 1 de S2B</td>
  </tr>
</table>
