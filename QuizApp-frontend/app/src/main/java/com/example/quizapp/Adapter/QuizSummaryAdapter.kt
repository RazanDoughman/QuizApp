    package com.example.quizapp.Adapter

    import android.util.Log
    import android.view.LayoutInflater
    import android.view.View
    import android.view.ViewGroup
    import android.widget.TextView
    import androidx.recyclerview.widget.AsyncListDiffer
    import androidx.recyclerview.widget.DiffUtil
    import androidx.recyclerview.widget.RecyclerView
    import com.bumptech.glide.Glide
    import com.example.quizapp.Question
    import com.example.quizapp.R
    import com.example.quizapp.databinding.ViewholderLeadersBinding

    class QuizSummaryAdapter(private val questions: List<Question>) :
        RecyclerView.Adapter<QuizSummaryAdapter.QuestionViewHolder>() {

        inner class QuestionViewHolder(view: View) : RecyclerView.ViewHolder(view) {
            val questionText: TextView = view.findViewById(R.id.questionText)
            val optionsText: TextView = view.findViewById(R.id.optionsText)
            val correctAnswerText: TextView = view.findViewById(R.id.correctAnswerText)
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuestionViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.item_question_summary, parent, false)
            return QuestionViewHolder(view)
        }

        override fun onBindViewHolder(holder: QuestionViewHolder, position: Int) {
            val question = questions[position]

            // Log the question to verify if data is present
            Log.d("QuizSummaryAdapter", "Binding question: $question")

            holder.questionText.text = "Q${position + 1}: ${question.question.ifBlank { "No question provided" }}"
            holder.optionsText.text = question.options.joinToString("\n") { it.ifBlank { "No option provided" } }
            holder.correctAnswerText.text = "Correct: ${question.correctAnswer.ifBlank { "Not specified" }}"

        }

        override fun getItemCount() = questions.size
    }
