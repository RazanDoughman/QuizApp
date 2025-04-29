package com.example.quizapp.Activity

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.quizapp.CreateQuizFragment
import com.example.quizapp.R
import com.example.quizapp.databinding.ActivityCreateQuizBinding


class CreateQuizActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCreateQuizBinding  // Declare the binding variable

    private val galleryLauncher =
        registerForActivityResult(ActivityResultContracts.GetContent()) { uri ->
            uri?.let {
                // Set the selected image
                binding.thumbnailImageView.setImageURI(uri)
                binding.thumbnailImageView.visibility = ImageView.VISIBLE // Show ImageView

                // Hide placeholder
                binding.selectImageText.visibility = TextView.GONE
                binding.placeholderIcon.visibility = ImageView.GONE
                binding.uploadHint.visibility = TextView.GONE
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreateQuizBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Gallery launcher for image picker
        binding.imageContainer.setOnClickListener {
            galleryLauncher.launch("image/*")
        }

        val backArrow = findViewById<ImageView>(R.id.backArrow)
        backArrow.setOnClickListener {
            finish()
        }

        //val quizCategorySpinner: Spinner = findViewById(R.id.quizCategorySpinner)
        val quizCategorySpinner = binding.quizCategorySpinner
        val dropdownArrow = binding.dropdownArrow

        // Set up the adapter with the array from strings.xml
        val adapter = ArrayAdapter.createFromResource(
            this,
            R.array.quiz_categories, // The array defined in strings.xml
            android.R.layout.simple_spinner_item // Default spinner item layout
        )

        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        // Apply the adapter to the Spinner
        quizCategorySpinner.adapter = adapter

        // Handle arrow click to trigger dropdown
        dropdownArrow.setOnClickListener {
            quizCategorySpinner.performClick() // Open the Spinner dropdown programmatically
        }


        // Handle selection events
        quizCategorySpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                val selectedAnswer = parent?.getItemAtPosition(position).toString()
                if (position == 0) {
                    // Default option selected
                    (view as TextView).setTextColor(getColor(R.color.darker_gray)) // Make default gray
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // Do nothing
            }
        }
        binding.nextButton.setOnClickListener {

            val numberOfQuestionsInput =
                binding.numberOfQuestionsInput.text.toString().toIntOrNull() ?: 0

            if (numberOfQuestionsInput > 0) {
                // Hide the main page (ScrollView)
                binding.scrollableContent.visibility = View.GONE

                binding.fixedBottomBar.visibility = View.GONE


                // Show the fragment container
                binding.fragmentContainer.visibility = View.VISIBLE

                // Pass the number of questions to the fragment
                val fragment = CreateQuizFragment()
                val bundle = Bundle()
                bundle.putInt("NUMBER_OF_QUESTIONS", numberOfQuestionsInput)
                bundle.putString("QUIZ_NAME", binding.quizNameInput.text.toString())
                bundle.putString("QUIZ_DESCRIPTION", binding.quizDescriptionInput.text.toString())
                fragment.arguments = bundle


                // Load the fragment into the container
                supportFragmentManager.beginTransaction()
                    .replace(R.id.fragmentContainer, fragment)
                    .addToBackStack(null) // Allows back navigation
                    .commit()
            } else {
                // Show an error message
                Toast.makeText(
                    this,
                    "Please enter a valid number of questions!",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
}
