package com.itmo.ictmobile.data.remote.firebase.answer

import com.google.firebase.firestore.FirebaseFirestore
import com.itmo.ictmobile.data.models.Answer

class AnswerService(private val database: FirebaseFirestore) {

    private val repository = AnswerRepository(database)

    fun executeAdd(answer: Answer) = repository.createAnswer(answer)

    fun executeRead(qId: String) = repository.getAnswers(qId)

}