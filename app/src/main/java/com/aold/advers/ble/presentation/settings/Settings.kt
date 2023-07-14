package com.aold.advers.ble.presentation.settings

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Shapes
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.platform.UriHandler
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.aold.advers.BuildConfig
import com.aold.advers.R
import com.aold.advers.ble.presentation.components.BasicBackTopAppBar
import com.aold.advers.ble.presentation.previewparams.FeatureParams
import com.aold.advers.ble.presentation.previewparams.LandscapeLayouts
import com.aold.advers.ble.presentation.previewparams.LandscapeListParams
import com.aold.advers.ble.presentation.previewparams.PortraitLayouts
import com.aold.advers.ble.presentation.previewparams.PortraitListParams
import com.aold.advers.ble.presentation.theme.AdversBleTheme
import com.aold.advers.ble.presentation.theme.appBarTitle
import com.aold.advers.ble.presentation.theme.pagerHeaders
import com.aold.advers.ble.utils.windowinfo.AppLayoutInfo
import com.aold.advers.ble.presentation.components.dialog.AgreementAlertDialog
import com.aold.advers.ble.presentation.components.dialog.InfoAlertDialog

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Settings(
    appLayoutInfo: AppLayoutInfo,
    onBackClicked: () -> Unit
) {

    val appSnackBarHostState = remember { SnackbarHostState() }

    val uriHandler = LocalUriHandler.current

    Scaffold(
        // modifier = Modifier.border(2.dp, Color.Magenta),
        containerColor = Color.Transparent,
        snackbarHost = { SnackbarHost(hostState = appSnackBarHostState) },
        topBar = {
            if (!appLayoutInfo.appLayoutMode.isLandscape()) {
                BasicBackTopAppBar(appLayoutInfo = appLayoutInfo, onBackClicked = onBackClicked) {
                    Text(
                        text = "Настройки",
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
                        //todo landscape
                    }
                }

            } else {


                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(rememberScrollState()),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Top
                ) {
                    Spacer(modifier = Modifier.height(20.dp))
                    WorkTimeOptionsUI()
                    HeaterAndPreheaterOptionsUI()
                    PumpAndHeaterOptionsUI()
                    SystemOptionsUi()
                    HeaterOptionsUi()
                    RemoteDeviceControlOptionsUi()
                }
            }
        }
    }

    @Composable
    fun LegalStuff(
        privacyPolicyLink: String,
        termsLink: String,
        uriHandler: UriHandler
    ) {

        val annotatedString = buildAnnotatedString {
            withStyle(
                style = SpanStyle(
                    fontSize = 12.sp,
                    color = MaterialTheme.colorScheme.onSurface
                )
            ) {
                append("Примечание. Используя Autoterm Connect, вы соглашаетесь с ")
            }

            pushStringAnnotation(tag = "policy", annotation = privacyPolicyLink)
            withStyle(
                style = SpanStyle(
                    fontSize = 12.sp,
                    color = MaterialTheme.colorScheme.primary
                )
            ) {
                append("Политикой конфиденциальности")
            }
            pop()

            withStyle(
                style = SpanStyle(
                    fontSize = 12.sp,
                    color = MaterialTheme.colorScheme.onSurface
                )
            ) {
                append(" и ")
            }
            pushStringAnnotation(tag = "terms", annotation = termsLink)

            withStyle(
                style = SpanStyle(
                    fontSize = 12.sp,
                    color = MaterialTheme.colorScheme.primary
                )
            ) {
                append("Условиями использования")
            }
            pop()
        }

        ClickableText(text = annotatedString,
            style = MaterialTheme.typography.bodyLarge,
            onClick = { offset ->
                annotatedString.getStringAnnotations(
                    tag = "policy", start = offset, end = offset
                ).firstOrNull()?.let { stringAnnotation ->
                    uriHandler.openUri(stringAnnotation.item)
                }

                annotatedString.getStringAnnotations(
                    tag = "terms",
                    start = offset, end = offset
                ).firstOrNull()?.let { stringAnnotation ->
                    uriHandler.openUri(stringAnnotation.item)
                }
            })
    }
    }
