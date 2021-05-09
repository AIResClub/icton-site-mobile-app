package com.itmo.ictmobile.screens.askIct.askquestion

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.itmo.ictmobile.R
import com.itmo.ictmobile.data.models.Question
import kotlinx.android.synthetic.main.activity_main.*

class AnswerIctActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.answer_ict_activity)

        setSupportActionBar(toolbar)


        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        supportActionBar?.setDisplayShowTitleEnabled(false)

        val question = Question(
            questionText = intent.getStringExtra("questionText")!!,
            id = intent.getStringExtra("questionId")!!
        )
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, AnswerIctQuestionFragment(question))
            .commit()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

}