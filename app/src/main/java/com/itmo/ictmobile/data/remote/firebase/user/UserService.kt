package com.itmo.ictmobile.data.remote.firebase.user

import com.google.firebase.firestore.FirebaseFirestore
import com.itmo.ictmobile.data.models.User

class UserService(private val database: FirebaseFirestore) {

    private val repository = UserRepository(database)

    fun executeLogin(user: User) = repository.login(user)
    fun executeRegister(user: User) = repository.register(user)

}