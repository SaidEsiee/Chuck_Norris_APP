package com.example.chuck_norris_joke_app

import io.reactivex.Single
import retrofit2.http.GET

interface JokeApiService {
    @GET("https://api.chucknorris.io/")
    fun giveMeAjoke(): Single<Joke>
}