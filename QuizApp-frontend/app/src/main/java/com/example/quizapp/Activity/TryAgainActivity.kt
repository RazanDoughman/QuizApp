package com.example.quizapp.Activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.quizapp.databinding.ActivityTryAgainBinding

class TryAgainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTryAgainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTryAgainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Inform the user that they need to try again
        binding.message.text = "❌ Oops, try again Tomorrow!❌"
        binding.apply {
            backBtn.setOnClickListener{
                startActivity(Intent(this@TryAgainActivity,MainActivity::class.java))
                finish()
            }
        }
    }

}
