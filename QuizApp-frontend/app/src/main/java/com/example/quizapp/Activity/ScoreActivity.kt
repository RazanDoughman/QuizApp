package com.example.quizapp.Activity

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.quizapp.R
import com.example.quizapp.databinding.ActivityMainBinding
import com.example.quizapp.databinding.ActivityScoreBinding

class ScoreActivity : AppCompatActivity() {
    lateinit var binding: ActivityScoreBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_score)
        binding = ActivityScoreBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Get the score from the intent
        val score = intent.getIntExtra("SCORE", 0)
        binding.apply {
            backBtn.setOnClickListener{
                startActivity(Intent(this@ScoreActivity,MainActivity::class.java))
                finish()
            }
        }

        // Find the TextView and set the score
        val scoreTextView = findViewById<TextView>(R.id.scoreTextView)
        scoreTextView.text = "$score"
    }
}
