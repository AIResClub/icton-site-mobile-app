package com.itmo.ictmobile.data.remote.firebase.answer

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.itmo.ictmobile.data.models.Answer
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.ObservableOnSubscribe
import io.reactivex.schedulers.Schedulers

class AnswerRepository(private val database: FirebaseFirestore) {

    fun createAnswer(answer: Answer): Completable {
        return Completable.create { emitter ->
            database.collection("answers")
                .add(answer)
                .addOnSuccessListener { emitter.onComplete() }
                .addOnFailureListener { emitter.onError(it) }
        }.subscribeOn(Schedulers.io())
    }

    fun getAnswers(questionId: String): Observable<List<Answer>> {
        return Observable.create(ObservableOnSubscribe<List<Answer>> { emitter ->
            database.collection("answers")
                .whereEqualTo("questionId", questionId)
                .orderBy("sendTime", Query.Direction.DESCENDING)
                .addSnapshotListener { snapshot, error ->
                    if (error != null) {
                        emitter.onError(error)
                        return@addSnapshotListener
                    }

                    val answers = arrayListOf<Answer>()

                    snapshot?.let {
                        for (doc in snapshot) {
                            answers.add(
                                Answer(
                                    doc.getString("questionId")!!,
                                    doc.getString("answerText")!!,
                                    doc.getString("author")!!,
                                    doc.getLong("sendTime")!!
                                )
                            )
                        }
                    }
                    emitter.onNext(answers)
                }
        }).subscribeOn(Schedulers.io())
    }

}