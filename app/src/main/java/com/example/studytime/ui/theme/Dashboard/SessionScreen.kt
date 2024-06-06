package com.example.studytime.ui.theme.Dashboard
import android.graphics.Paint.Style
import android.se.omapi.Session
import android.view.View.AccessibilityDelegate
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.studytime.sesso
import com.example.studytime.subjects
import com.example.studytime.ui.theme.Components.DeleteSessionDialog
import com.example.studytime.ui.theme.Components.SubjectListBottomSheet
import com.example.studytime.ui.theme.Domain.model.Subject
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import kotlinx.coroutines.launch
import studySessionList

@Destination()
@Composable
fun SessionScreenRoute(
    navigator : DestinationsNavigator
) {
    SessionScreen(
        onBackButtonClick = {
            navigator.navigateUp()
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun SessionScreen(
//    onStudySessionClicked : ()->Unit
    onBackButtonClick: () -> Unit
) {



    var isBottomSheetOpen by remember {
        mutableStateOf(false)
    }
//    var deleteSessionDialogOpen by remember {
//        mutableStateOf(false)
//    }

    var deleteSubjectDialogOpen by remember {
        mutableStateOf(false)
    }
    val sheetState = rememberModalBottomSheetState()
    val scope = rememberCoroutineScope()
    SubjectListBottomSheet(
        sheetState =sheetState,
        isOpen = isBottomSheetOpen,
        subjectsList = subjects,
        onSubjectClicked = {
            scope.launch { sheetState.hide() }.invokeOnCompletion {
                if(!sheetState.isVisible) isBottomSheetOpen = false
            }
        },
        onDismissRequest = { isBottomSheetOpen = false }
    )

    DeleteSessionDialog(isOpen = deleteSubjectDialogOpen   ,
        title = "Delete Session",
        bodyText = "Are you Sure you want to delete this session? your studied hours will be reduced by " +
                "by this session time. this action cannot be undo.",
        onDismissRequest = { deleteSubjectDialogOpen = false },
        onConfirmButtonClick = { deleteSubjectDialogOpen = false}
    )
    Scaffold(
        topBar = {
            SessionScreenTopAppBar(title = "Study Session" , onBackButtonClick = onBackButtonClick)
        }
    ) {paddingValues ->
        LazyColumn(
            modifier = Modifier
                .padding(paddingValues)
                .padding(12.dp)
                .fillMaxSize()
        ) {

            item {
                    TimerSection(modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(1f))
            }
            item {
                RelatedToSubjectSection(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(12.dp),
                    onClickDropDown = { isBottomSheetOpen = true },
                    relatedToSubject = "English"
                )
            }
            item{
                ButtonSection(
                    modifier = Modifier.fillMaxWidth(),
                    startButtonClick = { /*TODO*/ },
                    finishButtonClick = { /*TODO*/ }) {
                    
                }
            }
            studySessionList(
                emptyLis = "You don't have any upcomonh tasks \n " +
                        "Click the + button in Subject screen to add a task. ",
                sectionTitle = "Study Sessions History" ,
                sessions = sesso,
                onDeleteIconClick = {deleteSubjectDialogOpen = true}
            )

        }


    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SessionScreenTopAppBar(
    title : String,
    onBackButtonClick : () -> Unit
) {
    TopAppBar(
        title = {
                Text(text = title , style = MaterialTheme.typography.headlineSmall)
        },
        navigationIcon = {
            IconButton(onClick = { onBackButtonClick() }) {
                Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Back Button")
            }
        }

    )
}

@Composable
fun TimerSection(
    modifier: Modifier
) {
    Box (
        modifier = modifier,
        contentAlignment = Alignment.Center
    ){
        Box (
            modifier = Modifier
                .size(250.dp)
                .border(5.dp, MaterialTheme.colorScheme.surfaceVariant, CircleShape),
            contentAlignment = Alignment.Center
        ){
            Text(text = "05:12:23" , style = MaterialTheme.typography.titleLarge.copy(fontSize = 45.sp))
        }
    }

}

@Composable
fun RelatedToSubjectSection(
    modifier: Modifier,
    onClickDropDown : () -> Unit,
    relatedToSubject : String
) {
    Column (
        modifier = modifier
    ){
        Text(
            text = "Related to subject",
            style = MaterialTheme.typography.bodySmall,
            fontWeight = FontWeight.Bold
        )
//        Spacer(modifier = modifier.height(10.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = relatedToSubject, style = MaterialTheme.typography.bodyMedium)
            IconButton(onClick = {onClickDropDown() }) {
                Icon(
                    imageVector = Icons.Default.ArrowDropDown,
                    contentDescription = "Select Subject"
                )
            }
        }
    }
}

@Composable
private fun ButtonSection(
    modifier: Modifier,
    startButtonClick : ()-> Unit,
    finishButtonClick : ()-> Unit,
    cancelButtonClick : ()-> Unit,
) {
    Row (modifier = modifier
        .fillMaxWidth()
        .padding(horizontal = 12.dp),
        horizontalArrangement = Arrangement.SpaceBetween
        ){
        Button(onClick =  cancelButtonClick , modifier = Modifier.padding(horizontal = 10.dp , vertical = 5.dp) ) {
            Text(text = "Cancel")
        }
//        Spacer(modifier = )
        Button(onClick = startButtonClick, modifier = Modifier.padding(horizontal = 10.dp , vertical = 5.dp)) {
            Text(text = "Start")
        }
        Button(onClick = finishButtonClick, modifier = Modifier.padding(horizontal = 10.dp , vertical = 5.dp)) {
            Text(text = "Finish")
        }
    }


}


