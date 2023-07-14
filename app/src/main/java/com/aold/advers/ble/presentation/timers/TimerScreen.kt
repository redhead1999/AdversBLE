package com.aold.advers.ble.presentation.timers

import android.os.Build
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.annotation.RequiresApi
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconToggleButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Transparent
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import com.aold.advers.ble.presentation.components.BasicBackTopAppBar
import com.aold.advers.ble.presentation.previewparams.FeatureParams
import com.aold.advers.ble.presentation.previewparams.LandscapeLayouts
import com.aold.advers.ble.presentation.previewparams.LandscapeListParams
import com.aold.advers.ble.presentation.previewparams.PortraitLayouts
import com.aold.advers.ble.presentation.previewparams.PortraitListParams
import com.aold.advers.ble.presentation.theme.AdversBleTheme
import com.aold.advers.ble.presentation.theme.appBarTitle
import com.aold.advers.ble.utils.windowinfo.AppLayoutInfo
import com.commandiron.wheel_picker_compose.WheelTimePicker
import com.commandiron.wheel_picker_compose.core.TimeFormat
import com.vanpra.composematerialdialogs.MaterialDialog
import com.vanpra.composematerialdialogs.datetime.date.datepicker
import com.vanpra.composematerialdialogs.datetime.time.timepicker
import com.vanpra.composematerialdialogs.rememberMaterialDialogState
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter

/**
 * @author Kirilin Yury on 13.07.2023.
 */


@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TimerScreen(
    appLayoutInfo: AppLayoutInfo,
    onBackClicked: () -> Unit,
) {

    val appSnackBarHostState = remember { SnackbarHostState() }

    val uriHandler = LocalUriHandler.current

    val pagingItems = listOf("Таймеры")
    val pagingItemCount by rememberSaveable { mutableStateOf(pagingItems.count()) }
    var currentPagingIndex by rememberSaveable { mutableStateOf(0) }

    Scaffold(
        // modifier = Modifier.border(2.dp, Color.Magenta),
        containerColor = Color.Transparent,
        snackbarHost = { SnackbarHost(hostState = appSnackBarHostState) },
        topBar = {
            if (!appLayoutInfo.appLayoutMode.isLandscape()) {
                BasicBackTopAppBar(appLayoutInfo = appLayoutInfo, onBackClicked = onBackClicked) {
                    Text(
                        text = "Таймеры",
                        style = appBarTitle
                    )
                }
            }
        }
    ) { padding ->

        val controlPadding = if (appLayoutInfo.appLayoutMode.isLandscape()) 0.dp
        else
            padding.calculateTopPadding()

        Column(
            modifier = Modifier
                .padding(top = controlPadding)
                .fillMaxSize()
        ) {

            if (appLayoutInfo.appLayoutMode.isLandscape()) {
                Row(modifier = Modifier.fillMaxSize()) {
                    Column(
                        modifier = Modifier
                            .weight(2f)
                            .fillMaxHeight()
                            .background(MaterialTheme.colorScheme.secondaryContainer)
                    ) {

                        BasicBackTopAppBar(
                            appLayoutInfo = appLayoutInfo,
                            onBackClicked = onBackClicked,
                            titleContent = {
//                                SocialIcons(
//                                    uriHandler, youTubeLink,
//                                    linkedInLink, gitHubLink
//                                )
                            }
                        )
                        Spacer(modifier = Modifier.height(12.dp))
                        //AppInfo()
                    }
                    Column(
                        modifier = Modifier
                            .weight(3f)
                            .padding(horizontal = 16.dp)
                            .verticalScroll(rememberScrollState())
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .verticalScroll(rememberScrollState()),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Top
                        ) {
                            Spacer(modifier = Modifier.height(20.dp))


                        }
                    }
                }

            } else {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(rememberScrollState()),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {


                    WheelTimePicker(timeFormat = TimeFormat.AM_PM) { snappedTime -> }
                    Spacer(modifier = Modifier.height(20.dp))
                    WeekDaysButtons()

                    val dateDialogState = rememberMaterialDialogState()
                    val timeDialogState = rememberMaterialDialogState()
                    //todo начало (ориентация портрет/телефон)

                    var pickedDate by remember {
                        mutableStateOf(LocalDate.now())
                    }
                    var pickedTime by remember {
                        mutableStateOf(LocalTime.NOON)
                    }
                    val formattedDate by remember {
                        derivedStateOf {
                            DateTimeFormatter
                                .ofPattern("MMM dd yyyy")
                                .format(pickedDate)
                        }
                    }
                    val formattedTime by remember {
                        derivedStateOf {
                            DateTimeFormatter
                                .ofPattern("hh:mm")
                                .format(pickedTime)
                        }
                    }

                    val context = LocalContext.current

                    MaterialDialog(
                        dialogState = dateDialogState,
                        buttons = {
                            positiveButton(text = "Ok") {
                                Toast.makeText(
                                    context,
                                    "Clicked ok",
                                    Toast.LENGTH_LONG
                                ).show()
                            }
                            negativeButton(text = "Cancel")
                        }
                    ) {
                        datepicker(
                            initialDate = LocalDate.now(),
                            title = "Pick a date",
                            allowedDateValidator = {
                                it.dayOfMonth % 2 == 1
                            }
                        ) {
                            pickedDate = it
                        }
                    }
                    MaterialDialog(
                        dialogState = timeDialogState,
                        buttons = {
                            positiveButton(text = "Ok") {
                                Toast.makeText(
                                    context,
                                    "Clicked ok",
                                    Toast.LENGTH_LONG
                                ).show()
                            }
                            negativeButton(text = "Cancel")
                        }
                    ) {
                        timepicker(
                            initialTime = LocalTime.NOON,
                            title = "Pick a time",
                            timeRange = LocalTime.MIDNIGHT..LocalTime.NOON
                        ) {
                            pickedTime = it
                        }
                    }


                }
            }
        }

    }
}

