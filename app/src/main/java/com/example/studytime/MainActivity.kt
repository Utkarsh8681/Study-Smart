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
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavGraph
import com.example.studytime.ui.theme.Dashboard.NavGraphs
import com.example.studytime.ui.theme.Domain.model.Session
import com.example.studytime.ui.theme.Domain.model.Subject
import com.example.studytime.ui.theme.StudyTimeTheme
import com.ramcosta.composedestinations.DestinationsNavHost

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
    Subject(name = "Maths" , goalHours = 10f, colors = Subject.subjectCardColors[0] , subjectId = 1),
    Subject(name = "English" , goalHours = 10f, colors = Subject.subjectCardColors[1],subjectId = 2),
    Subject(name = "Physics" , goalHours = 10f, colors = Subject.subjectCardColors[2],subjectId = 3),
    Subject(name = "Maths" , goalHours = 10f, colors = Subject.subjectCardColors[3],subjectId = 4),
    Subject(name = "Maths" , goalHours = 10f, colors = Subject.subjectCardColors[4],subjectId = 5)
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

