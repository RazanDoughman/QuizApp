package com.example.quizapp

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.quizapp.Activity.CategoriesActivity
import com.example.quizapp.Activity.CategoryDetailActivity
import com.example.quizapp.Activity.CreateQuizActivity
import com.example.quizapp.Activity.HardQuestionActivity
import com.example.quizapp.Activity.QuestionActivity
import com.example.quizapp.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.singleBtn.setOnClickListener {
            val intent = Intent(requireContext(), QuestionActivity::class.java)
            startActivity(intent)
        }
        binding.spinTheWheelBtn.setOnClickListener{
            val intent = Intent(requireContext(), SpinWheelActivity::class.java)
            startActivity(intent)
        }

        binding.solveButton.setOnClickListener {
            val intent = Intent(requireContext(), HardQuestionActivity::class.java)
            startActivity(intent)
        }

        binding.createBtn.setOnClickListener {
            val intent = Intent(requireContext(), CreateQuizActivity::class.java)
            startActivity(intent)
        }

        binding.seeAll.setOnClickListener {
            val intent = Intent(requireContext(), CategoriesActivity::class.java)
            startActivity(intent)
        }

        setupCategoryClickListeners()
    }

    private fun setupCategoryClickListeners() {
        val categories = CategoryRepository.categories

        binding.scienceCategory.setOnClickListener {
            navigateToCategoryDetail(categories[0])
        }

        binding.historyCategory.setOnClickListener {
            navigateToCategoryDetail(categories[1])
        }

        binding.sportCategory.setOnClickListener {
            navigateToCategoryDetail(categories[2])
        }

        binding.artCategory.setOnClickListener {
            navigateToCategoryDetail(categories[3])
        }
    }

    private fun navigateToCategoryDetail(category: Category) {
        val intent = Intent(requireContext(), CategoryDetailActivity::class.java).apply {
            putExtra("CATEGORY_NAME", category.name)
            putExtra("CATEGORY_IMAGE", category.imageResId)
            putExtra("CATEGORY_DESCRIPTION", category.description)
        }
        startActivity(intent)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
