package br.edu.ifsp.aluno.moviesmanager.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.edu.ifsp.aluno.moviesmanager.model.entity.Movie
import android.view.View
import android.view.View.OnClickListener
import br.edu.ifsp.aluno.moviesmanager.R
import br.edu.ifsp.aluno.moviesmanager.view.OnMovieClickListener

class MovieAdapter(private val movieList: MutableList<Movie>, private val onMovieClickListener: OnMovieClickListener) :
    RecyclerView.Adapter<MovieAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.tile_movie, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        movieList[position].also { movie ->
            with(holder) {
                nameTextView.text = movie.name
                producerTextView.text = movie.producer
                itemView.setOnClickListener {
                    onMovieClickListener.onMovieClick(position)
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return movieList.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nameTextView: TextView = itemView.findViewById(R.id.nameTv)
        val producerTextView: TextView = itemView.findViewById(R.id.producerTv)
    }
}

