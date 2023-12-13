package br.edu.ifsp.aluno.moviesmanager.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import br.edu.ifsp.aluno.moviesmanager.R
import br.edu.ifsp.aluno.moviesmanager.databinding.FragmentMainBinding

class MainFragment: Fragment() {
    private lateinit var fmb: FragmentMainBinding

    // Navigation controller
    private val navController: NavController by lazy {
        findNavController()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        (activity as? AppCompatActivity)?.supportActionBar?.subtitle = getString(R.string.movie_list)

        fmb = FragmentMainBinding.inflate(inflater, container, false).apply {
            moviesRv.layoutManager = LinearLayoutManager(context)

            addMovieFab.setOnClickListener {
                try {
                    findNavController().navigate(
                        MainFragmentDirections.actionMainFragmentToMovieFragment()
                    )
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }

        return fmb.root
    }
}