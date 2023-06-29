package com.aold.advers.presentation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Switch
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.aold.advers.R
import com.aold.advers.WindowInfo
import com.aold.advers.presentation.notifications.Counter
import com.aold.advers.presentation.notifications.CounterNotificationService
import com.aold.advers.presentation.parts.TimerHeaderText
import com.aold.advers.rememberWindowInfo

/**
 * @author Kirilin Yury on 25.06.2023.
 */
@RequiresApi(Build.VERSION_CODES.M)
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun TimerScreen(
    navController: NavController,
) {
    val context = LocalContext.current
    val service = CounterNotificationService(context)
    val windowInfo = rememberWindowInfo()
    if (windowInfo.screenWidthInfo is WindowInfo.WindowType.Compact) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .verticalScroll(rememberScrollState())
        ) {
            TimerHeaderText()
            TimerGeneralOptionsUI()
            //SupportOptionsUI()
            Column(
                modifier = Modifier.fillMaxWidth().fillMaxHeight(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Button(
                    border = BorderStroke(1.dp, Color.Transparent),
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color.Red),
                    shape = RoundedCornerShape(15),
                    modifier = Modifier
                        .width(650.dp)
                        .height(65.dp),
                    onClick = {
                        //todo подключение к датчику
//                        navController.navigate(Screen.TemperatureHumidityScreen.route) {
//                            popUpTo(Screen.StartScreen.route) {
//                                inclusive = true
//                            }
//                        }
//                navController.navigate(Screen.TestScreen.route) {
////                            popUpTo(Screen.StartScreen.route) {
////                                inclusive = true
////                            }
////                        }
//                }
                        service.showNotification(Counter.value)
                    })
                {
                    Text(
                        text = "Отложенный запуск",
                        style = TextStyle(
                            fontSize = 15.sp,
                            color = Color.White
                        )
                    )
                }
            }
        }

        @ExperimentalMaterialApi
        @Composable
        fun GeneralOptionsUI() {
            Column(
                modifier = Modifier
                    .padding(horizontal = 114.dp)
                    .padding(top = 10.dp)
            ) {
                Text(
                    color = Color.White,
                    text = stringResource(id = R.string.general_setting_string),
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .padding(vertical = 8.dp)
                )
//            GeneralSettingItem(
//                mainText = stringResource(id = R.string.notifications_setting_string),
//                subText = "Customize notifications",
//                onClick = {}
//            )
                Spacer(modifier = Modifier.height(20.dp))
//            GeneralSettingItem(
//                mainText = "More customization",
//                subText = "Customize it more to fit your usage",
//                onClick = {}
//            )
            }
        }
    } else {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .verticalScroll(rememberScrollState())
        ) {
            TimerHeaderText()
            TimerGeneralOptionsUI()
            //SupportOptionsUI()
            Column(
                modifier = Modifier.fillMaxWidth().fillMaxHeight(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Button(
                    border = BorderStroke(1.dp, Color.Transparent),
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color.Red),
                    shape = RoundedCornerShape(15),
                    modifier = Modifier
                        .width(650.dp)
                        .height(65.dp),
                    onClick = {
                        service.showNotification(Counter.value)
                    })
                {
                    Text(
                        text = "Отложенный запуск",
                        style = TextStyle(
                            fontSize = 25.sp,
                            color = Color.White
                        )
                    )
                }
            }
        }
    }


        @ExperimentalMaterialApi
        @Composable
        fun GeneralOptionsUI() {
            Column(
                modifier = Modifier
                    .padding(horizontal = 114.dp)
                    .padding(top = 10.dp)
            ) {
                Text(
                    color = Color.White,
                    text = stringResource(id = R.string.general_setting_string),
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .padding(vertical = 8.dp)
                )
//            GeneralSettingItem(
//                mainText = stringResource(id = R.string.notifications_setting_string),
//                subText = "Customize notifications",
//                onClick = {}
//            )
                Spacer(modifier = Modifier.height(20.dp))
//            GeneralSettingItem(
//                mainText = "More customization",
//                subText = "Customize it more to fit your usage",
//                onClick = {}
//            )
            }
        }
    }


@ExperimentalMaterialApi
@Composable
fun TimerGeneralOptionsUI() {
    Column(
        modifier = Modifier
            .padding(horizontal = 14.dp)
            .padding(top = 10.dp)
            .height(380.dp)
    ) {
        TimerGeneralSettingItem(
            icon = R.drawable.ic_more,
            mainText = "00:00",
            subText = "Пн Вт Ср Чт Пт Сб Вс",
            onClick = {}
        )
        Spacer(modifier = Modifier.height(10.dp))
        TimerGeneralSettingItem(
            icon = R.drawable.ic_more,
            mainText = "00:00",
            subText = "Пн Вт Ср Чт Пт Сб Вс",
            onClick = {}
        )
        Spacer(modifier = Modifier.height(10.dp))
        TimerGeneralSettingItem(
            icon = R.drawable.ic_more,
            mainText = "00:00",
            subText = "Пн Вт Ср Чт Пт Сб Вс",
            onClick = {}
        )
        Spacer(modifier = Modifier.height(10.dp))
        TimerGeneralSettingItem(
            icon = R.drawable.ic_more,
            mainText = "00:00",
            subText = "Пн Вт Ср Чт Пт Сб Вс",
            onClick = {}
        )
        Spacer(modifier = Modifier.height(10.dp))
        TimerGeneralSettingItem(
            icon = R.drawable.ic_more,
            mainText = "00:00",
            subText = "Пн Вт Ср Чт Пт Сб Вс",
            onClick = {}
        )
        Spacer(modifier = Modifier.height(10.dp))
//        GeneralSettingItem()
    }
}

@ExperimentalMaterialApi
@Composable
fun TimerGeneralSettingItem(icon: Int, mainText: String, subText: String, onClick: () -> Unit) {
    Card(
        onClick = { onClick() },
        backgroundColor = Color.White,
        modifier = Modifier
            .padding(bottom = 8.dp)
            .fillMaxWidth(),
        elevation = 4.dp,
        shape = RoundedCornerShape(20.dp),
    ) {
        Row(
            modifier = Modifier.padding(vertical = 10.dp, horizontal = 14.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Column(
                    modifier = Modifier.offset(y = (2).dp)
                ) {
                    Text(
                        text = mainText,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                    )

                    Text(
                        text = subText,
                        color = Color.Gray,
                        fontSize = 10.sp,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier.offset(y = (-4).dp)
                    )
                }
            }
            Switch(checked = true, onCheckedChange = null)
        }
    }
}

