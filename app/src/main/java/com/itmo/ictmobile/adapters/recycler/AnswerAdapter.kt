package com.itmo.ictmobile.adapters.recycler

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.itmo.ictmobile.R
import com.itmo.ictmobile.data.models.Answer

class AnswerAdapter : RecyclerView.Adapter<AnswerAdapter.AnswerViewHolder>() {

    private var answers: List<Answer> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnswerViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val itemView = inflater.inflate(R.layout.item_ask, parent, false)

        return AnswerViewHolder(itemView)
    }

    override fun getItemCount() = answers.size

    override fun onBindViewHolder(holder: AnswerViewHolder, position: Int) {
        holder.bind(answers[position])
    }

    class AnswerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val answerTextView: TextView = itemView.findViewById(R.id.answerTextView)
        private val authorTextView: TextView = itemView.findViewById(R.id.authorTextView)

        fun bind(answer: Answer) {
            answerTextView.text = answer.answerText
            authorTextView.text = answer.author
        }
    }

    fun updateAnswers(newAnswers: List<Answer>) {
        answers = newAnswers
        notifyDataSetChanged()
    }
}