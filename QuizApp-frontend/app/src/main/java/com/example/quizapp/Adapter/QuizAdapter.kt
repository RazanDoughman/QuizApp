package com.example.quizapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.quizapp.QuizCreate
import com.example.quizapp.R


class QuizAdapter(private val quizList: List<QuizCreate>) : RecyclerView.Adapter<QuizAdapter.QuizViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuizViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.quiz_item, parent, false)
        return QuizViewHolder(view)
    }

    override fun onBindViewHolder(holder: QuizViewHolder, position: Int) {
        val quiz = quizList[position]
        holder.titleTextView.text = quiz.title
        holder.categoryTextView.text = quiz.category
    }

    override fun getItemCount(): Int = quizList.size

    inner class QuizViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titleTextView: TextView = itemView.findViewById(R.id.quizTitle)
        val categoryTextView: TextView = itemView.findViewById(R.id.quizCategory)
    }
}


