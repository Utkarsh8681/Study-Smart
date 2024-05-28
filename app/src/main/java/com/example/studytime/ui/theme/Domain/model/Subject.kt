package com.example.studytime.ui.theme.Domain.model

import com.example.studytime.ui.theme.gradient1
import com.example.studytime.ui.theme.gradient2
import com.example.studytime.ui.theme.gradient3
import com.example.studytime.ui.theme.gradient4
import com.example.studytime.ui.theme.gradient5

data class Subject(
    val name: String,
    val goalHours: Float,
    val colors: List<androidx.compose.ui.graphics.Color>,
    val subjectId : Int
){
    companion object{
        val subjectCardCol0rs  = listOf(gradient1 , gradient2 , gradient3, gradient4, gradient5)
    }
}
