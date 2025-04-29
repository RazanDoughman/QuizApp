package com.example.quizapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.quizapp.Activity.MainActivity
import com.example.quizapp.Adapter.QuizSummaryAdapter
import com.example.quizapp.databinding.FragmentQuizSummaryBinding

class QuizSummaryFragment : Fragment() {

    private lateinit var binding: FragmentQuizSummaryBinding
    private val quizData: ArrayList<Question> by lazy {
        arguments?.getParcelableArrayList<Question>("QUIZ_DATA") ?: arrayListOf()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentQuizSummaryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val quizName = arguments?.getString("QUIZ_NAME") ?: "Unknown Quiz"
        val quizDescription = arguments?.getString("QUIZ_DESCRIPTION") ?: "No description"

        // Add logs to check received data
        Log.d("QuizSummaryFragment", "Quiz Name: $quizName")
        Log.d("QuizSummaryFragment", "Quiz Description: $quizDescription")
        Log.d("QuizSummaryFragment", "Quiz Data: $quizData")

        // Display quiz name and description
        binding.quizName.text = quizName
        binding.quizDescription.text = quizDescription

        // Set up RecyclerView
        binding.quizDetailsRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.quizDetailsRecyclerView.adapter = QuizSummaryAdapter(quizData)
        Log.d("QuizSummaryFragment", "Adapter initialized with data: $quizData")

        // Handle button click to navigate to com.example.quizapp.FavoritesFragment
        binding.navigateToFavoritesButton.setOnClickListener {

            val quizName = arguments?.getString("QUIZ_NAME") ?: "Default Name"
            val quizDescription = arguments?.getString("QUIZ_DESCRIPTION") ?: "Default Description"

            val quizDescriptionList = listOf(quizDescription)

            // Get ViewModel and add quiz
            val viewModel = ViewModelProvider(requireActivity()).get(QuizSummaryViewModel::class.java)
            val updatedQuizzes = viewModel.quizzes.value ?: mutableListOf()
            updatedQuizzes.add(Question(quizName, quizDescriptionList, "50 points"))
            viewModel.quizzes.value = updatedQuizzes

            val intent = Intent(requireActivity(), MainActivity::class.java)
            intent.putExtra("NAVIGATE_TO_FAVORITES", true)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
            startActivity(intent)
        }
    }
}
