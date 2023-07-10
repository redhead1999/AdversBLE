package com.aold.advers.presentation.components.dialog

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable

@Composable
fun AgreementAlertDialog(name: String,
          showDialog: Boolean,
          onDismiss: () -> Unit) {
    if (showDialog) {
        AlertDialog(
            title = {
                Text("Согласие на отправку данных", color = MaterialTheme.colorScheme.primary)
            },
            text = {
                Text(text = name, color = MaterialTheme.colorScheme.primary)
            },
            onDismissRequest = onDismiss,
            confirmButton = {
                TextButton(onClick = onDismiss ) {
                    Text("ДА", color = MaterialTheme.colorScheme.primary)
                }
            },
            dismissButton = {
                TextButton(onClick = onDismiss ) {
                    Text("НЕТ", color = MaterialTheme.colorScheme.primary)
                }
            }
        )
    }
}