package com.example.quizapp

import android.database.Cursor
import com.example.quizapp.Domain.QuestionModel

class QuestionRepository {

    fun getQuestionsForCategory(categoryName: String): MutableList<QuestionModel> {
        // Example database query based on category
        val questionList: MutableList<QuestionModel> = mutableListOf()

        // Query database here and filter questions based on the category
        // For example:
        // SELECT * FROM questions WHERE category = categoryName

        // Return mock data for demonstration purposes:
        if (categoryName == "Science") {
            questionList.add(QuestionModel(1, "What is the largest planet?", "Earth", "Mars", "Jupiter", "Saturn", "Jupiter", 5, "q_1", null))
            // Add more science questions...
        } else if (categoryName == "History") {
            questionList.add(QuestionModel(1, "Who discovered America?", "Columbus", "Magellan", "Vasco da Gama", "Cook", "Columbus", 5, "q_2", null))
            // Add more history questions...
        }
        // Continue for other categories...

        return questionList
    }
}
