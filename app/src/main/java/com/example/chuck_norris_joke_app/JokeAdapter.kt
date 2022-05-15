package com.example.chuck_norris_joke_app

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.chuck_norris_joke_app.JokeAdapter

class JokeAdapter : RecyclerView.Adapter<JokeAdapter.JokeViewHolder>(){

    class JokeViewHolder(val constraintLayout: ConstraintLayout) : RecyclerView.ViewHolder(constraintLayout){
        val textView : TextView = constraintLayout.findViewById<TextView>(R.id.jokes)
    }

    /*var list_jokes = listOf("The Pope once tried to bless Chuck Norris. Nobody crosses Chuck Norris.",
        "Chuck Norris uses a flamethrower to light his BBQ.",
        "Chuck Norris gives computers viruses.",
        "Chuck Norris can cover a Beatles song on Youtube and won't get comments saying he ruined the song.",
        "There is only 1 person in the world who can kill Chuck Norris, ITS HIS EVIL TWIN Chack Norris.",
        "Chuck Norris never asks if you are talking to him.",
        "Chuck Norris once won an arm wrestling match,with both hands tied behind his back.",
        "Chuck Norris can connect parallel lines.",
        "Unlike Santa Claus, Chuck Norris doesn't need to check his list twice.",
        "Chuck Norris can login without signing up, on any website.")*/

    /*var map_jokes = list_jokes.map {Joke(value=it)}*/

    var listAdapterJokes = listOf<Joke>()

    fun addAJoke(joke : Joke) {
        listAdapterJokes = listAdapterJokes + joke
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JokeViewHolder {
        val view: ConstraintLayout = LayoutInflater.from(parent.context).inflate(R.layout.joke_layout,parent, false) as ConstraintLayout
        /*val textView = TextView(parent.context)*/
        return JokeViewHolder(view)
    }

    override fun onBindViewHolder(holder: JokeViewHolder, position: Int) {
        val joke = listAdapterJokes[position]
        holder.textView.text = joke.value
    }

    override fun getItemCount(): Int {
        return listAdapterJokes.size
    }
}







