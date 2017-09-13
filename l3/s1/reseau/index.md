---
layout: default
title: Réseau
section: l3/s1
---

## Documents

* [Cours de Réseau CM](cours.html)
* [Algorithme de Kruskal](algo.html)
* [Sujet de réseau 2015-2016](https://drive.google.com/uc?export=download&id=0B1b6pH21vC4eTlRhY0FBQXh3RkE)
* [Mettre en place un serveur DNS sous Windows Server 2008](dns.html)

## Ressources utiles

* [Cours sur les masques de sous-réseau](http://www.lalitte.com/index.php/Les_masques_de_sous-r%C3%A9seau) : pratique pour comprendre pas mal de choses.

## Pour Sid: choisir la connection filaire sous Ubuntu en passant par un router

* Cliquer sur l'icône réseau dans la barre
* Edit connections
* Wired connection 1
  - Edit
  - IPv4 settings
  - Manual
  - `192.168.20.40`
* Vérifier en tapant `ifconfig` dans un terminal.

## Shell / Powershell

### Pour exécuter un script Shell sous Linux:

~~~bash
$ bash script.sh
~~~

### Pour exécuter un script PowerShell sous Windows:

L'exècution est sûrement désactivé, il faut donc aller dans le menu Démarrer -> Clique droit sur Powershell -> Exécuter en tant qu'administrateur et taper:

~~~
Set-ExecutionPolicy RemoteSigned
~~~

Ensuite pour exécuter le script, toujours dans la fenêtre Powershell, taper:

~~~
.\fichier.ps1
~~~

### Pitit mémo

<div id="memo">

  <ul class="nav-tabs">
    <li class="active"><a href="#bash">Bash</a></li>
    <li><a href="#powershell">Powershell</a></li>
  </ul>

  <div class="tab-content">
    <div class="tab-pane active" id="bash">
      <table>
        <tr>
          <td>Écrire</td>
          <td>{% highlight bash %}
echo "Message"
{% endhighlight %}</td>
        </tr>
        <tr>
          <td>Lecture</td>
          <td>{% highlight bash %}
read variable
{% endhighlight %}</td>
        </tr>
        <tr>
          <td>Incrémenter une variable</td>
          <td>{% highlight bash %}
nb=0
nb=$((nb+1))
{% endhighlight %}</td>
        </tr>
        <tr>
          <td>Lire une ligne spécifique d'un<br> fichier (e.g. la 10<sup>ème</sup>)</td>
          <td>{% highlight bash %}
head -10 fichier.txt | tail -1
{% endhighlight %}</td>
        </tr>
        <tr>
          <td>Retourner une chaîne</td>
          <td>{% highlight bash %}
# Exemple: `whoami | rev` => "ykcaj"
# si whoami == "jacky"
rev
{% endhighlight %}</td>
        </tr>
        <tr>
          <td>Récupérer un lot de caractères</td>
          <td>{% highlight bash %}
# Uniquement le 3ème caractère
echo "mouloud" | cut -c3
# => u

# Du 3ème caractère au dernier
echo "mouloud" | cut -c3-
# => uloud
{% endhighlight %}</td>
        </tr>
        <tr>
          <td>Découper une chaîne</td>
          <td>{% highlight bash%}
read chaine

for i in $(echo $chaine | tr " " "\n")
do
  # $i vaut la valeur d'un mot à chaque
  # tour de boucle
  echo $i;
done;
{% endhighlight %}</td>
        </tr>
      </table>
    </div>

    <div class="tab-pane" id="powershell">
      <table>
        <tr>
          <td>Écrire</td>
          <td>{% highlight powershell %}
Write-Host "Message"
{% endhighlight %}</td>
        </tr>

        <tr>
          <td>Lecture</td>
          <td>{% highlight powershell %}
$variable = Read-Host "Message"
{% endhighlight %}</td>
        </tr>

        <tr>
          <td>Lecture de mot de passe</td>
          <td>{% highlight powershell %}
$pass = Read-Host -assecurestring "Mot de passe"
{% endhighlight %}</td>
        </tr>

        <tr>
          <td>Dossiers partagés</td>
          <td>{% highlight powershell %}
get-WmiObject -class Win32_Share
{% endhighlight %}</td>
        </tr>
        <tr>
          <td>Récupérer la date</td>
          <td>{% highlight powershell %}
get-date
{% endhighlight %}</td>
        </tr>
      </table>
    </div>
  </div>
</div>
