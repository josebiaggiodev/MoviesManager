package br.edu.ifsp.aluno.moviesmanager.view

sealed interface OnMovieClickListener {
    fun onMovieClick(position: Int)
}