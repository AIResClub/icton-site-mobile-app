package com.itmo.ictmobile.data.models

data class Answer(
    var questionId: String = "",
    var answerText: String = "",
    var author: String = "",
    var sendTime: Long = 0
)