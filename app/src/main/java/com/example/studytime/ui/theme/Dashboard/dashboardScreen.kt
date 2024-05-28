package com.example.studytime.ui.theme.Dashboard

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.studytime.R
import com.example.studytime.ui.theme.Components.CountCard
import com.example.studytime.ui.theme.Components.SubjectCard
import com.example.studytime.ui.theme.Components.tasksList
import com.example.studytime.ui.theme.Domain.model.Session
import com.example.studytime.ui.theme.Domain.model.Subject
import com.example.studytime.ui.theme.Domain.model.Task
import studySessionList
import java.security.cert.TrustAnchor

@Composable
fun DashboardScreen() {

    val subject = listOf(
        Subject(name = "Maths" , goalHours = 10f, colors = Subject.subjectCardCol0rs[0] , subjectId = 1),
        Subject(name = "English" , goalHours = 10f, colors = Subject.subjectCardCol0rs[1],subjectId = 2),
        Subject(name = "Physics" , goalHours = 10f, colors = Subject.subjectCardCol0rs[2],subjectId = 3),
        Subject(name = "Maths" , goalHours = 10f, colors = Subject.subjectCardCol0rs[3],subjectId = 4),
        Subject(name = "Maths" , goalHours = 10f, colors = Subject.subjectCardCol0rs[4],subjectId = 5)
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
            isComplete = true,
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

    Scaffold(
        topBar = {DashboardScreenTopAppBar()}
    ) {paddingValues ->
        LazyColumn(modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)) {
item {
    CountCardSection(
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp),
        subjectCount =15,
        studiedHours ="15",
        goalHours ="15"
    )
}
            item {
                SubjectsCardSection(modifier = Modifier.fillMaxWidth(), subjectList = subject)
            }
            item { 
                Button(onClick = { /*TODO*/ }, 
                    modifier = Modifier
                        .padding(horizontal = 48.dp, vertical = 20.dp)
                        .fillMaxWidth()

                    ) {
                    Text(text = "Start Study Session" )
                }
            }
          tasksList(
              sectionTitle = "Upcoming Tasks",
              emptyLis = "You don't have any upcomonh tasks \n " +
                      "Click the + button in Subject screen to add a task. ",
              tasks = tasks
          )
            studySessionList(
                emptyLis = "You don't have any upcomonh tasks \n " +
                        "Click the + button in Subject screen to add a task. ",
                sectionTitle = "Recent Study Sessions" ,
                sessions = sesso,
                onDeleteIconClick = {}

            )
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun DashboardScreenTopAppBar() {
    CenterAlignedTopAppBar(title = {
Text(text = "StudySmart" , style = MaterialTheme.typography.headlineMedium)
    })
}

@Composable
private fun CountCardSection(
    modifier: Modifier,
    subjectCount : Int,
    studiedHours : String,
    goalHours : String
) {
    Row (modifier = modifier){
        CountCard(
            modifier = Modifier.weight(1f),
            headlineText = "Subject Count",
            count = "$subjectCount"
        )
        Spacer(modifier = Modifier.width(10.dp))
        CountCard(modifier = Modifier.weight(1f),
            count = studiedHours,
            headlineText = "Study Hours")
        Spacer(modifier = Modifier.width(10.dp))
        CountCard(modifier = Modifier.weight(1f),
            headlineText = "Goal Study Hours",
            count = goalHours
        )
    }
}

@Composable
fun SubjectsCardSection(
    modifier: Modifier,
    subjectList : List<Subject>,
    emptyList : String =  "There are no subject. \n Click + to add subjects."
) {
    Column {
        Row(modifier = Modifier.fillMaxWidth() ,
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
            ) {
            Text(
                text = "Subjects",
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.padding(start = 12.dp)
            )
            IconButton(onClick = { /*TODO*/ }) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Add button")
            }
        }
        if(subjectList.isEmpty()){
            Image(
                modifier = modifier
                    .size(120.dp)
                    .align(Alignment.CenterHorizontally),
                painter = painterResource(id = R.drawable.books),
                contentDescription = emptyList
            )
            Text(
                modifier  =Modifier.fillMaxWidth(),
                text = emptyList,
                style = MaterialTheme.typography.bodySmall,
                color = Color.Gray,
                textAlign = TextAlign.Center
            )

        }
       LazyRow (
           horizontalArrangement = Arrangement.spacedBy(12.dp),
           contentPadding = PaddingValues(start = 12.dp , end = 12.dp)
       ){
           items(subjectList){subject ->
              SubjectCard(subjectName = subject.name, gradientColors =subject.colors, onClick = {})

           }
       }
    }
}

@Preview
@Composable
private fun Caared() {
DashboardScreen()
}