package com.example.quizapp.Activity

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.quizapp.Adapter.CategoryAdapter
import com.example.quizapp.Category
import com.example.quizapp.CategoryRepository
import com.example.quizapp.QuizCreate
import com.example.quizapp.R
import com.example.quizapp.RetrofitClient
import com.example.quizapp.adapters.QuizAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CategoriesActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var quizAdapter: QuizAdapter
    private val quizList = mutableListOf<QuizCreate>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_categories)

        val backArrow = findViewById<ImageView>(R.id.backArrow)
        backArrow.setOnClickListener {
            onBackPressed()
        }
        // Initialize RecyclerView
        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        quizAdapter = QuizAdapter(quizList)
        recyclerView.adapter = quizAdapter

        val categoriesRecyclerView: RecyclerView = findViewById(R.id.recyclerView)
        categoriesRecyclerView.layoutManager = GridLayoutManager(this, 1)

        val sharedCategories = CategoryRepository.categories

        val extraCategories = listOf(
            Category(
                name = "Geography",
                description = "Embark on an adventure across the globe. Test your knowledge of countries, landmarks, and the diverse cultures of the world.",
                imageResId = R.drawable.cat7
            ),
            Category(
                name = "English",
                description = "Sharpen your language skills! Explore grammar, vocabulary, and literary treasures in the world of English.",
                imageResId = R.drawable.cat8
            ),
            Category(
                name = "General Knowledge",
                description = "Expand your horizons with a mix of fun and facts across a variety of topics, from trivia to current events.",
                imageResId = R.drawable.cat9
            )
        )

        val allCategories = sharedCategories + extraCategories

        val adapter = CategoryAdapter(allCategories, this)
        categoriesRecyclerView.adapter = adapter

        // Set up category button listeners
        findViewById<Button>(R.id.btnScience).setOnClickListener {
            fetchQuizzesByCategory("Science")
        }
        findViewById<Button>(R.id.btnHistory).setOnClickListener {
            fetchQuizzesByCategory("History")
        }
        findViewById<Button>(R.id.btnMath).setOnClickListener {
            fetchQuizzesByCategory("Math")
        }
        findViewById<Button>(R.id.btnProgramming).setOnClickListener {
            fetchQuizzesByCategory("Programming")
        }
        findViewById<Button>(R.id.btnSport).setOnClickListener {
            fetchQuizzesByCategory("Sport")
        }
        findViewById<Button>(R.id.btnArt).setOnClickListener {
            fetchQuizzesByCategory("Art")
        }
        findViewById<Button>(R.id.btnGeography).setOnClickListener {
            fetchQuizzesByCategory("Geography")
        }
        findViewById<Button>(R.id.btnEnglish).setOnClickListener {
            fetchQuizzesByCategory("English")
        }
        findViewById<Button>(R.id.btnGeneralKnowledge).setOnClickListener {
            fetchQuizzesByCategory("General Knowledge")
        }
    }
    private fun fetchQuizzesByCategory(category: String) {
        RetrofitClient.api.getQuizzesByCategory(category).enqueue(object :
            Callback<List<QuizCreate>> {
            override fun onResponse(call: Call<List<QuizCreate>>, response: Response<List<QuizCreate>>) {
                if (response.isSuccessful) {
                    val quizzes = response.body()
                    if (quizzes != null) {
                        quizList.clear()
                        quizList.addAll(quizzes)
                        quizAdapter.notifyDataSetChanged()
                    } else {
                        Toast.makeText(
                            this@CategoriesActivity,
                            "No quizzes found for $category",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                } else {
                    // Handle server error
                    Toast.makeText(
                        this@CategoriesActivity,
                        "Failed to fetch quizzes. Server error.",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

            override fun onFailure(call: Call<List<QuizCreate>>, t: Throwable) {
                // Show a user-friendly message if the request fails
                Toast.makeText(
                    this@CategoriesActivity,
                    "Server error occurred. Please try again later.",
                    Toast.LENGTH_SHORT
                ).show()

                // Optionally, log the error for debugging purposes
                t.printStackTrace()
            }
        })
    }
}
