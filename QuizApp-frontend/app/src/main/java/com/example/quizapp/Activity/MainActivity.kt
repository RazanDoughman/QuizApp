package com.example.quizapp.Activity

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.quizapp.BoardFragment
import com.example.quizapp.FavoritesFragment
import com.example.quizapp.HomeFragment
import com.example.quizapp.ProfileFragment
import com.example.quizapp.R
import com.example.quizapp.RetrofitClient
import com.example.quizapp.adapters.QuizAdapter
import com.example.quizapp.databinding.ActivityMainBinding
import com.example.quizapp.QuizCreate
import com.google.android.material.bottomnavigation.BottomNavigationView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import android.widget.Button


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
   // private lateinit var recyclerView: RecyclerView
//    private lateinit var quizAdapter: QuizAdapter
//    private val quizList = mutableListOf<QuizCreate>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        // Initialize RecyclerView
//        recyclerView = findViewById(R.id.recyclerView)
//        recyclerView.layoutManager = LinearLayoutManager(this)
//        quizAdapter = QuizAdapter(quizList)
//        recyclerView.adapter = quizAdapter

        // Bottom Navigation
        val bottomNavigationView: BottomNavigationView = findViewById(R.id.bottomBar)
        loadFragment(HomeFragment()) // Load default fragment (Home)

        // Set up bottom navigation listener
        binding.bottomBar.setOnItemSelectedListener { item ->
            val fragment: Fragment = when (item.itemId) {
                R.id.Board -> BoardFragment()
                R.id.Favorites -> FavoritesFragment()
                R.id.Profile -> ProfileFragment()
                else -> HomeFragment()
            }
            loadFragment(fragment)
            true
        }

        // Set up category button listeners
//        findViewById<Button>(R.id.btnScience).setOnClickListener {
//            fetchQuizzesByCategory("Science")
//        }
//        findViewById<Button>(R.id.btnHistory).setOnClickListener {
//            fetchQuizzesByCategory("History")
//        }
//        findViewById<Button>(R.id.btnMath).setOnClickListener {
//            fetchQuizzesByCategory("Math")
//        }
//        findViewById<Button>(R.id.btnProgramming).setOnClickListener {
//            fetchQuizzesByCategory("Programming")
//        }
//        findViewById<Button>(R.id.btnSport).setOnClickListener {
//            fetchQuizzesByCategory("Sport")
//        }
//        findViewById<Button>(R.id.btnArt).setOnClickListener {
//            fetchQuizzesByCategory("Art")
//        }
//        findViewById<Button>(R.id.btnGeography).setOnClickListener {
//            fetchQuizzesByCategory("Geography")
//        }
//        findViewById<Button>(R.id.btnEnglish).setOnClickListener {
//            fetchQuizzesByCategory("English")
//        }
//        findViewById<Button>(R.id.btnGeneralKnowledge).setOnClickListener {
//            fetchQuizzesByCategory("General Knowledge")
//        }
    }

    private fun loadFragment(fragment: Fragment) {
        // Replace the current fragment
        supportFragmentManager.beginTransaction()
            .replace(R.id.frame, fragment)
            .commit()
    }

//    private fun fetchQuizzesByCategory(category: String) {
//        RetrofitClient.api.getQuizzesByCategory(category).enqueue(object : Callback<List<QuizCreate>> {
//            override fun onResponse(call: Call<List<QuizCreate>>, response: Response<List<QuizCreate>>) {
//                if (response.isSuccessful) {
//                    val quizzes = response.body()
//                    if (quizzes != null) {
//                        quizList.clear()
//                        quizList.addAll(quizzes)
//                        quizAdapter.notifyDataSetChanged()
//                    } else {
//                        Toast.makeText(
//                            this@MainActivity,
//                            "No quizzes found for $category",
//                            Toast.LENGTH_SHORT
//                        ).show()
//                    }
//                } else {
//                    Toast.makeText(this@MainActivity, "Failed to fetch quizzes", Toast.LENGTH_SHORT)
//                        .show()
//                }
//            }
//
//            override fun onFailure(call: Call<List<QuizCreate>>, t: Throwable) {
//                Toast.makeText(this@MainActivity, "Error: ${t.message}", Toast.LENGTH_SHORT).show()
//            }
//        })
//    }
}