@Composable
fun WorkTimeOptionsUI() {
    Column(
        modifier = Modifier
            .padding(horizontal = 14.dp)
            .padding(top = 10.dp)
    ) {
        Text(
            text = "Время работы",
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(vertical = 8.dp)
        )
        GeneralSettingItem(
            icon = R.drawable.ic_add,
            mainText = "Неограниченное время работы",
            subText = "Customize notifications",
            onClick = {}
        )
        GeneralSettingItem(
            icon = R.drawable.ic_more,
            mainText = "Задать время",
            subText = "Customize it more to fit your usage",
            onClick = {}
        )
//        GeneralSettingItem()
    }
}

@Composable
fun HeaterAndPreheaterOptionsUI() {
    Column(
        modifier = Modifier
            .padding(horizontal = 14.dp)
            .padding(top = 10.dp)
    ) {
        Text(
            text = "Подогреватель и догреватель",
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(vertical = 8.dp)
        )
        SupportItem(
            icon = R.drawable.ic_add,
            mainText = "Автоматический догреватель",
            onClick = {}
        )
        SupportItem(
            icon = R.drawable.ic_add,
            mainText = "Температура догревателя",
            onClick = {}
        )
        SupportItem(
            icon = R.drawable.ic_add,
            mainText = "Температура подогревателя",
            onClick = {}
        )
    }
}

@Composable
fun PumpAndHeaterOptionsUI() {
    Column(
        modifier = Modifier
            .padding(horizontal = 14.dp)
            .padding(top = 10.dp)
    ) {
        Text(
            text = "Помпа и отопление",
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(vertical = 8.dp)
        )
        GeneralSettingItem(
            icon = R.drawable.ic_add,
            mainText = "Неограниченное время работы",
            subText = "Customize notifications",
            onClick = {}
        )
        GeneralSettingItem(
            icon = R.drawable.ic_more,
            mainText = "Задать время",
            subText = "Customize it more to fit your usage",
            onClick = {}
        )
//        GeneralSettingItem()
    }
}

@Composable
fun SystemOptionsUi() {
    Column(
        modifier = Modifier
            .padding(horizontal = 14.dp)
            .padding(top = 10.dp)
    ) {
        Text(
            text = "Система",
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(vertical = 8.dp)
        )
        SystemSettingItem(
            deviceNameText = "Имя устройства",
            serialNumberText = "Серийный номер",
            softVerionText =  "Версия ПО",
            allWorkTimeText = "Общее время работы",
            onClick = {}
        )
    }
}

@Composable
fun HeaterOptionsUi() {
    Column(
        modifier = Modifier
            .padding(horizontal = 14.dp)
            .padding(top = 10.dp)
    ) {
        Text(
            text = "Подогреватель",
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(vertical = 8.dp)
        )
        HeaterSettingItem(
            deviceNameText = "Внешнее управление",
            serialNumberText = "Температура",
            softVerionText =  "Формат времени",
            onClick = {}
        )
//        GeneralSettingItem()
    }
}

