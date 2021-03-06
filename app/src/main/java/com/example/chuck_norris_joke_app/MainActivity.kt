package com.example.chuck_norris_joke_app

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /*Log.d("List_of_jokes",List_of_jokes.toString())*/
        val recyclerview = findViewById<RecyclerView>(R.id.id_view)

        recyclerview.adapter = adapter

        getJoke()

    }

    override fun onDestroy() {
        super.onDestroy()
        CompositeDisposable.clear()
    }

    /*object List_of_jokes {
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
    }*/

    val jokeService : JokeApiService = JokeApiServiceFactory.builderApi()

    val CompositeDisposable = CompositeDisposable()

    val adapter = JokeAdapter(onBottomReached = { getJoke() })

    private fun getJoke() {
        val progressBar = findViewById<ProgressBar>(R.id.id_progress_bar)
        progressBar.visibility = View.VISIBLE
        jokeService.giveMeAJoke().repeat(10).delay(5000, TimeUnit.MILLISECONDS).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribeBy(
            onError = { Log.d("error", "an error has appeared")
                progressBar.visibility = View.INVISIBLE },
            onNext = { Log.d("success", "${it.value}")
                progressBar.visibility = View.INVISIBLE
                adapter.addAJoke(it)},
            onComplete = { Log.d("It's complete", "All jokes are displayed" )
            }
        ).also { CompositeDisposable.add(it) }
    }

}



