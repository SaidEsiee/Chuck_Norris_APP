package com.example.chuck_norris_joke_app

import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.chuck_norris_joke_app.JokeAdapter

class JokeAdapter : RecyclerView.Adapter<JokeAdapter.JokeViewHolder>(){
    class JokeViewHolder(itemView: TextView) : RecyclerView.ViewHolder(itemView) {
        var dataList: ArrayList<MainActivity.List_of_jokes?> = TODO()

    }


    var list_jokes = listOf("The Pope once tried to bless Chuck Norris. Nobody crosses Chuck Norris.",
        "Chuck Norris uses a flamethrower to light his BBQ.",
        "Chuck Norris gives computers viruses",
        "Chuck Norris can cover a Beatles song on Youtube and won't get comments saying he ruined the song.",
        "There is only 1 person in the world who can kill Chuck Norris, ITS HIS EVIL TWIN Chack Norris",
        "Chuck Norris never asks if you are talking to him.",
        "Chuck Norris once won an arm wrestling match,with both hands tied behind his back",
        "Chuck Norris can connect parallel lines",
        "Unlike Santa Claus, Chuck Norris doesn't need to check his list twice.",
        "Chuck Norris can login without signing up, on any website.")

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JokeViewHolder {
        return ViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.list_of_jokes,
                parent,
                false

    }

    override fun onBindViewHolder(holder: JokeViewHolder, position: Int) {
        val dataModel = dataList.get(position)
        holder.listj.text = dataModel?.joke
    }

    override fun getItemCount(): Int {
        val dataList : ArrayList<MainActivity.List_of_jokes?>
        return dataList.size
    }
}



