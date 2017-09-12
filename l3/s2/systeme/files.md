---
layout: default
section: l3/s2
title: P. Système
---

## Files de messages

On crée une nouvelle file de message en précisant une clé pour pouvoir la réutiliser
et on précise le chmod du fichier qui sera créé :

~~~c
int id_de_la_file = msgget((key_t)CLE, 0750 | IPC_CREAT);
~~~

On doit ensuite avoir une structure avec un champ `mtype` de type `long`, qui contient
le type de message. Ce champ permet de savoir quel message on doit attendre :

~~~c
struct message {
  long mtype;
  // Autres champs
};
~~~

On crée donc une instance de cette structure en précisant le type du message et on
l'**envoie** dans la file :

~~~c
struct message mon_message;

mon_message.mtype = 12345;

msgsnd(id_de_la_file, &mon_message, sizeof(message), IPC_NOWAIT);
~~~

Dans le programme où on essaie de **récupérer** le message, on appelle de nouveau
`msgget` pour récupérer la file mais sans préciser le chmod cette fois :

~~~c
int id_de_la_file;

id_de_la_file = msgget((key_t)CLE_OP, 0);
~~~

On crée ensuite une nouvelle instance de la structure de message et on utilise la
fonction `msgrcv` pour recevoir le message et on précise le même type que celui
utilisé pour envoyer le message (*i.e.* 12345) :

~~~c
struct message mon_message;

msgrcv(id_de_la_file, &mon_message, sizeof(message), (long)12345, IPC_NOWAIT);
~~~

Il faut ensuite nettoyer la file de message :

~~~c
msgctl(id_de_la_file, IPC_RMID, NULL);
~~~
