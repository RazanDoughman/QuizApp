package com.example.quizapp

data class QuizCreate(
    val title: String,
    val description: String,
    val category: String,
    val questions: List<QuizQuestionCreate>
)

data class QuizQuestionCreate(
    val question_text: String,
    val option_a: String,
    val option_b: String,
    val option_c: String,
    val option_d: String,
    val correct_option: String
)
