package com.example.quizapp

import android.os.Bundle
import com.example.quizapp.QuizSummaryViewModel
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.quizapp.Adapter.QuizCardAdapter
import com.example.quizapp.Question
import com.example.quizapp.R
import com.example.quizapp.databinding.FragmentFavoritesBinding

class FavoritesFragment : Fragment() {

    private lateinit var binding: FragmentFavoritesBinding
    private val quizData: ArrayList<Question> by lazy {
        arguments?.getParcelableArrayList<Question>("QUIZ_DATA") ?: arrayListOf()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFavoritesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewModel = ViewModelProvider(requireActivity()).get(QuizSummaryViewModel::class.java)

        // Set up RecyclerView
        viewModel.quizzes.observe(viewLifecycleOwner) { quizData ->
            binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
            binding.recyclerView.adapter = QuizCardAdapter(quizData)
        }
    }
}