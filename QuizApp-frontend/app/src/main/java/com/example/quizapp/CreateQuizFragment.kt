package com.example.quizapp

import android.content.Intent
import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.quizapp.Activity.MainActivity
import com.example.quizapp.Adapter.Question2Adapter
import com.example.quizapp.databinding.FragmentCreateQuizBinding
import com.example.quizapp.FavoritesFragment

class CreateQuizFragment : Fragment(R.layout.fragment_create_quiz) {

    private var _binding: FragmentCreateQuizBinding? = null
    private val binding get() = _binding!!

    private var currentQuestion = 1
    private var totalQuestions = 1
    private val questions = mutableListOf<Question>() // Store user inputs

    private var quizName: String? = null
    private var quizDescription: String? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCreateQuizBinding.inflate(inflater, container, false)

        totalQuestions = arguments?.getInt("NUMBER_OF_QUESTIONS") ?: 1
        quizName = arguments?.getString("QUIZ_NAME") ?: "Unknown Quiz"
        quizDescription = arguments?.getString("QUIZ_DESCRIPTION") ?: "No description"

        binding.progressBar.max = totalQuestions

        for (i in 1..totalQuestions) {
            questions.add(Question("", listOf("", "", "", ""), "")) // Initialize empty questions
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        updateQuestionView()

        binding.backArrow.setOnClickListener {
            saveCurrentQuestion()
            if (currentQuestion > 1) {
                currentQuestion--
                updateQuestionView()
            }
        }

        binding.forwardArrow.setOnClickListener {
            saveCurrentQuestion()
            if (currentQuestion < totalQuestions) {
                currentQuestion++
                updateQuestionView()
            }
        }

        binding.submitButton.setOnClickListener {
            saveCurrentQuestion()

            // Log the data being passed
            Log.d("CreateQuizFragment", "Questions being passed: $questions")

            if (questions.any { it.question.isBlank() || it.options.any { option -> option.isBlank() } || it.correctAnswer.isBlank() }) {
                Toast.makeText(requireContext(), "Please complete all fields for all questions", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Ensure the container is visible
            requireActivity().findViewById<View>(R.id.fragmentContainer).visibility = View.VISIBLE

            // Navigate to com.example.quizapp.FavoritesFragment
            val quizSummaryFragment = QuizSummaryFragment()
            val bundle = Bundle()
            bundle.putParcelableArrayList("QUIZ_DATA", ArrayList(questions)) // Pass quiz questions
            bundle.putString("QUIZ_NAME", quizName) // Use the variable passed as an argument
            bundle.putString("QUIZ_DESCRIPTION", quizDescription) // Use the variable passed as an argument
            quizSummaryFragment.arguments = bundle

            parentFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, quizSummaryFragment)
                .commit()
        }


        // Handle "Back" button click
        binding.backButton.setOnClickListener {
            // Navigate back to the MainActivity
            val intent = Intent(requireActivity(), MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
            startActivity(intent)
        }
    }

    private fun saveCurrentQuestion() {
        val question = binding.questionInput.text.toString()
        val options = listOf(
            binding.option1.text.toString(),
            binding.option2.text.toString(),
            binding.option3.text.toString(),
            binding.option4.text.toString()
        )
        val correctAnswer = binding.correctAnswer.text.toString()

        if (question.isNotBlank() && options.all { it.isNotBlank() } && correctAnswer.isNotBlank()) {
            questions[currentQuestion - 1] = Question(question, options, correctAnswer)
        } else {
            Log.d("CreateQuizFragment", "Question data missing for question: $currentQuestion")
            Toast.makeText(requireContext(), "Please fill out all fields for Question $currentQuestion", Toast.LENGTH_SHORT).show()
        }
    }


    private fun updateQuestionView() {
        val question = questions[currentQuestion - 1]

        binding.questionNumber.text = "Question No. $currentQuestion"
        binding.questionInput.setText(question.question)
        binding.option1.setText(question.options[0])
        binding.option2.setText(question.options[1])
        binding.option3.setText(question.options[2])
        binding.option4.setText(question.options[3])
        binding.correctAnswer.setText(question.correctAnswer)

        binding.progressBar.max = totalQuestions
        binding.progressBar.progress = currentQuestion

        binding.backArrow.visibility = if (currentQuestion > 1) View.VISIBLE else View.GONE
        binding.forwardArrow.visibility =
            if (currentQuestion < totalQuestions) View.VISIBLE else View.GONE
        binding.submitButton.visibility = if (currentQuestion == totalQuestions) View.VISIBLE else View.GONE

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null // Avoid memory leaks
    }
}
data class Question(
    var question: String,
    var options: List<String>,
    var correctAnswer: String
): Parcelable {


    constructor(parcel: Parcel) : this(
        parcel.readString() ?: "",
        parcel.createStringArrayList() ?: emptyList(),
        parcel.readString() ?: ""
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(question)
        parcel.writeStringList(options)
        parcel.writeString(correctAnswer)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Question> {
        override fun createFromParcel(parcel: Parcel): Question {
            return Question(parcel)
        }

        override fun newArray(size: Int): Array<Question?> {
            return arrayOfNulls(size)
        }
    }
}