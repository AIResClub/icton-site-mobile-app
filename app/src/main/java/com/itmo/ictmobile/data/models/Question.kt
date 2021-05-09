package com.itmo.ictmobile.data.models

data class Question(
    var author: String = "",
    var username: String = "",
    var questionText: String = "",
    var timeSend: Long = 0
)