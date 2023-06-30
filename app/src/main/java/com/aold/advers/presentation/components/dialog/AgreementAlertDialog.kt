package com.aold.advers.presentation.components.dialog

import androidx.compose.material.AlertDialog
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable

@Composable
fun AgreementAlertDialog(name: String,
          showDialog: Boolean,
          onDismiss: () -> Unit) {
    if (showDialog) {
        AlertDialog(
            title = {
                Text("Согласие на отправку данных", color = MaterialTheme.colors.primary)
            },
            text = {
                Text(text = name, color = MaterialTheme.colors.primary)
            },
            onDismissRequest = onDismiss,
            confirmButton = {
                TextButton(onClick = onDismiss ) {
                    Text("ДА", color = MaterialTheme.colors.primary)
                }
            },
            dismissButton = {
                TextButton(onClick = onDismiss ) {
                    Text("НЕТ", color = MaterialTheme.colors.primary)
                }
            }
        )
    }
}