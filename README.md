README


3. Retrofit usage

J'ai donc crée une interface JokeApiService, dans laquelle j'ai déclaré une fonction giveMeAJoke() qui retourne
une Single<Joke>. Suite à cela, j'ai ajouté l'annotation @GET pour indiquer le lien du site Api chuck norris.

J'ai ensuite crée une nouvelle classe en tant qu'objet que j'ai nommé jokeApiServiceFactory, ensuite j'ai donc crée une 
fonction retrofit.builder qui me retourne la JokeApiService.

