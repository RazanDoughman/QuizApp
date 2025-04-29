package com.example.quizapp.Activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.quizapp.Adapter.QuestionAdapter
import com.example.quizapp.Domain.QuestionModel
import com.example.quizapp.QuestionRepository
import com.example.quizapp.databinding.ActivityQuestionBinding

class QuestionActivity : AppCompatActivity(), QuestionAdapter.score {

    private lateinit var binding: ActivityQuestionBinding
    private lateinit var questionList: MutableList<QuestionModel>
    private var currentQuestionIndex = 0
    private var score = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQuestionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val categoryName = intent.getStringExtra("CATEGORY_NAME")  // Get the category name from the Intent

        questionList = if (categoryName == null) {
            questionList()
        }else {
            getCategoryQuestions(categoryName)
        }
        // Display the first question
        displayQuestion()

        // Set up RecyclerView for options
        binding.questionList.layoutManager = LinearLayoutManager(this)
        binding.backBtn.setOnClickListener { finish() }
    }

    private fun displayQuestion() {
        if (currentQuestionIndex < questionList.size) {
            val currentQuestion = questionList[currentQuestionIndex]

            // Update question text and progress
            binding.questionNumberTxt.text = "Question ${currentQuestionIndex + 1}/${questionList.size}"
            binding.questionTxt.text = currentQuestion.question
            binding.progressBar.progress = currentQuestionIndex + 1

            // Set question image
            val imageResId = resources.getIdentifier(currentQuestion.picPath, "drawable", packageName)
            binding.pic.setImageResource(imageResId)

            // Set up QuestionAdapter for options
            val options = listOf(currentQuestion.answer_1, currentQuestion.answer_2, currentQuestion.answer_3, currentQuestion.answer_4)
            val adapter = QuestionAdapter(currentQuestion.clickedAnswer ?: "", options, this)
            binding.questionList.adapter = adapter


        }
    }

    override fun amount(number: Int, clickedAnswer: String) {
        val selectedAnswer = questionList[currentQuestionIndex]

        // Update score based on answer correctness
        val match = clickedAnswer.trim() == (selectedAnswer.correctAnswer?.trim() ?: "")

        if(match) {
            score++
            println("Correct! Score Updated: $score")
        }
        else {
            println("Incorrect! Score remains: $score")
        }
        // Move to the next question
        currentQuestionIndex++
        if (currentQuestionIndex < questionList.size) {
            displayQuestion()
        } else {
            transitionToScoreActivity()
        }
    }
    private fun transitionToScoreActivity() {
        // Create an Intent to transition to the ScoreActivity
        val intent = Intent(this, ScoreActivity::class.java)
        intent.putExtra("SCORE", score)  // Pass score to the next activity
        startActivity(intent)
        finish()
    }
    private fun questionList(): MutableList<QuestionModel> {
        val question: MutableList<QuestionModel> = mutableListOf()
        question.add(
            QuestionModel(
                1,
                "Which planet is the largest planet in the solar system?",
                "Earth",
                "Mars",
                "Neptune",
                "Jupiter",
                "d",
                5,
                "q_1",
                null
            )
        )

        question.add(
            QuestionModel(
                2,
                "Which city is home to the Brandenburg Gate?",
                "Vienna",
                "Zurich",
                "Berlin",
                "Beirut",
                "c",
                5,
                "q_2",
                null
            )
        )

        question.add(
            QuestionModel(
                3,
                "Which of the following languages has the longest alphabet?",
                "Greek",
                "Russian",
                "Arabic",
                "French",
                "b",
                5,
                "q_3",
                null
            )
        )

        question.add(
            QuestionModel(
                4,
                "The fear of insects is known as what?",
                "Entomophobia",
                "Arachnophobia",
                "Ailurophobia",
                "Thantophobia",
                "a",
                5,
                "q_4",
                null
            )
        )

        question.add(
            QuestionModel(
                5,
                "Which horoscope sign is a fish?",
                "Aquarius",
                "Cancer",
                "Gemini",
                "Pisces",
                "d",
                5,
                "q_5",
                null
            )
        )

        question.add(
            QuestionModel(
                6,
                "Which app has the most total users?",
                "TikTok",
                "Snapchat",
                "Instagram",
                "Facebook",
                "c",
                5,
                "q_6",
                null
            )
        )

        question.add(
            QuestionModel(
                7,
                "What city hosted the 2002 Olympic Games?",
                "Tokyo",
                "Beijing",
                "Damascus",
                "Sydney",
                "d",
                5,
                "q_7",
                null
            )
        )

        question.add(
            QuestionModel(
                8,
                "What is the strongest muscle in the human body?",
                "Jaw",
                "Heart",
                "Forearms",
                "Legs",
                "a",
                5,
                "q_8",
                null
            )
        )

        question.add(
            QuestionModel(
                9,
                "Where was tea invented?",
                "England",
                "USA",
                "China",
                "Lebanon",
                "c",
                5,
                "q_9",
                null
            )
        )

        question.add(
            QuestionModel(
                10,
                "What is the meaning of 'Hakuna Matata'?",
                "No worries",
                "Goodnight",
                "Thank you",
                "Take care",
                "a",
                5,
                "q_10",
                null
            )
        )

        return question
    }
    // New method to fetch questions dynamically based on the category
    private fun getCategoryQuestions(categoryName: String): MutableList<QuestionModel> {
        val questionRepository = QuestionRepository()  // Assuming you have a repository to fetch data
        return questionRepository.getQuestionsForCategory(categoryName)  // Fetch questions from DB based on category name
    }
}