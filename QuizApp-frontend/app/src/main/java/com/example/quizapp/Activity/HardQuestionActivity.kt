package com.example.quizapp.Activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.quizapp.Adapter.QuestionAdapterHard
import com.example.quizapp.Domain.QuestionModel
import com.example.quizapp.databinding.ActivityHardQuestionBinding

class HardQuestionActivity : AppCompatActivity(), QuestionAdapterHard.score {

    private lateinit var binding: ActivityHardQuestionBinding
    private lateinit var question: QuestionModel
    private var score = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHardQuestionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Load the single hard question
        question = hardQuestionList().first()

        // Display the question
        displayQuestion()

        // Set up RecyclerView for options
        binding.questionList.layoutManager = LinearLayoutManager(this)
        binding.backBtn.setOnClickListener { finish() }
    }

    private fun displayQuestion() {
        // Update question text
        binding.questionTxt.text = question.question

        // Set question image (optional)
        val imageResId = resources.getIdentifier(question.picPath, "drawable", packageName)
        binding.pic.setImageResource(imageResId)

        // Set up QuestionAdapter for options
        val options = listOf(question.answer_1, question.answer_2, question.answer_3, question.answer_4)
        val adapter = QuestionAdapterHard(question.clickedAnswer ?: "", options, this)
        binding.questionList.adapter = adapter
    }

    override fun amount(number: Int, clickedAnswer: String) {
        val selectedAnswer = question

        // Check if the answer is correct
        val isCorrect = clickedAnswer.trim() == (selectedAnswer.correctAnswer?.trim() ?: "")

        if (isCorrect) {
            // If the answer is correct, go to CelebrationActivity
            transitionToCelebrationActivity()
        } else {
            // If the answer is incorrect, go to TryAgainActivity
            transitionToTryAgainActivity()
        }
    }

    private fun transitionToCelebrationActivity() {
        // Create an Intent to transition to the CelebrationActivity
        val intent = Intent(this, CelebrationActivity::class.java)
        intent.putExtra("SCORE", score)  // You can pass the score if needed
        startActivity(intent)
        finish()
    }

    private fun transitionToTryAgainActivity() {
        // Create an Intent to transition to the TryAgainActivity
        val intent = Intent(this, TryAgainActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun hardQuestionList(): MutableList<QuestionModel> {
        val question: MutableList<QuestionModel> = mutableListOf()

        // Add a very hard question
        question.add(
            QuestionModel(
                1,
                "Which platform introduced the concept of \"Stories,\" allowing posts to disappear after 24 hours?",
                "Facebook",
                "Snapchat",
                "Instagram",
                "Twitter",
                "b",
                5,
                "trickquestion",
                null
            )
        )

        return question
    }
}
