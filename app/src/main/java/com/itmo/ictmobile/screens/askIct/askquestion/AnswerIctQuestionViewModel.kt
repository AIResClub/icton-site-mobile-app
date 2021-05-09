package com.itmo.ictmobile.screens.askIct.askquestion

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.itmo.IctApp
import com.itmo.ictmobile.data.models.Answer
import com.itmo.ictmobile.data.remote.firebase.answer.AnswerService

class AnswerIctQuestionViewModel (application: Application) : AndroidViewModel(application) {

    private val answerService = AnswerService(IctApp.database)

    fun createAnswer(answer: Answer) = answerService.executeAdd(answer)

    fun getAnswers(qId: String) = answerService.executeRead(qId)

}