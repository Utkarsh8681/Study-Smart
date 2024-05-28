package com.example.studytime.ui.theme.Domain.model

data class Task(
    val title : String,
    val taskSubjectId : Int,
    val description : String,
    val dueDate: Long,
    val priority : Int,
    val relatedSubject: String,
    val isComplete : Boolean,
    val taskId : Int
)