@Composable
fun WeekDaysButtons() {

    var checkedMonday by remember { mutableStateOf(false) }
    var checkedTuesday by remember { mutableStateOf(false) }
    var checkedWednesday by remember { mutableStateOf(false) }
    var checkedThursday by remember { mutableStateOf(false) }
    var checkedFriday by remember { mutableStateOf(false) }
    var checkedSaturday by remember { mutableStateOf(false) }
    var checkedSunday by remember { mutableStateOf(false) }

    val tintMonday by animateColorAsState(if (checkedMonday) Color.Gray else White)
    val tintTuesday by animateColorAsState(if (checkedTuesday) Color.Gray else White)
    val tintWednesday by animateColorAsState(if (checkedWednesday) Color.Gray else White)
    val tintThursday by animateColorAsState(if (checkedThursday) Color.Gray else White)
    val tintFriday by animateColorAsState(if (checkedFriday) Color.Gray else White)
    val tintSaturday by animateColorAsState(if (checkedSaturday) Color.Gray else White)
    val tintSunday by animateColorAsState(if (checkedSunday) Color.Gray else White)

    Row(
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        IconToggleButton(
            checked = checkedMonday,
            onCheckedChange = { checkedMonday = it },
            modifier = Modifier
                .clip(CircleShape)
                .border(1.dp, Transparent, CircleShape)
                .background(tintMonday)
        ) {
            Text("ПН", color = MaterialTheme.colorScheme.primary)
        }
        IconToggleButton(
            checked = checkedTuesday,
            onCheckedChange = { checkedTuesday = it },
            modifier = Modifier
                .clip(CircleShape)
                .border(1.dp, Transparent, CircleShape)
                .background(tintTuesday)
        ) {
            Text("ВТ", color = MaterialTheme.colorScheme.primary)
        }
        IconToggleButton(
            checked = checkedWednesday,
            onCheckedChange = { checkedWednesday = it },
            modifier = Modifier
                .clip(CircleShape)
                .border(1.dp, Transparent, CircleShape)
                .background(tintWednesday)


        ) {
            Text("СР", color = MaterialTheme.colorScheme.primary)
        }
        IconToggleButton(
            checked = checkedThursday,
            onCheckedChange = { checkedThursday = it },
            modifier = Modifier
                .clip(CircleShape)
                .border(1.dp, Transparent, CircleShape)
                .background(tintThursday)


        ) {
            Text("ЧТ", color = MaterialTheme.colorScheme.primary)
        }
        IconToggleButton(
            checked = checkedFriday,
            onCheckedChange = { checkedFriday = it },
            modifier = Modifier
                .clip(CircleShape)
                .border(1.dp, Transparent, CircleShape)
                .background(tintFriday)
        ) {
            Text("ПТ", color = MaterialTheme.colorScheme.primary)
        }
        IconToggleButton(
            checked = checkedSaturday,
            onCheckedChange = { checkedSaturday = it },
            modifier = Modifier
                .clip(CircleShape)
                .border(1.dp, Transparent, CircleShape)
                .background(tintSaturday)
        ) {
            Text("СБ", color = MaterialTheme.colorScheme.primary)
        }
        IconToggleButton(
            checked = checkedSunday,
            onCheckedChange = { checkedSunday = it },
            modifier = Modifier
                .clip(CircleShape)
                .border(1.dp, Transparent, CircleShape)
                .background(tintSunday)
        ) {
            Text("ВС", color = MaterialTheme.colorScheme.primary)
        }
    }
}

@Composable
fun userInterface(
    checkNotificationPermission: ActivityResultLauncher<String>,
    isPermission: Boolean = false
) {

}

@RequiresApi(Build.VERSION_CODES.O)
@PortraitLayouts
@Composable
fun PreviewPortraitAbout(
    @PreviewParameter(PortraitListParams::class) featureParams: FeatureParams,
) {
    AdversBleTheme() {
        TimerScreen(appLayoutInfo = featureParams.appLayoutInfo) {
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@LandscapeLayouts
@Composable
fun PreviewLandscapeAbout(
    @PreviewParameter(LandscapeListParams::class) featureParams: FeatureParams,
) {
    AdversBleTheme() {
        TimerScreen(appLayoutInfo = featureParams.appLayoutInfo) {
        }
    }
}