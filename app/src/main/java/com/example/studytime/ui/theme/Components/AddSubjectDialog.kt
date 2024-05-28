package com.example.studytime.ui.theme.Components

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable

@Composable
fun AddSubjectDialog(
    onDismissRequest : () -> Unit,
    onConfirmButtonClick : () -> Unit
) {

    AlertDialog(
        onDismissRequest = onDismissRequest,
        title = { Text(text = "Hell")},
        text = {

        },
        dismissButton = {
            TextButton(onClick = onDismissRequest) {
                Text(text = "Cancel")

            }
        },
        confirmButton = {
            TextButton(onClick = onConfirmButtonClick) {
                Text(text = "Save")
            }
        }

    )
}