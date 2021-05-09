package com.itmo.ictmobile.adapters.recycler

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.itmo.ictmobile.R
import com.itmo.ictmobile.data.models.Question
import com.itmo.ictmobile.screens.askIct.askquestion.AnswerIctActivity

class QuestionAdapter : RecyclerView.Adapter<QuestionAdapter.QuestionViewHolder>() {

    private var questions: List<Question> = listOf()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): QuestionViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val itemView = inflater.inflate(R.layout.item_question, parent, false)
        return QuestionViewHolder(itemView)
    }

    override fun getItemCount() = questions.size

    override fun onBindViewHolder(holder: QuestionViewHolder, position: Int) {
        holder.bind(questions[position])
    }

    fun updateQuestions(newQuestions: List<Question>) {
        questions = newQuestions
        notifyDataSetChanged()
    }

    class QuestionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val questionText: TextView = itemView.findViewById(R.id.question_text)
        private val author: TextView = itemView.findViewById(R.id.question_author)

        fun bind(question: Question) {
            questionText.text = question.questionText
            author.text = question.author

            itemView.setOnClickListener {
                val i = Intent(itemView.context, AnswerIctActivity::class.java)
                i.putExtra("questionText", question.questionText)
                i.putExtra("questionId", question.id)
                itemView.context.startActivity(i)
            }
        }

    }

}