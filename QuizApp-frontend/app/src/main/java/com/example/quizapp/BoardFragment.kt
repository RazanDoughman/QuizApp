package com.example.quizapp

import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.quizapp.Activity.CategoryDetailActivity
import com.example.quizapp.databinding.FragmentBoardBinding
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.formatter.PercentFormatter
import com.github.mikephil.charting.utils.MPPointF

class BoardFragment : Fragment() {

    private var _binding: FragmentBoardBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentBoardBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupPieChart()
        setupCategoryClickListeners()
    }

    private fun setupPieChart() {
        val pieChart: PieChart = binding.pieChart

        pieChart.setUsePercentValues(true)
        pieChart.description.isEnabled = false
        pieChart.setExtraOffsets(5f, 10f, 5f, 5f)
        pieChart.dragDecelerationFrictionCoef = 0.95f
        pieChart.setDrawHoleEnabled(true)
        pieChart.setHoleColor(Color.WHITE)
        pieChart.setTransparentCircleColor(Color.WHITE)
        pieChart.setTransparentCircleAlpha(110)
        pieChart.holeRadius = 58f
        pieChart.transparentCircleRadius = 61f
        pieChart.setDrawCenterText(true)
        pieChart.rotationAngle = 0f
        pieChart.isRotationEnabled = true
        pieChart.isHighlightPerTapEnabled = true
        pieChart.animateY(1400, com.github.mikephil.charting.animation.Easing.EaseInOutQuad)
        pieChart.legend.isEnabled = false
        pieChart.setEntryLabelColor(Color.WHITE)
        pieChart.setEntryLabelTextSize(12f)

        val entries: ArrayList<PieEntry> = arrayListOf(
            PieEntry(70f, "Programming"),
            PieEntry(20f, "Science"),
            PieEntry(10f, "Math")
        )

        val dataSet = PieDataSet(entries, "Popular Categories")
        dataSet.setDrawIcons(false)
        dataSet.sliceSpace = 3f
        dataSet.iconsOffset = MPPointF(0f, 40f)
        dataSet.selectionShift = 5f
        dataSet.colors = listOf(
            resources.getColor(R.color.purple_200),
            resources.getColor(R.color.yellow),
            resources.getColor(R.color.red)
        )

        val data = PieData(dataSet)
        data.setValueFormatter(PercentFormatter())
        data.setValueTextSize(15f)
        data.setValueTypeface(Typeface.DEFAULT_BOLD)
        data.setValueTextColor(Color.WHITE)

        pieChart.data = data
        pieChart.invalidate()
    }

    private fun setupCategoryClickListeners() {
        val categoryRepository = CategoryRepository.categories

        binding.programmingCategory.setOnClickListener {
            navigateToCategoryDetail(categoryRepository.find { it.name == "Programming" })
        }

        binding.mathCategory.setOnClickListener {
            navigateToCategoryDetail(categoryRepository.find { it.name == "Math" })
        }

        binding.scienceCategory.setOnClickListener {
            navigateToCategoryDetail(categoryRepository.find { it.name == "Science" })
        }
    }

    private fun navigateToCategoryDetail(category: Category?) {
        category?.let {
            val intent = Intent(requireContext(), CategoryDetailActivity::class.java).apply {
                putExtra("CATEGORY_NAME", it.name)
                putExtra("CATEGORY_IMAGE", it.imageResId)
                putExtra("CATEGORY_DESCRIPTION", it.description)
            }
            startActivity(intent)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
