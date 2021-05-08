package com.itmo.ictmobile.data.models

class User(
    var username: String = "",
    var password: String = "",
    var firstName: String = "",
    var secondName: String = "",
    var points: Int = 0,
    var isStudent: Boolean = false
) {
}