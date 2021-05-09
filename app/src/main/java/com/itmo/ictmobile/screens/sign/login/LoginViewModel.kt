package com.itmo.ictmobile.screens.sign.login

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.itmo.IctApp
import com.itmo.ictmobile.data.models.User
import com.itmo.ictmobile.data.remote.firebase.user.UserService

class LoginViewModel(
    application: Application
) : AndroidViewModel(application) {

    private val userService = UserService(IctApp.database)

    fun login(user: User) = userService.executeLogin(user)

}