@Composable
fun RemoteDeviceControlOptionsUi() {
    Column(
        modifier = Modifier
            .padding(horizontal = 14.dp)
            .padding(top = 10.dp)
    ) {
        Text(
            text = "Устройство дистанционного управления",
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(vertical = 8.dp)
        )
        RemoteControlItem(
            onClick = {}
        )
//        GeneralSettingItem()
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GeneralSettingItem(icon: Int, mainText: String, subText: String, onClick: () -> Unit) {
    Card(
        onClick = { onClick() },
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.onPrimary),
        modifier = Modifier
            .padding(bottom = 8.dp)
            .fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
    ) {
        Row(
            modifier = Modifier.padding(vertical = 10.dp, horizontal = 14.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(
                    modifier = Modifier
                        .size(34.dp)
                        .background(Color.Red)
                ) {
                    Icon(
                        painter = painterResource(id = icon),
                        contentDescription = "",
                        tint = Color.Unspecified,
                        modifier = Modifier.padding(8.dp)
                    )
                }

                Spacer(modifier = Modifier.width(14.dp))
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
            Icon(
                painter = painterResource(id = R.drawable.ic_add),
                contentDescription = "",
                modifier = Modifier.size(16.dp)
            )

        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SystemSettingItem(deviceNameText: String, serialNumberText: String, softVerionText: String, allWorkTimeText: String, onClick: () -> Unit) {
    Card(
        onClick = { onClick() },
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.onPrimary),
        modifier = Modifier
            .padding(bottom = 8.dp)
            .fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
    ) {
        Row(
            modifier = Modifier.padding(vertical = 10.dp, horizontal = 4.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Spacer(modifier = Modifier.width(14.dp))
                Column(
                    modifier = Modifier.offset(y = (2).dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = deviceNameText,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold,
                        )
                        Text(
                            text = "1.0",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold,
                        )
                    }
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = serialNumberText,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold,
                        )
                        Text(
                            text = "1.0",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold,
                        )
                    }
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = softVerionText,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold,
                        )
                        Text(
                            text = "1.0",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold,
                        )
                    }
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = allWorkTimeText,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold,
                        )
                        Text(
                            text = "1.0",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold,
                        )
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HeaterSettingItem(deviceNameText: String, serialNumberText: String, softVerionText: String, onClick: () -> Unit) {
    Card(
        onClick = { onClick() },
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.onPrimary),
        modifier = Modifier
            .padding(bottom = 8.dp)
            .fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
    ) {
        Row(
            modifier = Modifier.padding(vertical = 10.dp, horizontal = 4.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Spacer(modifier = Modifier.width(14.dp))
                Column(
                    modifier = Modifier.offset(y = (2).dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = deviceNameText,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold,
                        )
                        Text(
                            text = "1.0",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold,
                        )
                    }
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = serialNumberText,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold,
                        )
                        Text(
                            text = "1.0",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold,
                        )
                    }
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = softVerionText,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold,
                        )
                        Text(
                            text = "1.0",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold,
                        )
                    }
                }
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RemoteControlItem(onClick: () -> Unit) {
    Card(
        onClick = { onClick() },
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.onPrimary),
        modifier = Modifier
            .padding(bottom = 8.dp)
            .fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
    ) {
        Row(
            modifier = Modifier.padding(vertical = 10.dp, horizontal = 14.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Spacer(modifier = Modifier.width(14.dp))
                Column(
                    modifier = Modifier.offset(y = (2).dp)
                ) {
                    Button(onClick = { /*TODO*/ }) {
                        Text(text = "Проверить обновления")
                    }
                }
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SupportItem(icon: Int, mainText: String, onClick: () -> Unit) {
    Card(
        onClick = { onClick() },
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.onPrimary),
        modifier = Modifier
            .padding(bottom = 8.dp)
            .fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
    ) {
        Row(
            modifier = Modifier.padding(vertical = 10.dp, horizontal = 14.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(34.dp)
                        .background(Color.Red)
                ) {
                    Icon(
                        painter = painterResource(id = icon),
                        contentDescription = "",
                        tint = Color.Unspecified,
                        modifier = Modifier.padding(8.dp)
                    )
                }

                Spacer(modifier = Modifier.width(14.dp))

                Text(
                    text = mainText,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                )
            }
            Icon(
                painter = painterResource(id = R.drawable.ic_add),
                contentDescription = "",
                modifier = Modifier.size(16.dp)
            )

        }
    }
}
@PortraitLayouts
@Composable
fun PreviewPortraitAbout(
    @PreviewParameter(PortraitListParams::class) featureParams: FeatureParams
) {
    AdversBleTheme() {
        Settings(appLayoutInfo = featureParams.appLayoutInfo) {
        }
    }
}

@LandscapeLayouts
@Composable
fun PreviewLandscapeAbout(
    @PreviewParameter(LandscapeListParams::class) featureParams: FeatureParams
) {
    AdversBleTheme() {
        Settings(appLayoutInfo = featureParams.appLayoutInfo) {
        }
    }
}