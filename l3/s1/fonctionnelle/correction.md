---
layout: default
title: P. Fonctionnelle
section: l3/s1
---

## Correction DS programmation fonctionnelle

### Exercice 1

1. À la ligne 17, le type de la variable `Y` doit être une liste car `cons` attend en deuxième paramètre une liste à partir de laquelle sera construit la nouvelle liste.

2. En supposant que `(car L)` = 9 et que `(mystere3 X (cdr L))` = `'((1) (2) (2 3) ())`, la valeur de:

    ~~~racket
    (map (lambda (Y) (cons (car L) Y)) (mystere3 X (cdr L)))
    ~~~

    est

    ~~~racket
    '((9 1) (9 2) (9 2 3) (9))
    ~~~

3. Si on a `X = 5`, l'expression donne de la ligne 15 donne pour résultat donne une liste contenant une liste contenant 5 : `'((5))`

4. L'expression `(mystere3 4 '(1 2 3))` donnera :

    ~~~racket
    '((4 1 2 3) (1 4 2 3) (1 2 4 3) (1 2 3 4))
    ~~~

5. L'expression `(mystere2 4 '((1 2) (3 4)))` donnera :

    ~~~racket
    '((4 1 2) (1 4 2) (1 2 4) (4 3 4) (3 4 4) (3 4 4))
    ~~~

6. L'expression `(mystere '(1 2 3))` donnera la liste vide `'()`.

## Exercice 2

**1.**

~~~racket
(define construction
  (lambda (a b)
    (construction2 a b '())))

(define construction2
  (lambda (a b L)
    (if (> a b)
        L
        (cons a (construction2 (+ a 1) b L)))))
~~~

**2.**

~~~racket
(define max
  (lambda (L)
    (max2 L 0)))

(define max2
  (lambda (L m)
    (cond [(null? L) m]
          [(> (car L) m) (max2 (cdr L) (car L))]
          [#t (max2 (cdr L) m)])))
~~~

**3.**

~~~racket
(define egales?
  (lambda (L1 L2)
    (cond [(and (null? L1) (null? L2)) #t]
          [(= (car L1) (car L2)) (egales? (cdr L1) (cdr L2))]
          [#t #f])))

(define estPalindrome
  (lambda (L)
    (egales? (reverse L) L)))
~~~

**4.**

~~~racket
(define concatenation
  (lambda (L1 L2)
    (if (null? L1)
      L2
      (cons (car L1) (concatenation (cdr L1) L2)))))

(define renverse
  (lambda (L)
    (if (null? L)
      '()
      (concatenation (renverse (cdr L)) (cons (car L) '())))))
~~~

**5.**

~~~racket
(define renverseeComplete
  (lambda (L1)
    (permutation (renversee L1))

(define permutation
  (lambda (L)
    (cond [(null? L) '()]
          [(list? (car L) (renversee L))]
          [#t (cons (permutation (cdr L)) (car L))])))
~~~

**6.** C.f question 3

**7.**

~~~racket
(define fusion
  (lambda (L1 L2)
    (if (null? L1)
        (copie L2)
        (if (null? L2)
            (copie L1)
            (if (< (car L1) (car L2))
                (cons (car L1) (fusion (cdr L1) L2))
                (cons (car L2) (fusion L1 (cdr L2))))))))
~~~

**8.**

*Pas moyen de refaire le tri fusion, ça me pète le cul, du coup si quelqu'un l'a, n'hésitez pas à faire tourner. :-)*

**9.**

~~~racket
(define appartient?
  (lambda (e L)
    (if (null? L)
        #f
        (or (= (car L) e) (appartient? e (cdr L))))))

(define intersection
  (lambda (E1 E2)
    (cond [(or (null? E1) (null? E2)) '()]
          [(appartient? (car E1) E2) (cons (car E1) (intersection (cdr E1) E2))]
          [#t (intersection (cdr E1) (cdr E2))])))
~~~

**10.**

~~~racket
(define union
  (lambda (E1 E2)
    (cond [(null? E1) E2]
          [(appartient? (car E1) E2) (union (cdr E1) E2)]
          [#t (cons (car E1) (union (cdr E1) E2))])))
~~~

### Exercice 3.a

~~~racket
(map list '(1 2 3) '(4 5 6) '(7 8 9))
=> '((1 4 7) (2 5 8) (3 6 9))

(map + '(1 2 3) '(4 5 6) '(7 8 9))
=> '(12 15 18)

(apply + '(1 2 3))
=> 6

(eval (cons '+ '(1 2 3)))
=> 6

(cons '+ '(1 2 3))
=> '(+ 1 2 3)

(map list '(1 2 3))
=> '((1) (2) (3))

(map (lambda (Y) (cons 0 Y)) '((1) (2) (3)))
=> '((0 1) (0 2) (0 3))

(cons '() '())
=> '(())

(cons (cons '() (cons '() (cons '() '()))) '())
=> '((() () ()))
~~~

### Exercice 3.b

~~~racket
(f1 5)
=> #f

(f2 5)
=> #t
~~~

### Exercice 3.c

~~~racket
(define azerty
  (lambda (L V K)
    (if (null? L)
        (/ V K)
        (if (< 10 (car L))
            (azerty (cdr L) (+ (* 3 (car L)) V) (+ K 2))
            (azerty (cdr L) (+ (* 2 (car L)) V) (+ K 1))))))
~~~

L'expression `azerty('(1 2 8 10 12 20)` vaut `46 / 3`.
