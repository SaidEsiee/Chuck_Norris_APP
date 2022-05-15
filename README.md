Ce README est mon rapport du projet dans lequel j'ai expliqué les différentes étapes que j'ai effectué.

--------------------------------
--------------------------------
Part 1 - Create a UI List component
--------------------------------
--------------------------------

--------------------------
1. Create a static list of jokes
--------------------------

Dans cette première partie, je crée simplement un objet en tant que fichier dans lequel je crée une liste
qui contient 10 blagues et que j'affiche ensuite dans le logcat.

--------------------------
2. RecyclerView instantiation
--------------------------

Dans cette partie, je crée la RecyclerView qui va nous permettre de voir ce qui sera affiché sur l'écran de l'application.

--------------------------
3. Custom Adapter
--------------------------

Dans cette partie, je vais créer la classe JokeAdapter qui va nous permettre de mettre des données dans notre recyclerview
pour pouvoir ensuite les afficher dans l'application. Pour cela, j'ai override 3 méthodes dans JokeAdapter qui sont 
onCreateViewHolder(), onBindViewHolder() et getItemCount().
Il faut ensuite donner les blagues à notre adapter pour qu'il puisse les afficher.

--------------------------
4. Custom view for items
--------------------------

Dans cette partie, je crée un nouveau Layout qui se nomme joke_layout.xml qui va me servir à améliorer l'affichage des
blagues pour que l'application soit un peu plus ergonomique.


--------------------------------
--------------------------------
Part 2 - Fetch jokes
--------------------------------
--------------------------------

--------------------------
1. Create the data model matching the API
--------------------------

Tout d'abord nous réalisons quelques imports. Ensuite, je crée une data class joke que je tag en tant que Serializable.
Suite à cela, je modifie certaines values pour qu'elles correspondent à la convention "camel case".
Le fichier JokeSerializationTest.kt était à disposition pour pouvoir tester notre classe et voir si tout était correct,
ce qui était le cas pour moi ( après quelques corrections suite à des erreurs).
Et pour finir nous avons modifier, toutes les String par des Joke.

--------------------------
2. Import Retrofit & Rx Java
--------------------------

Dans cette partie, j'ai seulement effectuer les imports des dernières version de Retrofit et Rx Java et également 
donner à l'application le droit d'accéder à internet via le fichier Manifest.

--------------------------
3. Retrofit usage
--------------------------

J'ai donc crée une interface JokeApiService, dans laquelle j'ai déclaré une fonction giveMeAJoke() qui retourne
une Single<Joke>. Suite à cela, j'ai ajouté l'annotation @GET pour indiquer le lien du site Api chuck norris duquel 
nous pourrons ensuite directement récupérer des blagues.

J'ai ensuite crée une nouvelle classe en tant qu'objet que j'ai nommé jokeApiServiceFactory, ensuite j'ai donc crée 
une fonction retrofit.builder qui me retourne la JokeApiService. 

---------------------------
4. Call Api to get one Joke
---------------------------

Dans cette partie, le but va être d'afficher une blague aléatoire récupéré du site chucknorris.io
directement sur notre application.

J'ai donc crée une value jokeservice sur laquelle j'ai appelé ma fonction builderApi dans le fichier kotlin "jokeApiService" 
pour obtenir la Single<Joke>.
Suite à cela, pour obtenir le résultat de cet appel, j'ai crée la fonction getJoke() qui m'as permis d'appeler
la fonction subcribeBy() sur la jokeservice que j'ai appelé précédemment. Cette nouvelle fonction va me permettre 
d'effectuer différentes actions à chaque appel de blague.
La fonction getJoke() est donc composé d'une partie onError() dans le cas où l'appel de la blague a échoué, si c'est le cas 
nous affichons simplement un message d'erreur.
Mais elle est également composé d'une partie onSuccess(), dans le cas où l'appel de blague a réussi, et si c'est le cas nous affichons 
la blague en question.
Ensuite, j'ai forcé l'appel de notre blague sur un thread différent grâce à Schedulers.io().

---------------------------
5. Leaks killer
---------------------------

Maintenant, il est nécessaire de se désabonner aux blagues auxquels on s'est abonnés et qui ne nous sont plus 
d'aucune utilité et surtout pour éviter les fuites.

Je vais donc me servir de "CompositeDisposable" qui est une sorte de poubelle dans laquelle nous pourrons "jeter"
les blagues qui ne nous sont plus utiles après les avoir consultés. On ajoute donc logiquement les blagues à la fin de la fonction
getJoke().

