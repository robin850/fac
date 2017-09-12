---
layout: default
section: l3/s2
title: BDD
---

# Utilisation des triggers

## Création de triggers

### Pour les deux

* Les affectations de variables se font *via* `:=`
* Les comparaisons d'égalité se font *via* `=`
* Il faut utiliser `INTO` dans une requête SQL pour injecter une valeur
  dans une variable (e.g. `SELECT champ INTO variable FROM table`).
* Pour faire des conditions :

    ~~~sql
    IF ... THEN

    -- Sans "e" entre le "s" et le "if"
    ELSIF ... THEN

    ELSE

    END IF;
    ~~~

### Pour Oracle

~~~sql
CREATE TRIGGER nom_du_trigger
BEFORE INSERT ON NomDeLaTable
DECLARE
  variable TYPE;
BEGIN
  -- Code du trigger
END nom_du_trigger;
~~~

* Pour accéder à la nouvelle entrée depuis la définition du *trigger*, il faut
  utiliser la pseudo entrée `:new` (e.g. `:new.nom` renvoie la valeur de la colonne
  `nom` de la nouvelle entrée).

### Pour PostgreSQL

Il vaut mieux créer une fonction et l'appeler dans le *trigger* :

~~~sql
CREATE FUNCTION nom_de_la_fonction()
RETURNS trigger AS
$$
DECLARE
  variable TYPE;
BEGIN
  -- Code du trigger

  -- Il vaut mieux toujours retourner quelque chose à la fin de la
  -- fonction sinon il peut y avoir des erreurs même si osef de ce
  -- qui est renvoyé pour les TPs.
  RETURN NULL;
END
$$ LANGUAGE plpgsql;

CREATE TRIGGER nom_du_trigger BEFORE INSERT ON NomDeLaTable
FOR EACH ROW
EXECUTE PROCEDURE nom_de_la_fonction();
~~~

* Pour accéder à la nouvelle entrée depuis la définition du *trigger*, il faut
  utiliser la pseudo entrée `NEW` (e.g. `NEW.nom` renvoie la valeur de la colonne
  `nom` de la nouvelle entrée).

## Exemples de trigger

Création d'un trigger qui lève une exception si un client essaie de réserver :

* Plus de 3 articles dans la catégorie A
* ou plus de 2 articles dans le catégorie B
* ou plus d'1 article de catégorie C

### Sous Oracle

~~~sql
CREATE OR REPLACE TRIGGER check_articles_categories
BEFORE INSERT ON Reservation
FOR EACH ROW
DECLARE
  categorie_mat VARCHAR2(30);
  compteur NUMBER;
  limite NUMBER;
  limite_depassee EXCEPTION;
  PRAGMA EXCEPTION_INIT(limite_depassee, -12345);
BEGIN
  -- On sélectionne la catégorie associé au produit qu'un employé
  -- essaie de réserver.
  SELECT Materiel.Categorie INTO categorie_mat FROM Materiel
  WHERE Materiel.RefMateriel = :new.RefMateriel;

  -- On compte le nombre de réservation que l'employé a fait de matériel
  -- classé dans la catégorie du matériel.
  SELECT COUNT(*) INTO compteur FROM Reservation
  LEFT JOIN Materiel ON Reservation.RefMateriel = Materiel.RefMateriel
  WHERE Materiel.Categorie = categorie_mat AND Reservation.NumEmploye = :new.NumEmploye;

  IF categorie_mat = 'A' THEN
    limite := 2;
  ELSIF categorie_mat = 'B' THEN
    limite := 1;
  ELSIF categorie_mat = 'C' THEN
    limite := 0;
  END IF;

  IF compteur > limite
  THEN
    -- Pour afficher la bonne limite à l'utilisateur
    limite := limite + 1;

    RAISE limite_depassee;
  END IF;

  EXCEPTION
  WHEN limite_depassee THEN
    RAISE_APPLICATION_ERROR (
       num => -20107,
       msg => 'Vous ne pouvez pas réserver plus de '|| limite ||' articles de catégorie '|| categorie_mat ||'.');
END check_articles_categories;
~~~

### Sous PostgreSQL

~~~sql
CREATE FUNCTION check_articles_categories()
RETURNS trigger AS
$$
DECLARE
  categorie_mat VARCHAR;
  compteur INTEGER;
  limite INTEGER;
BEGIN
  -- On sélectionne la catégorie associé au produit qu'un employé
  -- essaie de réserver.
  SELECT Materiel.Categorie INTO categorie_mat FROM Materiel
  WHERE Materiel.RefMateriel = New.RefMateriel;

  -- On compte le nombre de réservation que l'employé a fait de matériel
  -- classé dans la catégorie du matériel.
  SELECT COUNT(*) INTO compteur FROM Reservation
  LEFT JOIN Materiel ON Reservation.RefMateriel = Materiel.RefMateriel
  WHERE Materiel.Categorie = categorie_mat AND Reservation.NumEmploye = NEW.NumEmploye;

  IF categorie_mat = 'A' THEN
    limite := 2;
  ELSIF categorie_mat = 'B' THEN
    limite := 1;
  ELSIF categorie_mat = 'C' THEN
    limite := 0;
  END IF;

  IF compteur > limite
  THEN
    RAISE EXCEPTION 'Vous ne pouvez pas réserver plus de % articles de catégorie %',
                    limite+1,
                    categorie_mat;
  END IF;

  RETURN NEW;
END
$$ LANGUAGE plpgsql;

CREATE TRIGGER check_articles BEFORE INSERT ON Reservation
FOR EACH ROW
EXECUTE PROCEDURE check_articles_categories();
~~~
