package br.edu.ifsp.aluno.moviesmanager.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import br.edu.ifsp.aluno.moviesmanager.databinding.FragmentMovieBinding
import br.edu.ifsp.aluno.moviesmanager.model.entity.Movie
import br.edu.ifsp.aluno.moviesmanager.view.MainFragment.Companion.EXTRA_MOVIE
import br.edu.ifsp.aluno.moviesmanager.view.MainFragment.Companion.MOVIE_FRAGMENT_REQUEST_KEY

class MovieFragment : Fragment() {
    private lateinit var fmb: FragmentMovieBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fmb = FragmentMovieBinding.inflate(inflater, container, false)

        return fmb.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fmb.apply {
            saveBt.setOnClickListener {
                val movie = Movie(
                    name = nameEt.text.toString(),
                    releaseYear = releaseYearEt.text.toString().toInt(),
                    producer = producerEt.text.toString(),
                    duration = durationEt.text.toString().toInt(),
                    isWatched = isWatchedEt.text.toString(),
                    review = reviewEt.text.toString(),
                    genre = genreEt.text.toString()
                )

                val result = Bundle().apply {
                    putParcelable(EXTRA_MOVIE, movie)
                }

                setFragmentResult(MOVIE_FRAGMENT_REQUEST_KEY, result)
                findNavController().popBackStack()
            }
        }
    }
}

