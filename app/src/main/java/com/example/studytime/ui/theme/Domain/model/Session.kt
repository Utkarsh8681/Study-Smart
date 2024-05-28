package com.example.studytime.ui.theme.Domain.model
data class Session(
    val sessionSubjectId : Int,
    val relatedToSubject : String,
    val date : Long,
    val duration : Long,
    val sessionId : Int
)
