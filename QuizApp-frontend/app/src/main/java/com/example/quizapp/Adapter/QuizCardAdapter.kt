package com.example.quizapp.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.quizapp.R
import com.example.quizapp.Question

class QuizCardAdapter(private val quizzes: List<Question>) :
    RecyclerView.Adapter<QuizCardAdapter.QuizViewHolder>() {

    class QuizViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val quizName: TextView = itemView.findViewById(R.id.quizName)
        val quizDescription: TextView = itemView.findViewById(R.id.quizDescription)
        val quizPoints: TextView = itemView.findViewById(R.id.quizPoints)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuizViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_quiz_card, parent, false)
        return QuizViewHolder(view)
    }

    override fun onBindViewHolder(holder: QuizViewHolder, position: Int) {
        val quiz = quizzes[position]
        holder.quizName.text = quiz.question // Assuming question holds the quiz name
        holder.quizDescription.text = quiz.options.joinToString(", ") // Placeholder description
        holder.quizPoints.text = "50 points" // Placeholder points
    }

    override fun getItemCount(): Int = quizzes.size
}