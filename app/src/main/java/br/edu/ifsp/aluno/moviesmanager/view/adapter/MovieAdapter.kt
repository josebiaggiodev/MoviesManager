package br.edu.ifsp.aluno.moviesmanager.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.edu.ifsp.aluno.moviesmanager.databinding.TileMovieBinding
import br.edu.ifsp.aluno.moviesmanager.model.entity.Movie

class MovieAdapter(private val movieList: MutableList<Movie>): RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {
    inner class MovieViewHolder(tileMovieBinding: TileMovieBinding): RecyclerView.ViewHolder(tileMovieBinding.root) {
        val nameTv: TextView = tileMovieBinding.nameTv
        val producerTv: TextView = tileMovieBinding.producerTv
    }

    override fun getItemCount() = movieList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        TileMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false).run {
            MovieViewHolder(this)
        }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        movieList[position].also { movie ->
            with(holder) {
                nameTv.text = movie.name
                producerTv.text = movie.producer
            }
        }
    }
}