package com.example.quizapp

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class QuizSummaryViewModel : ViewModel() {
    val quizzes = MutableLiveData<MutableList<Question>>(mutableListOf())

    init {
        quizzes.observeForever {
            Log.d("QuizSummaryViewModel", "Quizzes Updated: $it")
        }
    }
}