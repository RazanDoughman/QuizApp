package com.example.quizapp.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.quizapp.R
import com.example.quizapp.databinding.ViewholderQuestionBinding

class Question2Adapter(private val questionCount: Int) : RecyclerView.Adapter<Question2Adapter.QuestionViewHolder>() {

    inner class QuestionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val questionTitle: TextView = itemView.findViewById(R.id.questionTitle)
        val questionName: EditText = itemView.findViewById(R.id.questionName)
        val answerOptions: List<EditText> = listOf(
            itemView.findViewById(R.id.answerOption1),
            itemView.findViewById(R.id.answerOption2),
            itemView.findViewById(R.id.answerOption3),
            itemView.findViewById(R.id.answerOption4)
        )
        val correctAnswerSpinner: Spinner = itemView.findViewById(R.id.correctAnswerSpinner)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuestionViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_question, parent, false)
        return QuestionViewHolder(view)
    }

    override fun onBindViewHolder(holder: QuestionViewHolder, position: Int) {
        holder.questionTitle.text = "Question No. ${position + 1}"
        val optionsAdapter = ArrayAdapter(holder.itemView.context, android.R.layout.simple_spinner_item, listOf("Option 1", "Option 2", "Option 3", "Option 4"))
        holder.correctAnswerSpinner.adapter = optionsAdapter
    }

    override fun getItemCount(): Int = questionCount
}
