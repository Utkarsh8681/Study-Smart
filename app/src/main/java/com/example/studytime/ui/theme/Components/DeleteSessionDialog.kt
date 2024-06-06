package com.example.studytime.ui.theme.Components

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue

@Composable
fun DeleteSessionDialog(
    isOpen : Boolean,
    title : String ,
    bodyText  : String,
    onDismissRequest : () -> Unit,
    onConfirmButtonClick : () -> Unit
) {


    var subjectNameError by rememberSaveable {
        mutableStateOf<String?>(null)
    }
  var deleteSubjectDialig by rememberSaveable {
        mutableStateOf<String?>(null)
    }
    var goalHoursError by rememberSaveable {
        mutableStateOf<String?>(null)
    }

//    subjectNameError = when {
//        subjectName.isBlank()-> "Enter Subject Name"
//        subjectName.length >20 -> "Subject Name Length Should be less than 20 word"
//        subjectName.length<2 -> "Subject name should be greater than 2"
//        else -> null
//    }
//    goalHoursError = when {
//        goalHours.isBlank()-> "Enter Goal Hours Name"
//        goalHours.toFloatOrNull() == null -> "Invalid Number"
//        goalHours.toFloat() < 1f -> " Goal Hours should be greater than 1 Hour"
//        else -> null
//    }




    if(isOpen) {
        AlertDialog(
            onDismissRequest = onDismissRequest,
            title = { Text(text = title) },
            text = {
Text(text = bodyText)
//                Column {
//                    Row (
//                        modifier = Modifier.fillMaxWidth(),
//                        horizontalArrangement = Arrangement.SpaceAround
//                    ){
//                        Subject.subjectCardColors.forEach{ colors ->
//                            Box (
//                                modifier = Modifier
//                                    .size(25.dp)
//                                    .clip(CircleShape)
//                                    .border(
//                                        width = 1.dp, color = if (colors == selectedColor) {
//                                            Color.Black
//                                        } else Color.Transparent
//                                    )
//                                    .background(brush = Brush.verticalGradient(colors))
//                                    .clickable { onColorChange(colors) }
//
//
//                            )
//
//
//                        }
//                    }
//                    OutlinedTextField(
//                        value = subjectName,
//                        onValueChange = onSubjectNameChange,
//                        label = { Text(text = "Subject Name") },
//                        singleLine = true ,
//                        isError = subjectNameError != null && subjectName.isNotBlank(),
//                        supportingText = { Text(text = subjectNameError.orEmpty()) }
//                    )
//                    Spacer(modifier = Modifier.height(10.dp))
//                    OutlinedTextField(
//                        value = goalHours,
//                        onValueChange = onGoalHoursChange,
//                        label = { Text(text = "Goal Study Hours") },
//                        singleLine = true,
//                        isError = goalHoursError != null && goalHours.isNotBlank(),
//                        supportingText = { Text(text = goalHoursError.orEmpty()) },
//                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
//                    )
//
//                }
            },
            dismissButton = {
                TextButton(onClick = onDismissRequest) {
                    Text(text = "Cancel")

                }
            },
            confirmButton = {
                TextButton(onClick = onConfirmButtonClick)

                {

                    Text(text = "Delete")
                }
            }

        )
    }
}