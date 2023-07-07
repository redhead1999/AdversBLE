package com.aold.advers.ble.presentation.components

import android.content.res.Configuration
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import com.aold.advers.R
import com.aold.advers.ble.local.entities.ScannedDevice
import com.aold.advers.ble.presentation.previewparams.ScannedDevicePreviewParameterProvider
import com.aold.advers.ble.presentation.theme.AdversBleTheme

@Composable
fun MainMenu(
    expanded: Boolean,
    onExpanded: (Boolean) -> Unit,
    onHelp: () -> Unit,
) {
    IconButton(
        //modifier = Modifier.offset((-14).dp),
        colors = IconButtonDefaults.iconButtonColors(
            contentColor = MaterialTheme.colorScheme.outline
        ),
        onClick = { onExpanded(true) }) {
        Icon(
            //modifier = Modifier.then(Modifier.padding(0.dp)),
            painter = painterResource(id = R.drawable.corporation),
            contentDescription = "Actions",
            tint = Color.Unspecified
        )
    }
    DropdownMenu(
        modifier = Modifier.border(
            1.dp,
            MaterialTheme.colorScheme.primaryContainer
        ),
        expanded = expanded,
        onDismissRequest = { onExpanded(false) }
    ) {
        DropdownMenuItem(
            text = { Text("Информация  ") },
            onClick = {
                onExpanded(false)
                onHelp()
            },
            leadingIcon = {
                Icon(
                    Icons.Outlined.Info,
                    contentDescription = "Help"
                )
            })
        DropdownMenuItem(
            text = { Text("Настройки") },
            onClick = {
                onExpanded(false)
            },
            leadingIcon = {
                Icon(
                    Icons.Outlined.Settings,
                    contentDescription = "Settings"
                )
            })
    }
}
@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES or Configuration.UI_MODE_TYPE_NORMAL, showBackground = true,
    showSystemUi = true
)
@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_NO or Configuration.UI_MODE_TYPE_NORMAL, showSystemUi = true, showBackground = true
)
@Composable
fun PreviewDeviceMenu(
    @PreviewParameter(ScannedDevicePreviewParameterProvider::class) device: ScannedDevice
) {

    //var state by rememberSaveable { mutableStateOf(0) }
    var expanded by rememberSaveable { mutableStateOf(false) }

    AdversBleTheme {
        Surface(
            modifier = Modifier.size(200.dp)
        ) {
            MainMenu(
                expanded = expanded,
                onExpanded = {expanded = it},
                {
                }
            )
        }
    }
}
