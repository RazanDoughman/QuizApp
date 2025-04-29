package com.example.quizapp.Activity

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.quizapp.R

class CategoryDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category_detail)

        // Retrieve data from Intent
        val categoryName = intent.getStringExtra("CATEGORY_NAME")
        val categoryDescription = intent.getStringExtra("CATEGORY_DESCRIPTION")
        val categoryImageResId = intent.getIntExtra("CATEGORY_IMAGE", R.drawable.white_background)

        // Populate UI
        val backArrow = findViewById<ImageView>(R.id.backArrow)
        val categoryImage = findViewById<ImageView>(R.id.category_image)
        val categoryNameText = findViewById<TextView>(R.id.category_name)
        val categoryDescriptionText = findViewById<TextView>(R.id.category_description)
        val startQuizButton = findViewById<Button>(R.id.startQuizButton)

        categoryImage.setImageResource(categoryImageResId)
        categoryNameText.text = categoryName
        categoryDescriptionText.text = categoryDescription

        // Back Arrow Functionality
        backArrow.setOnClickListener {
            onBackPressed()
        }

        // Handle Start Quiz Button
        startQuizButton.setOnClickListener {
            val intent = Intent(this, QuestionActivity::class.java).apply {
                putExtra("CATEGORY_NAME", categoryName)  // Pass the category name to QuestionActivity
            }
            startActivity(intent)
        }
    }
}
