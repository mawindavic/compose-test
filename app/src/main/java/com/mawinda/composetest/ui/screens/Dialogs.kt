package com.mawinda.composetest.ui.screens

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun ErrorDialog(message: String, onDismiss: () -> Unit = {}, modifier: Modifier = Modifier) {
    AlertDialog(
        modifier = modifier,
        title = {
            Text(text = "Error")
        },
        text = {
            Text(text = message)
        },
        onDismissRequest = {
            onDismiss.invoke()
        },
        confirmButton = {

        },
        dismissButton = {
            Text("Dismiss")
        }
    )
}

@Composable
fun ConfirmationDialog(
    message: String,
    onDismiss: () -> Unit = {},
    onConfirm: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    AlertDialog(
        modifier = modifier,
        title = {
            Text(text = "Are you sure?")
        },
        text = {
            Text(text = message)
        },
        onDismissRequest = {
            onDismiss.invoke()
        },
        confirmButton = {
            TextButton(
                onClick = {
                    onConfirm.invoke()
                }
            ) {
                Text("Confirm")
            }
        },
        dismissButton = {
            TextButton(
                onClick = {
                    onDismiss.invoke()
                }
            ) {
                Text("Dismiss")
            }
        }
    )
}


