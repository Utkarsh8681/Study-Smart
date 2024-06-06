package com.example.studytime.ui.theme.Dashboard

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.studytime.sesso
import com.example.studytime.tasks
import com.example.studytime.ui.theme.Components.AddSubjectDialog
import com.example.studytime.ui.theme.Components.CountCard
import com.example.studytime.ui.theme.Components.DeleteSessionDialog
import com.example.studytime.ui.theme.Components.tasksList
import com.example.studytime.ui.theme.Dashboard.destinations.TaskScreenRouteDestination
//import com.example.studytime.ui.theme.Dashboard.destinations.TaskScreenRouteDestination
import com.example.studytime.ui.theme.Domain.model.Subject
import com.example.studytime.ui.theme.Domain.model.Task
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import studySessionList

data class SubjectScreenNavArgs(
    val subjectId : Int
)
@Destination(navArgsDelegate = SubjectScreenNavArgs::class)
@Composable
fun SubjectScreenRoute(
    navigator : DestinationsNavigator
) {
//    val viewModel :SubjectScreenViewModel = hiltViewModel()

   SubjectScreen(
       onTaskCardClicked = {
               taskId ->
           val navArgs = TaskScreenNavArgs(taskId = taskId , subjectId = null)
           navigator.navigate(TaskScreenRouteDestination(navArgs = navArgs))

       },
       onAddIconClicked = {
           val navArgs = TaskScreenNavArgs(taskId = null , subjectId = -1)
           navigator.navigate(TaskScreenRouteDestination(navArgs = navArgs))
       }
   ) {
       navigator.navigateUp()
   }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun SubjectScreen(
    onTaskCardClicked: (Int) -> Unit,
    onAddIconClicked:() ->Unit,
    onBackIconClick: () -> Unit
) {

    val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior()
    val listState = rememberLazyListState()
    val isFabExpanded by remember{
        derivedStateOf { listState.firstVisibleItemIndex == 0}
    }




    val completedTasks = listOf(
        Task(
            title = "Prepare Notes",
            description = "",
            dueDate = 0L,
            priority = 1,
            relatedSubject = "maths",
            isComplete = true,
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
            isComplete = true,
            taskId = 1,
            taskSubjectId = 0
        ),
        Task(
            title = "Assignment",
            description = "",
            dueDate = 0L,
            priority = 4,
            relatedSubject = "maths",
            isComplete = true,
            taskId = 1,
            taskSubjectId = 0
        ),

        )


    var addSubjectDialogOpen by rememberSaveable {
        mutableStateOf(false)
    }
    var deleteSubjectDialogOpen by remember {
        mutableStateOf(false)}

    var deleteSessionDialogOpen by remember {
        mutableStateOf(false)
    }
    var subjectName by remember {
        mutableStateOf("")
    }
    var goalHours by remember {
        mutableStateOf("")
    }
    var selectedColors by remember {
        mutableStateOf(Subject.subjectCardColors.random())
    }

    AddSubjectDialog(
        isOpen = addSubjectDialogOpen,
        subjectName = subjectName,
        goalHours = goalHours,
        onSubjectNameChange = {subjectName = it},
        onGoalHoursChange = {goalHours = it},
        selectedColor = selectedColors,
        onColorChange = {selectedColors = it},
        onDismissRequest = { addSubjectDialogOpen = false } ,
        onConfirmButtonClick = {addSubjectDialogOpen =false}
    )

    DeleteSessionDialog(isOpen = deleteSubjectDialogOpen   ,
        title = "Delete Subject",
        bodyText = "Are you Sure you want to delete this session? your studied hours will be reduced by " +
                "by this session time. this action cannot be undo.",
        onDismissRequest = { deleteSubjectDialogOpen = false },
        onConfirmButtonClick = { deleteSubjectDialogOpen = false}
    )
    DeleteSessionDialog(isOpen = deleteSessionDialogOpen   ,
        title = "Delete Subject",
        bodyText = "Are you Sure you want to delete this session? your studied hours will be reduced by " +
                "by this session time. this action cannot be undo.",
        onDismissRequest = { deleteSessionDialogOpen = false },
        onConfirmButtonClick = { deleteSessionDialogOpen = false}
    )


    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            SubjectScreenTopBar(
                title = "English",
                onBackIconClick = onBackIconClick,
                onDeleteIconClick = { deleteSubjectDialogOpen = true },
                onEditIconClick ={addSubjectDialogOpen = true},
                scrollBehavior = scrollBehavior
            )
        },
        floatingActionButton = {
ExtendedFloatingActionButton(
    expanded = isFabExpanded,
    onClick =  onAddIconClicked,
    icon ={  Icon(imageVector =Icons.Default.Add , contentDescription ="Add Icon" )},
    text = { Text(text = "Add Task")}
    )
        }
    ) {paddingValues ->  
LazyColumn (
    state = listState,
    modifier = Modifier
        .fillMaxSize()
        .padding(paddingValues)
){
item {
    SubjectOverviewSection(
        modifier = Modifier.fillMaxSize(),
        goalHours = "15",
        studyHours = "10",
        progress = .70f)
}
    tasksList(
        sectionTitle = "Upcoming Tasks",
        emptyLis = "You don't have any upcomonh tasks \n " +
                "Click the + button in Subject screen to add a task. ",
        tasks = tasks,
        onTaskCardClicked =onTaskCardClicked ,
        onCheckBoxClicked = {}
    )
    tasksList(
        sectionTitle = "Completed Tasks",
        emptyLis = "You don't have any completed tasks \n " +
                "Click the + button in Subject screen to add a task. ",
        tasks = completedTasks,
        onTaskCardClicked = onTaskCardClicked,
        onCheckBoxClicked = {}
    )
    studySessionList(
        emptyLis = "You don't have any upcomonh tasks \n " +
                "Click the + button in Subject screen to add a task. ",
        sectionTitle = "Recent Study Sessions" ,
        sessions = sesso,
        onDeleteIconClick = {deleteSessionDialogOpen = true}
    )
}
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun SubjectScreenTopBar(
    title : String,
    onBackIconClick :() -> Unit,
    onDeleteIconClick :() -> Unit,
    onEditIconClick :() -> Unit,
    scrollBehavior: TopAppBarScrollBehavior


) {
        LargeTopAppBar(
            scrollBehavior = scrollBehavior,
        title = { Text(text = title,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            style = MaterialTheme.typography.headlineSmall
            ) } ,
        navigationIcon = {
            IconButton(onClick = {onBackIconClick() }) {
                Icon(imageVector = Icons.Default.ArrowBack, contentDescription ="Go Back to last Screen" )
            }
        },
        actions = {
            IconButton(onClick = { onDeleteIconClick() }) {
                Icon(imageVector = Icons.Default.Delete, contentDescription ="Delete Subject" )
            }
            IconButton(onClick = { onEditIconClick() }) {
                Icon(imageVector = Icons.Default.Edit, contentDescription ="Edit Subject" )
            }

        }


        )
}

@Composable
private fun SubjectOverviewSection(
    modifier: Modifier,
    goalHours : String,
    studyHours : String,
    progress : Float
) {
    val progressPercentage = remember(progress){
        ((progress*100).toInt().coerceIn(0,100))

    }

    Row(
        modifier = Modifier.padding(12.dp),
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically
    ) {
        CountCard(count = goalHours, headlineText = "Goal Study Hours", modifier = Modifier.weight(1f)  )
        Spacer(modifier = Modifier.width(10.dp))
        CountCard(count = studyHours, headlineText = "Study Hours"  , modifier = Modifier.weight(1f))
        Spacer(modifier = Modifier.width(10.dp))
        Box (

           modifier =  Modifier.size(75.dp) ,
            contentAlignment = Alignment.Center
        ){
            CircularProgressIndicator(
                progress =1f,
                modifier = Modifier.fillMaxSize(),
                strokeWidth = 4.dp,
                strokeCap = StrokeCap.Round,
                color = MaterialTheme.colorScheme.surfaceVariant

            )
            CircularProgressIndicator(
                progress =progress,
                modifier = Modifier.fillMaxSize(),
                strokeWidth = 4.dp,
                strokeCap = StrokeCap.Round,
            )
            Text(text = "$progressPercentage%")
        }
    }
}

