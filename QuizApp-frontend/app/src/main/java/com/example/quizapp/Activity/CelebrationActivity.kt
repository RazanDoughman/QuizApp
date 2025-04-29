package com.example.quizapp.Activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.quizapp.databinding.ActivityCelebrationBinding

class CelebrationActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCelebrationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCelebrationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Celebrate the correct answer (you can add animations or confetti here)
        binding.message.text = "🎉 You won 100 points 🎉"

        binding.apply {
            backBtn.setOnClickListener{
                startActivity(Intent(this@CelebrationActivity,MainActivity::class.java))
                finish()
            }
        }
    }
}
