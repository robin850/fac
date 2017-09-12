---
layout: default
title: P. Système
section: l3/s2
---

## Fonction dans `unistd.h`

<table>
  <tr>
    <th>Nom</th>
    <th>Description</th>
  </tr>

  <tr>
    <td><code>open</code></td>
    <td>
      <p>
        Permet d'ouvrir un fichier en renvoyant un descripteur
        de fichier (<code>int</code>).</p>

      <p>Exemple :</p>
{% highlight c %}
// Pour simplement ouvrir un fichier
descripteur = open("fichier", O_RDONLY);

// Pour créer un fichier s'il n'existe pas déjà
descripteur = open("fichier", O_CREAT|O_EXCL, 0755);
{% endhighlight %}
    </td>
  </tr>

  <tr>
    <td><code>read</code></td>
    <td>
      <p>Permet de lire le contenu d'un fichier en le plaçant dans un tampon et en spécifiant la taille maximale de ce tampon</p>

      <p>Exemple :</p>

{% highlight c %}
char tampon[10];

// Le troisième paramètre spécifie la taille
// maximale du tampon.
// n contiendra le nombre de caractères lus.
int n = read(descripteur, tampon, 10);
{% endhighlight %}
    </td>
  </tr>

  <tr>
    <td>&nbsp; <code>write</code></td>
    <td>
      <p>
        Permet d'écrire une chaîne de caractère dans un fichier en spécifiant
        sa taille.
      </p>

      <p>Exemple :</p>

{% highlight c %}
write(descripteur, "unechaine", 9);
{% endhighlight %}
    </td>
  </tr>
</table>

### Les masques utilisés en cours pour `open`

| Masque     |   Utilité                                |
|------------|------------------------------------------|
| `O_RDONLY` |  Ouvrir le fichier en lecture seulement. |
| `O_WRONLY` |  Ouvrir le fichier en écriture seulement.|
| `O_CREAT`  |  Créer le fichier.                       |
| `O_EXCL`   |  Combiné avec `O_CREAT`, crée le fichier uniquement s'il n'existe pas. |

