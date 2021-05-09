package com.itmo.ictmobile.screens.askIct

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.itmo.IctApp
import com.itmo.ictmobile.data.models.Question
import com.itmo.ictmobile.data.remote.firebase.question.QuestionService

class AskIctViewModel(application: Application) : AndroidViewModel(application) {

    private val questionService = QuestionService(IctApp.database)

    fun createQuestion(question: Question) = questionService.executeAdd(question)

    fun getQuestions() = questionService.executeRead()

}