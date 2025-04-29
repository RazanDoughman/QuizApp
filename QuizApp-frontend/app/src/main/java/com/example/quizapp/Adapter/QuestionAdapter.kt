package com.example.quizapp.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.quizapp.R
import com.example.quizapp.databinding.ViewholderQuestionBinding

class QuestionAdapter(
    private val correctAnswer: String,
    private val options: List<String?>,
    private val returnScore: score
) : RecyclerView.Adapter<QuestionAdapter.ViewHolder>() {

    interface score {
        fun amount(number: Int, clickedAnswer: String)
    }

    private var selectedAnswer: String? = null
    private var answerCorrectness: MutableList<Boolean> = MutableList(options.size) { false } // To store if each option is correct

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ViewholderQuestionBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val option = options[position]
        holder.binding.answerTxt.text = option

        // Handle background color based on selection
        if (selectedAnswer != null) {
            val isCorrect = option.equals(selectedAnswer, ignoreCase = true)
            val backgroundColor = if (isCorrect) R.drawable.green_background else R.drawable.red_background
            holder.binding.answerTxt.setBackgroundResource(backgroundColor)
            holder.binding.answerTxt.setTextColor(ContextCompat.getColor(holder.itemView.context, R.color.white))
        }

        holder.binding.root.setOnClickListener {
            // Determine if the answer is correct
            selectedAnswer = when (position) {
                0 -> "a"
                1 -> "b"
                2 -> "c"
                3 -> "d"
                else -> ""
            }

            // Mark if the selected answer is correct
            answerCorrectness[position] = selectedAnswer.equals(correctAnswer, ignoreCase = true)

            // Notify the activity about the selected answer
            returnScore.amount(5, selectedAnswer ?: "")
            notifyDataSetChanged()
        }
    }

    override fun getItemCount(): Int = options.size

    inner class ViewHolder(val binding: ViewholderQuestionBinding) : RecyclerView.ViewHolder(binding.root)
}