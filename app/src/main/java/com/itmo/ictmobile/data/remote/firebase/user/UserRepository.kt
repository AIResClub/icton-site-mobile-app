package com.itmo.ictmobile.data.remote.firebase.user

import com.google.firebase.firestore.FirebaseFirestore
import com.itmo.ictmobile.data.models.User
import io.reactivex.Single
import io.reactivex.SingleOnSubscribe
import io.reactivex.schedulers.Schedulers

class UserRepository(private val database: FirebaseFirestore) {

    fun register(user: User): Single<User> {
        return Single.create(SingleOnSubscribe<User> { emitter ->
            val ref = database.collection("users").document(user.username)
            ref.get().addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val document = task.result
                    if (document != null) {
                        if (document.exists()) {
                            emitter.onError(Exception("User exists"))
                        } else {
                            database.collection("users")
                                .document(user.username)
                                .set(user)
                                .addOnSuccessListener { emitter.onSuccess(user) }
                                .addOnFailureListener { emitter.onError(it) }
                        }
                    }
                } else {
                    emitter.onError(task.exception!!)
                }
            }
        }).subscribeOn(Schedulers.io())
    }

    fun login(user: User): Single<User> {
        return Single.create(SingleOnSubscribe<User> { emitter ->
            val ref = database.collection("users").document(user.username)
            ref.get().addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val document = task.result
                    if (document != null) {
                        if (document.exists()) {
                            val enteredUser = document.toObject(User::class.java)
                            if (enteredUser != null && enteredUser.password.contentEquals(user.password)) {
                                emitter.onSuccess(enteredUser)
                            } else {
                                emitter.onError(Exception("Password incorrect"))
                            }
                        } else {
                            emitter.onError(Exception("User does not exists"))
                        }
                    }
                } else {
                    emitter.onError(task.exception!!)
                }
            }
        }).subscribeOn(Schedulers.io())
    }
}