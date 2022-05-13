package com.example.chuck_norris_joke_app

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory

object JokeApiServiceFactory {
    fun BuilderApi(): JokeApiService = Retrofit.Builder()
        .baseUrl("https://api.chucknorris.io/")
        .addConverterFactory(Json.asConverterFactory(MediaType.get("application/json")))
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
        .create(JokeApiService::class.java)
}

