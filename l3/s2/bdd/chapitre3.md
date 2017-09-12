---
layout: default
title: BDD
section: l3/s2
---

# Les langages relationnels

## LDD (Langage de Description des Données)

* `CREATE DOMAIN`
* `CREATE TABLE`

## LMD (Langage de Modélisation des Données)

* `SELECT`
* `SELECT DISTINCT` (sans doublons)
* `SELECT AS` (auto-jointure)
* Fonctions d'aggrégats
  - `COUNT`
  - `SUM`
  - `AVG`
  - `MIN`
  - `MAX`
* `GROUP BY` + `HAVING` (aggrégats multiples)

> Dans le cas d'un aggrégat multiple, le/les attribut(s) de partition
> doivent être dans la clause `SELECT`.

## LCD (Langage de Contrôle des Données)

* `INSERT INTO`
* `ALTER TABLE`
* `DELETE FROM`
* `UPDATE table SET a = b, ...`
* `CROISS JOIN` (produit cartésien) ou un classico `SELECT * FROM A, B`.
* `CREATE VIEW`
* `DROP VIEW (RESTRICT | CASCADE)`

## Opérateurs ensemblistes

* {1, 2, 1} U {1, 2, 4} = {1, 1, 1, 2, 2, 4}
* {1, 2, 1} n {1, 2, 4} = {1, 2}
* {1, 2, 1} - {1, 2, 4} = {1}

* R U (S n T) = (R U S) n (R U T) si R, S, T ensembles
* Faux si R, S, T sont des multi-ensembles! Le résultat vaut {1} dans ce cas

## Contraintes de domaines

~~~sql
CREATE DOMAIN couleursvin CHAR(10)
CONSTRAINT couleurs
CHECK (VALUE IN ('Rouge', 'Rose', 'Blanc'))
~~~

~~~sql
CREATE ASSERTION anneemax CHECK
(SELECT MAX(millesime) FROM VINS)
<= EXTRACT(YEAR FROM CURRENT_DATE)
~~~

## Transactions

Niveaux d'isolation:

| Niveau d'isolation | Lecture sale | Lecture non répétable | Fantômes |
|--------------------|--------------|-----------------------|----------|
| READ UNCOMMITTED   |  &#x2713;    |      &#x2713;         | &#x2713; |
| READ COMMITTED     |      ✗       |      &#x2713;         | &#x2713; |
| REPEATABLE READ    |      ✗       |          ✗            | &#x2713; |
| SERIALIZABLE       |      ✗       |          ✗            |    ✗     |
