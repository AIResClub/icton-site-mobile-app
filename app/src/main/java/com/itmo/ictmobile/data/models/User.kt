package com.itmo.ictmobile.data.models

data class User(
    var username: String = "",
    var password: String = "",
    var firstName: String = "",
    var secondName: String = "",
    var isStudent: Boolean = false,
    var points: Int = 0
    )
