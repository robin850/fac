# Cours PRREL, L3 et M1

## Mise en place

Dépendances:

* Ruby (version 2.4 ; Jekyll requiert au minimum la version 2.0 mais le site peut
  potentiellement ne pas marcher avec cette version).
* Bundler (`gem install bundler` dans un terminal).

~~~
$ git clone https://github.com/robin850/fac
$ cd fac
$ bundle
~~~

Pour lancer le serveur en local :

~~~
$ bundle exec jekyll server
~~~

Pour générer le site sans lancer le serveur :

~~~
$ bundle exec jekyll build
~~~

## Différences avec la stack Jekyll par défaut

* Possibilité de colorer le [pseudo code](https://github.com/robin850/fac/tree/master/_plugins/pseudo_code_lexer.rb)
  *via* `pseudo` dans les *[fenced code blocks](https://help.github.com/articles/creating-and-highlighting-code-blocks/)*.
* Possibilité de [surligner les lignes](https://github.com/robin850/fac/tree/master/_plugins/custom_converter.rb#L9-26).

## Sous Windows

Par défaut, Jekyll utilise [Kramdown](https://kramdown.gettalong.org/) pour générer
le Markdown. Ici, [Redcarpet](https://github.com/vmg/redcarpet) est utilisé pour
que la génération soit plus rapide mais est une bibliothèque écrite en C ; ça risque
donc d'être méga chiant à installer sous Windows.

Pour désactiver Redcarpet et utiliser Kramdown, il faut :

* Commenter la ligne `gem 'redcarpet'` dans le fichier `Gemfile`.
* Possible qu'il faille aussi commenter la ligne `gem "listen"` mais pas sûr.
  ¯\\\_(ツ)_/¯
* Lancer la commande `bundle` dans une invite de commande.
* Éditer le fichier `_config.yml` et changer l'avant dernière ligne par
  `markdown: kramdown`.
* Supprimer le fichier `_plugins/custom_converter.rb`.

La fonctionnalité de surlignage du code ne sera juste pas disponible avec Kramdown.

## Licence

Le code Ruby est sous [licence MIT](http://opensource.org/licenses/mit-license.php).

Les fichiers Markdown et les images sont sous licence
[CC-BY-NC 4.0](https://creativecommons.org/licenses/by-nc/4.0/).
