package com.itmo.ictmobile.screens.askIct.askquestion

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.itmo.IctApp
import com.itmo.ictmobile.R
import com.itmo.ictmobile.adapters.recycler.AnswerAdapter
import com.itmo.ictmobile.data.models.Answer
import com.itmo.ictmobile.data.models.Question
import com.itmo.ictmobile.util.Preferences
import com.itmo.ictmobile.util.Strings
import com.itmo.ictmobile.util.toast
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.answer_ict_question_fragment.*

class AnswerIctQuestionFragment(private val question: Question) : Fragment(R.layout.answer_ict_question_fragment) {

    private lateinit var answerIctQuestionViewModel: AnswerIctQuestionViewModel

    private val disposable = CompositeDisposable()

    private lateinit var answerAdapter: AnswerAdapter

    private val user = Preferences(IctApp.sharedPreferences).getUser()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        answerIctQuestionViewModel = ViewModelProviders.of(this).get(AnswerIctQuestionViewModel::class.java)
    }

    override fun onStart() {
        super.onStart()

        if (user != null) {
            if (!user.isStudent) {
                answerEt.visibility = View.GONE
                enterAnswer.visibility = View.GONE
            }
        }
        answerAdapter = AnswerAdapter()

        questionText.text = question.questionText

        ask_rv.adapter = answerAdapter

        disposable.add(
            answerIctQuestionViewModel.getAnswers(question.id)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    { answerAdapter.updateAnswers(it) },
                    { toast(Strings.get(R.string.sign_failed)) }
                )
        )

        enterAnswer.setOnClickListener {
            if (answerEt.text.isEmpty()) {
                toast("Пустое сообщение")
                return@setOnClickListener
            }

            val answer = Answer(
                question.id,
                answerEt.text.toString(),
                "${user?.firstName} ${user?.secondName}",
                System.currentTimeMillis()
            )

            disposable.add(
                answerIctQuestionViewModel.createAnswer(answer)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(
                        { answerEt.text.clear() },
                        { toast(Strings.get(R.string.sign_failed)) }
                    )
            )
        }
    }

    override fun onStop() {
        super.onStop()
        disposable.clear()
    }

}
