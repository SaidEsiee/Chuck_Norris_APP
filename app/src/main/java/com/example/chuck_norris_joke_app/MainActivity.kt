package com.example.chuck_norris_joke_app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import io.reactivex.Single
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import kotlinx.serialization.*
import io.reactivex.disposables.CompositeDisposable


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val recyclerview = findViewById<RecyclerView>(R.id.rvContacts)

        recyclerview.adapter = JokeAdapter()

        getJoke()
    }

    override fun onDestroy() {
        super.onDestroy()
        CompositeDisposable.clear()
    }

    object List_of_jokes {
        val jokes_list = listOf<String>("The Pope once tried to bless Chuck Norris. Nobody crosses Chuck Norris.",
            "Chuck Norris uses a flamethrower to light his BBQ.",
            "Chuck Norris gives computers viruses",
            "Chuck Norris can cover a Beatles song on Youtube and won't get comments saying he ruined the song.",
            "There is only 1 person in the world who can kill Chuck Norris, ITS HIS EVIL TWIN Chack Norris",
            "Chuck Norris never asks if you are talking to him.",
            "Chuck Norris once won an arm wrestling match,with both hands tied behind his back",
            "Chuck Norris can connect parallel lines",
            "Unlike Santa Claus, Chuck Norris doesn't need to check his list twice.",
            "Chuck Norris can login without signing up, on any website.")
    }

    val jokeService : JokeApiService = JokeApiServiceFactory.builderApi()

    val CompositeDisposable = CompositeDisposable()

    fun getJoke() {
        jokeService.giveMeAJoke().subscribeOn(Schedulers.io()).subscribeBy(
            onError = { Log.d("error","an error has appeared") },
            onSuccess = {Log.d("success","${it.value}")
                }
        ).also{CompositeDisposable.add(it)}
    }

}

