package com.itmo.ictmobile.data.remote.firebase.question

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.itmo.ictmobile.data.models.Question
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.ObservableOnSubscribe
import io.reactivex.schedulers.Schedulers

class QuestionRepository(private val database: FirebaseFirestore) {

    fun createQuestion(question: Question): Completable {
        return Completable.create { emitter ->
            database.collection("questions")
                .add(question)
                .addOnSuccessListener { emitter.onComplete() }
                .addOnFailureListener { emitter.onError(it) }
        }.subscribeOn(Schedulers.io())
    }

    fun getQuestions(): Observable<List<Question>> {
        return Observable.create(ObservableOnSubscribe<List<Question>> { emitter ->
            database.collection("questions")
                .orderBy("timeSend", Query.Direction.DESCENDING)
                .addSnapshotListener { snapshot, error ->
                    if (error != null) {
                        emitter.onError(error)
                        return@addSnapshotListener
                    }

                    val questions = arrayListOf<Question>()

                    snapshot?.let {
                        for (doc in snapshot) {
                            questions.add(
                                Question(
                                    doc.id,
                                    doc.getString("author")!!,
                                    doc.getString("username")!!,
                                    doc.getString("questionText")!!,
                                    doc.getLong("timeSend")!!
                                )
                            )
                        }
                    }
                    emitter.onNext(questions)
                }

        }).subscribeOn(Schedulers.io())
    }

}