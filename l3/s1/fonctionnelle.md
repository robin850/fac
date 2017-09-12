---
layout: default
title: P. Fonctionnelle
section: l3/s1
---

## Documents

* [Correction sujet 2015-2016](fonctionnelle/correction.html) : le sujet est dispo sur le groupe.

## Trucs à savoir

> **Toutes** les parenthèses sont remplaçables par des crochets mais
> apparemment vous savez qui n'est pas au courant donc vaut peut être
> mieux pas l'utiliser pour le DS mais ça rend les exemples en dessous
> plus clairs.

### Fonctionnement de `map`

~~~racket
(map fonction liste)

; Exemple:
(map car '((1 2 3)
           (4 5 6)
           (7 8 9)))
; => '(1 4 7)
~~~

### Fonctionnement de `curry`

`curry` permet de "commencer" un appel de fonction et de l'exécuter
qu'une fois que le nombre nécessaire d'arguments est présents. Ça fonctionne
un petit peu comme un accumulateur.

~~~racket
(curry fonction argument)

; Exemple
(define variable (curry + 10)) ; "devient" une fonction appelable

(variable 5)
; => 15

(variable 12)
; => 22
~~~

### Fonctionnement de `let` et `letrec`

`let` permet d'associer une valeur à un symbole:

~~~racket
; Exemple
(let ([x 10] [y 20]) (+ y x))
; => 30
~~~

Le `let` ne permet de référencer un symbole définit par lui même dans la
définition d'un autre mais `letrec` peut:

~~~racket
; Exemple
(letrec ([x 10] [y (+ x 1)]) (+ y x))
; => 21
~~~

### Fonctionnement de `cond`

`cond` marche un peu comme un `switch` en C à la différence que `cond` permet
de vérifier n'importe quelle type de condition, pas uniquement sur la valeur
d'une variable:

<div id="memo">

  <ul class="nav-tabs">
    <li class="active"><a href="#racket">Racket</a></li>
    <li><a href="#c">C</a></li>
  </ul>

  <div class="tab-content">
    <div class="tab-pane active" id="racket">
      {% highlight racket %}
; #t est le cas par défaut
(cond [(= 0 symbole) (instruction1)]
      [(= 1 symbole) (instruction2)]
      [#t (instruction3)])
{% endhighlight %}
    </div>

    <div class="tab-pane" id="c">
{% highlight C %}
switch(symbole) {
  case 0:
    instruction1;
    break;
  case 1:
    instruction2;
    break;
  default:
    instruction3
    break;
}
{% endhighlight %}
    </div>
  </div>
</div>

## TD

Les fonctions `gauche`, `droite`, `valeur` et `concat` sont définies dans le cours
par:

~~~racket
(define gauche
  (lambda (A) (cadr A)))

(define droite
  (lambda (A) (caddr A)))

(define valeur
  (lambda (A) (car A)))

(define concat
  (lambda (L1 L2)
    (if (null? L1)
      L2
      (cons (car L1) (concat (cdr L1) L2)))))
~~~


### Exercice 1

Pour un arbre A donné, définir une fonction qui vérifie si A est ordonné
ou non.

~~~racket
(define ordonne?
  (lambda (A)
    (if (null? A)
      #t
      (and (inferieurs (lesValeurs (gauche A)) (valeur A))
           (superieurs (lesValeurs (droite A)) (valeur A))
           (ordonne? (gauche A))
           (ordonne? (droite A))))))
~~~

~~~racket
(define inferieurs
  (lambda (L v)
    (if (null? L)
      #t
      (and (<= (valeur L) v)
           (inferieurs (cdr L) v)))))
~~~

~~~racket
(define superieurs
  (lambda (L v)
    (if (null? L)
      #t
      (and (> (valeur L) v)
           (superieurs (cdr L) v)))))
~~~

~~~racket
(define lesValeurs
  (lambda (A)
    (if (null? A)
      '()
      (concat (lesValeurs (gauche A))
              (cons (valeur A) (lesValeurs (droite A)))))))
~~~

### Exercice 2

Définir une fonction qui pour une liste L et un opérator `op donné, vérifie
si une liste est bien ordonnée:

~~~racket
(define listeOrdonnee?
  (lambda (L op)
    (if (or (null? L) (null? (cdr L)))
      #t
      (and (op (car L) (cadr L))
           (listeOrdonnee? (cdr L) op)))))

; Et après on appelle la fonction comme ça pour vérifier si elle
; est triée dans l'ordre croissant:
;
;   (listeOrdonnee? '(1 2 3) <=)
;
; Ou comme ça pour l'ordre décroissant:
;
;   (listeOrdonnee? '(3 2 1) >)
~~~