Il ne faut pas cependant oublier de vider cette poubelle "CompositeDisposable" pour cela, on override la fonction
onDestroy() qui lui est assigné pour ensuite clear "CompositeDisposable".

A ce moment là de l'application, nous sommes donc capable d'afficher aléatoirement une blague du site chucknorris.
Pour ma part, j'avais une erreur dans mon code que j'ai corrigé dans la partie suivante pour afficher cette blague aléatoire.

--------------------------------
--------------------------------
Part 3 - Display jokes on screen
--------------------------------
--------------------------------


--------------------------
1. Display a single Joke
--------------------------

Dans cette partie, nous abandonnons notre liste de blagues préalablement écrites car nous allons nous servir de celles
disponibles en ligne, et nous ajountons également notre blague au contenu de notre JokeAdapter.

De la même manière que précédemment, j'ai forcé l'appel de notre blague sur un thread différent mais cette fois ci grâce à AndroidSchedulers.mainThread().

Ensuite, j'ai dû créer un bouton qui devait afficher une nouvelle blague à chaque appui, il fallait donc tout 
d'abord créer ce bouton dans le layout et lui créer son id, ensuite il fallait créer une fonction addAjoke() qui permettrait 
d'ajouter et de stocker les blagues dans une liste, et également créer une value bouton dans le main activity pour
configurer le bouton et qu'il appelle une blague à chaque click du bouton.

--------------------------
2. Add a loader
--------------------------

Dans cette partie, le but est de créer une barre de chargement pour indiquer à l'utilisateur de patienter durant
le téléchargement de la blague. Dans mon cas, cette barre de chargement est un cercle qui tourne, et pour sa création
c'est un peu similaire au bouton car en effet, il fallait créer réellement cette barre dans le layout, puis créer sa value 
associé dans le main activity. Sauf qu'ici, il fallait faire en sorte que la barre de progression ne soit affiché
à l'écran seulement lorsque la blague est affiché, il fallait donc modifier la fonction getJoke() pour afficher 
la barre lorsqu'on "s'abonne" a une blague, puis la rendre invisible lorsque la blague est affiché ou qu'il y a une erreur.
J'ai également appelé la fonction delay() à la fonction getJoke() pour qu'on puisse voir la barre de progression pour les test,
car en effet l'appel pour une seule blague peut être trop rapide pour pouvoir observer la barre de chargement.

--------------------------
3. Make the call for multiple joke with Observable
--------------------------

Dans cette partie, j'ai simplement utilisé la fonction repeat() pour afficher 10 blagues au lieu d'une, et modifier 
la fonction getJoke(), en séparant la fonction onSuccess() en onNext() et onComplete() car en effet, le succès est 
maintenant défini par l'appel de 10 blagues au lieu d'une.

--------------------------
4. Reload new jokes
--------------------------

Maintenant, je supprime le bouton car on cherche à afficher les blagues suivantes en défilant l'application.
Je me sers donc du callback onBottomReached qui va me permettre d'appeler d'autres blagues lorsque le bas de l'écran sera atteint.
Je dois donc appeler onBottomReached() lorsque le bas de l'écran est atteint, je dois donc trouver un moyen de savoir
lorsque le bas de l'écran est atteint par l'utilisateur. Cette tâche m'as donné des difficultés car en effet j'ai dû y aller 
petit à petit pour le réussir.
J'ai pensé me servir du paramètre position dans la fonction onBindViewHolder(holder,position) de JokeAdapter.
Lorsque pour le premier test, j'ai utilisé comme condition que position devait être strictement supérieur à 8
( car il y a 10 blagues affiché à l'écran), l'appli rechargerait sans cesse de nouvelles blagues quand que j'ai 
eu besoin de scroll, j'ai donc sû qu'il fallait que j'ajoute une condition. Après de nombreux essais, 
j'ai ajouter que la position devait être égale à la taille de la liste ( en gros cela signifie que la position se situe 
au niveau de la dernière blague) et suite à cela, je pouvais ajouter des blagues en défilant sur l'application par vague de 10 blagues.

J'ai donc actuellement une application qui affiche les blagues de chuck norris. Malheureusement, je n'ai pas pu réaliser 
la partie 4 qui avait pour but d'améliorer l'interface et l'affichage des blagues sur l'application.


