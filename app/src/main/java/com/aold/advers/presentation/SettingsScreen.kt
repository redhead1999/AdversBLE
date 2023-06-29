package com.aold.advers.presentation

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Switch
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.aold.advers.BuildConfig
import com.aold.advers.R
import com.aold.advers.presentation.parts.SettingsHeaderText
import com.aold.advers.presentation.parts.dialog.CustomAlertDialog
import com.aold.advers.ui.theme.AdversBluetoothTheme

/**
 * @author Kirilin Yury on 25.06.2023.
 */

@ExperimentalMaterialApi
@Composable
fun SettingsScreen(
    navController: NavController,
) {
    Column(
        modifier = Modifier
            .padding(16.dp)
            .verticalScroll(rememberScrollState())

    ) {
        var darkMode by remember { mutableStateOf(true) }



        AdversBluetoothTheme(darkTheme = darkMode) {
            SettingsHeaderText()
            Row() {
                Text(
                    color = MaterialTheme.colors.primary,
                    text = "Тема приложения",
                    fontSize = 14.sp,

                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .padding(vertical = 8.dp)
                )
                Spacer(modifier = Modifier.width(15.dp))
                Switch(checked = darkMode, onCheckedChange = { darkMode = !darkMode })
            }
            GeneralOptionsUI()
            SupportOptionsUI()
            displayVersion()
        }
    }
}


@ExperimentalMaterialApi
@Composable
fun GeneralOptionsUI() {
    Column(
        modifier = Modifier
            .padding(horizontal = 10.dp)
            .padding(top = 10.dp)
    ) {
        Text(
            color = MaterialTheme.colors.primary,
            text = stringResource(id = R.string.worktime_string),
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(vertical = 2.dp)
        )
        GeneralSettingItem(
            mainText = stringResource(id = R.string.unlimited_worktime_string),
            subText = "",
            onClick = {}
        )
        Text(
            color = MaterialTheme.colors.primary,
            text = stringResource(id = R.string.heater_and_reheater_string),
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(vertical = 8.dp)
        )
        GeneralSettingItem(
            mainText = stringResource(id = R.string.automatic_reheater_string),
            subText = "",
            onClick = {}
        )
    }
}

@ExperimentalMaterialApi
@Composable
fun GeneralSettingItem(mainText: String, subText: String, onClick: () -> Unit) {
    Card(
        onClick = { onClick() },
        backgroundColor = MaterialTheme.colors.primary,
        modifier = Modifier
            .padding(bottom = 8.dp)
            .fillMaxWidth(),
        elevation = 4.dp,
        shape = RoundedCornerShape(20.dp),
    ) {
        Row(
            modifier = Modifier.padding(vertical = 10.dp, horizontal = 2.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Spacer(modifier = Modifier.width(14.dp))
                Column(
                    modifier = Modifier.offset(y = (2).dp)
                ) {
                    Text(
                        text = mainText,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                    )
                }
            }
            //Switch(checked =true , onCheckedChange = null)
        }
    }
}

@ExperimentalMaterialApi
@Composable
fun SupportOptionsUI() {
    Column(
        modifier = Modifier
            .padding(horizontal = 10.dp)

    ) {
        Text(
            color = MaterialTheme.colors.primary,
            text = stringResource(id = R.string.pump_and_heating_string),
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(vertical = 2.dp)
        )
        SettingsItem(
            mainText = stringResource(id = R.string.pump_operation_in_standby_mode_string),
            onClick = {}
        )
        Spacer(modifier = Modifier.height(5.dp))
        SettingsItem(
            mainText = stringResource(id = R.string.operation_of_the_pump_with_the_engine),
            onClick = {}
        )
        Spacer(modifier = Modifier.height(5.dp))
        SettingsItem(
            mainText = stringResource(id = R.string.pump_string),
            onClick = {}
        )
        Spacer(modifier = Modifier.height(5.dp))

        val showDialog = remember { mutableStateOf(false) }
        if (showDialog.value) {
            CustomAlertDialog(name = "Cправочная информация - это очень ванжая информация." +
                    "Cправочная информация - это очень ванжая информация",
                showDialog = showDialog.value,
                onDismiss = {showDialog.value = false})
        }
        SettingsItem(
            mainText = stringResource(id = R.string.cabin_heater_start_temperature),
            onClick = {showDialog.value = true})
        Spacer(modifier = Modifier.height(5.dp))

        val context = LocalContext.current
        val intent = remember {
            Intent(
                Intent.ACTION_VIEW,
                Uri.parse("https://advers.ru/storage/app/media/certificate/privacy_policy.pdf")
            )
        }
        SettingsItem(
            mainText = stringResource(id = R.string.privacy_policy_setting_string),
            onClick = { context.startActivity(intent) }
//                    val urlIntent = Intent(
//                        Intent.ACTION_VIEW,
//                        Uri.parse("https://advers.ru/storage/app/media/certificate/privacy_policy.pdf")
        )
    }
}

@ExperimentalMaterialApi
@Composable
fun SettingsItemWithSwitcher(mainText: String, onClick: () -> Unit) {
    Card(
        onClick = { onClick() },
        backgroundColor = MaterialTheme.colors.primary,
        modifier = Modifier
            .padding(bottom = 8.dp)
            .fillMaxWidth(),
        elevation = 4.dp,
        shape = RoundedCornerShape(20.dp),
    ) {
        Row(
            modifier = Modifier.padding(vertical = 10.dp, horizontal = 19.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = mainText,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                )
            }
            Switch(checked =true , onCheckedChange = null)
        }
    }
}

@ExperimentalMaterialApi
@Composable
fun SettingsItem(mainText: String, onClick: () -> Unit) {
    Card(
        onClick = { onClick() },
        backgroundColor = MaterialTheme.colors.primary,
        modifier = Modifier
            .padding(bottom = 8.dp)
            .fillMaxWidth(),
        elevation = 4.dp,
        shape = RoundedCornerShape(20.dp),
    ) {
        Row(
            modifier = Modifier.padding(vertical = 10.dp, horizontal = 19.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = mainText,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                )
            }
            //Switch(checked =true , onCheckedChange = null)
        }
    }
}

@Composable
fun displayVersion() {

    // on below line we are creating a column
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 1.dp)
            .fillMaxHeight()
            .background(Color.Transparent),
        contentAlignment = Alignment.Center
    )
    {

        val version =
            "Версия: " + BuildConfig.VERSION_NAME + "\n" + "Cборка : " + BuildConfig.VERSION_CODE.toString()

        Text(
            textAlign = TextAlign.Center,
            color = MaterialTheme.colors.primary,
            text = version,
            modifier = Modifier.padding(5.dp),
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold
        )
    }
}
