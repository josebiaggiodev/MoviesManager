package br.edu.ifsp.aluno.moviesmanager.view

import android.app.Activity
import android.os.Build
import android.os.Bundle
import android.view.ContextMenu
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.view.inputmethod.InputMethodManager.HIDE_NOT_ALWAYS
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import br.edu.ifsp.aluno.moviesmanager.R
import br.edu.ifsp.aluno.moviesmanager.databinding.FragmentMainBinding
import br.edu.ifsp.aluno.moviesmanager.model.entity.Movie
import br.edu.ifsp.aluno.moviesmanager.view.adapter.MovieAdapter

class MainFragment: Fragment(), OnMovieClickListener {
    private lateinit var fmb: FragmentMainBinding

    // Data Source
    private val movieList: MutableList<Movie> = mutableListOf()

    // Adapter
    private val moviesAdapter: MovieAdapter by lazy {
        MovieAdapter(movieList, this)
    }

    // Navigation controller
    private val navController: NavController by lazy {
        findNavController()
    }

    // Communication constants
    companion object {
        const val EXTRA_MOVIE = "EXTRA_MOVIE"
        const val MOVIE_FRAGMENT_REQUEST_KEY = "MOVIE_FRAGMENT_REQUEST_KEY"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setFragmentResultListener(MOVIE_FRAGMENT_REQUEST_KEY) { requestKey, bundle ->
            if (requestKey == MOVIE_FRAGMENT_REQUEST_KEY) {
                val movie = bundle.getParcelable<Movie>(EXTRA_MOVIE)
                movie?.let { receivedMovie ->
                    val existingMovieIndex = movieList.indexOfFirst { it.name == receivedMovie.name }

                    if (existingMovieIndex != -1) {
                        movieList[existingMovieIndex] = receivedMovie
                        moviesAdapter.notifyItemChanged(existingMovieIndex)
                    } else {
                        movieList.add(receivedMovie)
                        moviesAdapter.notifyItemInserted(movieList.size - 1)
                    }
                }
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        (activity as? AppCompatActivity)?.supportActionBar?.subtitle = getString(R.string.movie_list)

        fmb = FragmentMainBinding.inflate(inflater, container, false).apply {
            moviesRv.layoutManager = LinearLayoutManager(context)
            moviesRv.adapter = moviesAdapter

            addMovieFab.setOnClickListener {
                navController.navigate(
                    MainFragmentDirections.actionMainFragmentToMovieFragment()
                )
            }
        }

        return fmb.root
    }

    override fun onCreateContextMenu(
        menu: ContextMenu,
        v: View,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        requireActivity().menuInflater.inflate(R.menu.context_menu_main, menu)
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        return when(item.itemId) {
            R.id.removeMovieMi -> {
                true
            }
            R.id.editMovieMi -> {
                true
            }
            else -> { false }
        }
    }

    override fun onMovieClick(position: Int) {
        navController.navigate(
            MainFragmentDirections.actionMainFragmentToMovieFragment()
        )
    }
}