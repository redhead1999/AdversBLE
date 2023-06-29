package com.aold.advers.presentation.parts.dialog

import androidx.compose.material.AlertDialog
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable

@Composable
fun CustomAlertDialog(name: String,
          showDialog: Boolean,
          onDismiss: () -> Unit) {
    if (showDialog) {
        AlertDialog(
            title = {
                Text("Справка", color = MaterialTheme.colors.primary)
            },
            text = {
                Text(text = name, color = MaterialTheme.colors.primary)
            },
            onDismissRequest = onDismiss,
            confirmButton = {
                TextButton(onClick = onDismiss ) {
                    Text("OK", color = MaterialTheme.colors.primary)
                }
            },
            dismissButton = {}
        )
    }
}