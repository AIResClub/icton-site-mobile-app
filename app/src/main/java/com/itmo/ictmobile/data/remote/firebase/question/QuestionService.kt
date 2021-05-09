package com.itmo.ictmobile.data.remote.firebase.question

import com.google.firebase.firestore.FirebaseFirestore
import com.itmo.ictmobile.data.models.Question

class QuestionService(private val database: FirebaseFirestore) {

    private val repository = QuestionRepository(database)

    fun executeAdd(question: Question) = repository.createQuestion(question)

    fun executeRead() = repository.getQuestions()

}