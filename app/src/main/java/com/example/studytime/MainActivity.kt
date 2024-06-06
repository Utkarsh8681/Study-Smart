package com.example.studytime

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavGraph
import com.example.studytime.ui.theme.Dashboard.DashboardScreenRoute
import com.example.studytime.ui.theme.Dashboard.NavGraphs
//import com.example.studytime.ui.theme.Dashboard.NavGraphs
import com.example.studytime.ui.theme.Domain.model.Session
import com.example.studytime.ui.theme.Domain.model.Subject
import com.example.studytime.ui.theme.Domain.model.Task
import com.example.studytime.ui.theme.StudyTimeTheme
import com.ramcosta.composedestinations.DestinationsNavHost
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            StudyTimeTheme {
                DestinationsNavHost(navGraph = NavGraphs.root)

            }
        }
    }
}
val subjects = listOf(
    Subject(name = "Maths" , goalHours = 10f, colors = Subject.subjectCardColors[0].map { it.toArgb() } , subjectId = 1),
    Subject(name = "English" , goalHours = 10f, colors = Subject.subjectCardColors[1].map { it.toArgb() },subjectId = 2),
    Subject(name = "Physics" , goalHours = 10f, colors = Subject.subjectCardColors[2].map { it.toArgb() },subjectId = 3),
    Subject(name = "Maths" , goalHours = 10f, colors = Subject.subjectCardColors[3].map { it.toArgb() },subjectId = 4),
    Subject(name = "Maths" , goalHours = 10f, colors = Subject.subjectCardColors[4].map { it.toArgb() },subjectId = 5)
)

val sesso = listOf(
    Session(
        sessionSubjectId =0,
        sessionId = 0,
        relatedToSubject = "Hindi",
        duration = 2,
        date = 0L
    ),
    Session(
        sessionSubjectId =0,
        sessionId = 0,
        relatedToSubject = "Maths",
        duration = 2,
        date = 0L
    ),
    Session(
        sessionSubjectId =0,
        sessionId = 0,
        relatedToSubject = "Science",
        duration = 2,
        date = 0L
    ),
    Session(
        sessionSubjectId =0,
        sessionId = 0,
        relatedToSubject = "English",
        duration = 2,
        date = 0L
    ),
)

val tasks = listOf(
    Task(
        title = "Prepare Notes",
        description = "",
        dueDate = 0L,
        priority = 1,
        relatedSubject = "maths",
        isComplete = false,
        taskId = 1,
        taskSubjectId = 0
    ),
    Task(
        title = "Do Home Work",
        description = "",
        dueDate = 0L,
        priority = 2,
        relatedSubject = "maths",
        isComplete = false,
        taskId = 1,
        taskSubjectId = 0
    ),
    Task(
        title = "Go Coaching",
        description = "",
        dueDate = 0L,
        priority = 3,
        relatedSubject = "maths",
        isComplete = false,
        taskId = 1,
        taskSubjectId = 0
    ),
    Task(
        title = "Assignment",
        description = "",
        dueDate = 0L,
        priority = 4,
        relatedSubject = "maths",
        isComplete = false,
        taskId = 1,
        taskSubjectId = 0
    ),

    )