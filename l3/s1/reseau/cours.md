---
title: Réseau
section: l3/s1
layout: default
---

## Cours de Réseau

### Définitions, principes et rappels

DNS signie Domain Name System (système de noms de domaines). Il répond aux requêtes des domaines de premier niveau / TLD (Top Level Domains). Par exemple `.fr`, `.com` ou `.be` sont des TLD.

Le but du DNS est de résoudre les noms et les IPs ; il réalise une correspondance entre les noms de composants et leurs adresses IP.

<pre>
                                  Résolution directe
                            Noms -------------------> IP
                                 <-------------------
                                 Résolution inversée
</pre>

Quelques acronymes:

| Acronyme | Signification |
|:--------:|---------------|
| SOA | Start of Authority |
| NS  | Name Server        |
| A   | Address            |
| MX  | Mail Exchange      |
| CNAME | Canonical name   |

> **Rappel**: Un réseau est identifiable par :
>
> - Un nom: PRN3 par exemple
> - Une adresse IP:  `X.Y.Z.T` (où `X.Y.Z` est *l'id réseau* et `T` est *l'id hôte*). De plus, X, Y, Z et T sont compris entre 0 et 255.
> - Un masque de réseau :
>   * Classe A : 255.0.0.0
>   * Classe B : 255.255.0.0
>   * Classe C : 255.255.255.0
>
> En classe A, le premier bit est bloqué, en B les deux premiers et en C les trois premiers.

Pour créer des sous-réseaux, on agit sur la partie *id-hôte* du masque pour récupérer les bits permettant de créer des sous réseaux.

En classe C, il y a 1 octet pour les hôtes. Le masque par défaut sécrit :

<pre>
255.255.255.10000000
   24    +  1         = 25 bits
</pre>

Nouveaux masques :

<pre>

                       00000000   X.Y.Z.0
Classe C : 255.255.255.xxxxxxxx
                       10000000   X.Y.Z.128 (128 = 2<sup>7</sup>)
</pre>

### Calcul d'id réseau

Prenons par exemple l'IP `192.168.20.65` :

<pre>
Id<sub>res</sub> = &amp; IP<sub>hôte</sub>  = 192.168.20.65
                             Masque           255.255.255.128
                         -----------------    ----------------
                                              192.168.20.0
</pre>

L'id du réseau est donc `192.168.20.0`.

Règles de calcul pour l'opérateur `&` (et logique) :

| Opération | Résultat |
|:---------:|:--------:|
| 0 &amp; 0 | 0        |
| 0 &amp; 1 | 0        |
| 1 &amp; 0 | 0        |
| 1 &amp; 1 | 1        |
| x &amp; 255 | x     |

Ici, on a 65 = 64 + 1 = 2<sup>6</sup> + 1 &times; 2<sup>0</sup> d'où :

<pre>
65  -> 0100 0001
128 -> 1000 0000
       ---------
       0000 0000
</pre>

### Exercice

Un administrateur réseau travaille sur le réseau d'adresse IP : `194.100.40.0` / `255.255.255.0`. Il décide de créer des sous-réseaux et utilise le masque `255.255.255.224`. Questions :

**1. Combien peut-il créer de sous-réseaux ?**

On a 224 = 256 - 32 = 2<sup>8</sup> - 2<sup>5</sup> = 128 + 64 + 32.

On a donc 2<sup>3</sup> = 8 sous réseaux possibles.

**2. Combien y a-t-il d'hôtes adressable dans chaque sous-réseau ?**

*Je suis pas sûr de la réponse ici, à prendre avec des pincettes*

On a 256 - 224 = 32 = 2<sup>5</sup>. On a donc  2<sup>5</sup> - 2 = 30 hôtes adressable dans chaque sous-réseau.

**3. A quels sous-réseaux appartiennent les hôtes:**

- PC4 : 194.100.40.138
- PC9 : 194.100.40.250

<pre>
&amp; IP  194.100.40.138
  M  255.255.255.224
     ---------------
     194.100.40.128

&amp; IP 194.100.40.250
  M  255.255.255.224
     ---------------
     194.100.40.224
</pre>

### Service réseau

Un service = un programme (daemon) + un port d'écoute (TCP/UDP).

Un service réseau suit le modèle clients - serveur.

### Quelques ports à connaître

* 21: FTP
* 22: SSH
* 25: SMTP
* 80: HTTP
* 110: POP
* 1521: Oracle
* 3306: MySQL
* 5432: PostgreSQL
