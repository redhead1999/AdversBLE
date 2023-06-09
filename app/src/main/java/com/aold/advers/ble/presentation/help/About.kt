package com.aold.advers.ble.presentation.help

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
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
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
import com.aold.advers.ble.presentation.components.BasicBackTopAppBarWithImage
import com.aold.advers.ble.presentation.previewparams.FeatureParams
import com.aold.advers.ble.presentation.previewparams.LandscapeLayouts
import com.aold.advers.ble.presentation.previewparams.LandscapeListParams
import com.aold.advers.ble.presentation.previewparams.PortraitLayouts
import com.aold.advers.ble.presentation.previewparams.PortraitListParams
import com.aold.advers.ble.presentation.theme.AdversBleTheme
import com.aold.advers.ble.presentation.theme.pagerHeaders
import com.aold.advers.ble.utils.windowinfo.AppLayoutInfo
import com.aold.advers.ble.presentation.components.dialog.AgreementAlertDialog

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AboutScreen(
    appLayoutInfo: AppLayoutInfo,
    onBackClicked: () -> Unit,
) {

    val appSnackBarHostState = remember { SnackbarHostState() }

    val uriHandler = LocalUriHandler.current

    val pagingItems = listOf("Политика", "Согласие на обработку персональных данных", "Поддержка")
    val pagingItemCount by rememberSaveable { mutableStateOf(pagingItems.count()) }
    var currentPagingIndex by rememberSaveable { mutableStateOf(0) }

    Scaffold(
        // modifier = Modifier.border(2.dp, Color.Magenta),
        containerColor = Color.Transparent,
        snackbarHost = { SnackbarHost(hostState = appSnackBarHostState) },
        topBar = {
            if (!appLayoutInfo.appLayoutMode.isLandscape()) {
                BasicBackTopAppBarWithImage(appLayoutInfo = appLayoutInfo, onBackClicked = onBackClicked) {
                    Image(
                        modifier = Modifier.width(120.dp).height(100.dp),
                        painter = painterResource(id = R.drawable.corporation),
                        contentDescription = "Адверс")
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
                        AppInfo()
                    }
                    Column(
                        modifier = Modifier
                            .weight(3f)
                            .padding(horizontal = 16.dp)
                            .verticalScroll(rememberScrollState())
                    ) {
                    }
                }

            } else {
//                Column(
//                    modifier = Modifier
//                        .fillMaxSize(),
//                    verticalArrangement = Arrangement.Center,
//                    horizontalAlignment = Alignment.CenterHorizontally
//                ) {
                var isVisibleSettings by remember {
                    mutableStateOf(false)
                }

                var isVisibleNotifications by remember {
                    mutableStateOf(false)
                }

                var isVisibleAboutApp by remember {
                    mutableStateOf(false)
                }
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(30.dp)
                ) {

                    val isShowing = remember { mutableStateOf(false) }
                    val context = LocalContext.current

                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .verticalScroll(rememberScrollState()),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Top
                    ) {
                        Card(
                            onClick = { isVisibleSettings = !isVisibleSettings },
                            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.secondaryContainer),
                            modifier = Modifier
                                .padding(bottom = 8.dp)
                                .fillMaxWidth(),
                            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
                            shape = RoundedCornerShape(20.dp),
                        ) {
                            Row(
                                modifier = Modifier.padding(vertical = 10.dp, horizontal = 2.dp),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Spacer(modifier = Modifier.width(14.dp))
                                    Row(
                                        Modifier.fillMaxWidth().padding(all = 8.dp),
                                        verticalAlignment = Alignment.CenterVertically,
                                        horizontalArrangement = Arrangement.Start
                                    ) {
                                        Icon(
                                            modifier = Modifier.size(24.dp),
                                            painter = painterResource(id = R.drawable.settings),
                                            contentDescription = null,
                                            tint = Color.White// decorative element
                                        )
                                        Spacer(Modifier.width(20.dp))
                                        Text(text = "Настройки приложения", textAlign = TextAlign.Start)
                                    }
                                }
                                //Switch(checked =true , onCheckedChange = null)
                            }
                        }
                        AnimatedVisibility(isVisibleSettings) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .align(Alignment.CenterHorizontally)
                            ) {
                                val showDialog = remember { mutableStateOf(false) }
                                if (showDialog.value) {
                                    AgreementAlertDialog(name = "Даете ли вы согласие на отправку данных с вашего изделия для его дистанционной диагностики?",
                                        showDialog = showDialog.value,
                                        onDismiss = { showDialog.value = false })
                                }
                                SettingsItem(
                                    mainText = stringResource(id = R.string.agreement_string),
                                    onClick = { showDialog.value = true })
                            }
                        }
                        Card(
                            onClick = { isVisibleNotifications = !isVisibleNotifications },
                            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.secondaryContainer),
                            modifier = Modifier
                                .padding(bottom = 8.dp)
                                .fillMaxWidth(),
                            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
                            shape = RoundedCornerShape(20.dp),
                        ) {
                            Row(
                                modifier = Modifier.padding(vertical = 10.dp, horizontal = 2.dp),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Spacer(modifier = Modifier.width(14.dp))
                                    Row(
                                        Modifier.fillMaxWidth().padding(all = 8.dp),
                                        verticalAlignment = Alignment.CenterVertically,
                                        horizontalArrangement = Arrangement.Start
                                    ) {
                                            Icon(
                                                modifier = Modifier.size(24.dp),
                                                painter = painterResource(id = R.drawable.bell),
                                            contentDescription = null,
                                            tint = Color.White// decorative element
                                        )
                                        Spacer(Modifier.width(20.dp))
                                        Text(text = "Уведомления")
                                    }
                                }
                                //Switch(checked =true , onCheckedChange = null)
                            }
                        }
                        AnimatedVisibility(isVisibleNotifications) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .align(Alignment.CenterHorizontally)
                            ) {
//                                NotificationItem(
//                                    icon = R.drawable.about_icon,
//                                    mainText = "test",
//                                    subText = "2"
//                                )
                                NotificationList()
                            }
                        }
                        Card(
                            onClick = { isVisibleAboutApp = !isVisibleAboutApp },
                            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.secondaryContainer),
                            modifier = Modifier
                                .padding(bottom = 8.dp)
                                .fillMaxWidth(),
                            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
                            shape = RoundedCornerShape(20.dp),
                        ) {
                            Row(
                                modifier = Modifier.padding(vertical = 10.dp, horizontal = 2.dp),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                    Spacer(modifier = Modifier.width(14.dp))
                                    Row(
                                        Modifier.fillMaxWidth().padding(all = 8.dp),
                                        verticalAlignment = Alignment.CenterVertically,
                                        horizontalArrangement = Arrangement.Start
                                    ) {
                                        Icon(
                                            modifier = Modifier.size(24.dp),
                                            painter = painterResource(id = R.drawable.info),
                                            contentDescription = null,
                                            tint = Color.White// decorative element
                                        )
                                        Spacer(Modifier.width(20.dp))
                                        Text(text = "О приложении", textAlign = TextAlign.Center)
                                }
                                //Switch(checked =true , onCheckedChange = null)
                            }

                        }
                        AnimatedVisibility(isVisibleAboutApp) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .align(Alignment.CenterHorizontally)
                            ) {
                                displayVersion()
                            }
                        }
//                        GeneralOptionsUI()
//                        SupportOptionsUI()
//                        AnimatedVisibility(isVisibleAboutApp) {
//                        displayVersion()
                    }
                }
            }
        }
    }
}

@Composable
fun AboutPager(
    currentPagingIndex: Int,
    pagingItemCount: Int,
    pagingItems: List<String>,
    onMove: (Boolean) -> Unit,
) {

    Row(
        modifier = Modifier.fillMaxWidth(),
        //.background(Color.White.copy(.3f)),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        IconButton(
            colors = IconButtonDefaults.iconButtonColors(
                contentColor = MaterialTheme.colorScheme.onPrimaryContainer,
                disabledContentColor = MaterialTheme.colorScheme.onPrimaryContainer.copy(.3f)
            ),
            enabled = (currentPagingIndex > 0),
            onClick = {
                onMove(false)
            }
        ) {
            Icon(
                //modifier = Modifier.align(Alignment.Top),
                imageVector = Icons.Default.KeyboardArrowLeft,
                contentDescription = "Next",
            )
        }
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = pagingItems[currentPagingIndex],
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.onSurface
            )
        }

        IconButton(
            colors = IconButtonDefaults.iconButtonColors(
                contentColor = MaterialTheme.colorScheme.onPrimaryContainer,
                disabledContentColor = MaterialTheme.colorScheme.onPrimaryContainer.copy(.3f)
            ),
            enabled = (currentPagingIndex != (pagingItemCount - 1)),
            onClick = {
                onMove(true)
            }
        ) {
            Icon(
                //modifier = Modifier.align(Alignment.Top),
                imageVector = Icons.Default.KeyboardArrowRight,
                contentDescription = "Next Service",
            )
        }
    }


}

@Composable
fun AboutAndPrivacy(
    uriHandler: UriHandler,
    aboutLink: String,
    privacyPolicyLink: String,
    termsLink: String,
) {
    Column(
        modifier = Modifier.fillMaxSize(),
    ) {

        OutlinedCard(
            modifier = Modifier.padding(16.dp)
        ) {
            Column(
                modifier = Modifier.padding(12.dp),
            ) {

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        modifier = Modifier.size(36.dp),
                        imageVector = Icons.Default.Info,
                        contentDescription = "About Icon",
                        tint = MaterialTheme.colorScheme.onSecondary
                    )
                    Text(
                        modifier = Modifier.padding(start = 6.dp),
                        text = "Learn More",
                        style = pagerHeaders,
                        color = MaterialTheme.colorScheme.onSecondary
                    )
                }
                Divider(modifier = Modifier.padding(top = 8.dp))
                Spacer(modifier = Modifier.height(20.dp))

                Text(
                    text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam," +
                            " quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum " +
                            "dolore eu fugiat nulla pariatur. " +
                            "Excepteur sint occaecat cupidatat non proident, " +
                            "sunt in culpa qui officia deserunt mollit anim id est laborum. "
                )
                Spacer(modifier = Modifier.height(10.dp))
                LegalStuff(
                    privacyPolicyLink = privacyPolicyLink,
                    termsLink = termsLink,
                    uriHandler = uriHandler
                )
                Spacer(modifier = Modifier.height(30.dp))

                val buttonTextColor = if (isSystemInDarkTheme())
                    MaterialTheme.colorScheme.surface
                else
                    MaterialTheme.colorScheme.onPrimary

                Button(
                    colors = ButtonDefaults.buttonColors(
                        contentColor = MaterialTheme.colorScheme.onPrimary
                    ),
                    modifier = Modifier.fillMaxWidth(),
                    onClick = {
                        uriHandler.openUri(aboutLink)
                    }) {
                    Text(text = "Политика")
                }

            }
        }
    }
}


@Composable
fun HelpCard(
    uriHandler: UriHandler,
    discussionsLink: String,
) {
    Column(
        modifier = Modifier.fillMaxSize(),
    ) {

        OutlinedCard(
            modifier = Modifier.padding(16.dp)
        ) {
            Column(
                modifier = Modifier.padding(12.dp),
            ) {

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        modifier = Modifier.size(36.dp),
                        painter = painterResource(id = R.drawable.github_logo),
                        contentDescription = "GitHub Icon",
                        tint = MaterialTheme.colorScheme.onSecondary
                    )
                    Text(
                        modifier = Modifier.padding(start = 6.dp),
                        text = "GitHub Discussions",
                        style = pagerHeaders,
                        color = MaterialTheme.colorScheme.onSecondary
                    )
                }
                Divider(modifier = Modifier.padding(top = 8.dp))
                Spacer(modifier = Modifier.height(20.dp))
                Text(
                    text = "orem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam",
                )

                Spacer(modifier = Modifier.height(30.dp))

                val buttonTextColor = if (isSystemInDarkTheme())
                    MaterialTheme.colorScheme.surface
                else
                    MaterialTheme.colorScheme.onPrimary

                Button(
                    colors = ButtonDefaults.buttonColors(
                        contentColor = MaterialTheme.colorScheme.onPrimary
                    ),
                    modifier = Modifier.fillMaxWidth(),
                    onClick = {
                        uriHandler.openUri(discussionsLink)
                    }) {
                    Text(text = "Go to Discussions")
                }

            }
        }
    }
}


@Composable
private fun AppInfo() {
    Row(
        modifier = Modifier.padding(top = 6.dp, bottom = 6.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            modifier = Modifier
                .size(76.dp)
                .padding(4.dp)
                .offset(y = 3.dp),
            painter = painterResource(id = R.drawable.corporation),
            contentDescription = "App Logo"
        )

//        Column(
//            modifier = Modifier.padding(start = 6.dp)
//        ) {
//            Text(
//                text = "Адверс",
//                style = MaterialTheme.typography.titleMedium,
//                color = MaterialTheme.colorScheme.onSecondaryContainer
//            )
//            Text(
//                text = "Самара",
//                style = MaterialTheme.typography.titleSmall,
//                color = MaterialTheme.colorScheme.onSecondaryContainer
//            )
//            Text(
//                text = "Версия: ${BuildConfig.VERSION_NAME}",
//                style = MaterialTheme.typography.bodyMedium,
//                color = MaterialTheme.colorScheme.onSecondaryContainer
//            )
//        }
    }
}

@Composable
fun LegalStuff(
    privacyPolicyLink: String,
    termsLink: String,
    uriHandler: UriHandler,
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsItemWithSwitcher(mainText: String, onClick: () -> Unit) {
    Card(
        onClick = { onClick() },
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.onPrimary),
        modifier = Modifier
            .padding(bottom = 8.dp)
            .fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
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
            Switch(checked = true, onCheckedChange = null)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsItem(mainText: String, onClick: () -> Unit) {
    Card(
        onClick = { onClick() },
        colors = CardDefaults.cardColors(containerColor = Color.Transparent),
        modifier = Modifier
            .padding(bottom = 8.dp)
            .fillMaxWidth(),
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
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 1.dp)
            .height(50.dp)
            .background(Color.Transparent),
        contentAlignment = Alignment.Center
    )
    {

        val version =
            "Версия: " + BuildConfig.VERSION_NAME + "\n" + "Cборка : " + BuildConfig.VERSION_CODE.toString()

        Text(
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.primary,
            text = version,
            modifier = Modifier.padding(5.dp),
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun NotificationList() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp),
    ) {

        NotificationItem(
            icon = com.aold.advers.R.drawable.ic_launcher,
            mainText = "Вы скачали наше приложение!",
            subText = "Если у вас есть вопросы или предложения по приложению, вы можете связаться с нами по адресу info@dd-inform.com"
        )
        NotificationItem(
            icon = com.aold.advers.R.drawable.ic_launcher,
            mainText = "Вы скачали наше приложение!",
            subText = "Если у вас есть вопросы или предложения по приложению, вы можете связаться с нами по адресу info@dd-inform.com"
        )
        NotificationItem(
            icon = com.aold.advers.R.drawable.ic_launcher,
            mainText = "Вы скачали наше приложение!",
            subText = "Если у вас есть вопросы или предложения по приложению, вы можете связаться с нами по адресу info@dd-inform.com"
        )
        NotificationItem(
            icon = com.aold.advers.R.drawable.ic_launcher,
            mainText = "Вы скачали наше приложение!",
            subText = "Если у вас есть вопросы или предложения по приложению, вы можете связаться с нами по адресу info@dd-inform.com"
        )
    }

    Text(
        text = "Сегодня",
        fontWeight = FontWeight.SemiBold,
        fontSize = 14.sp,
        modifier = Modifier.padding(top = 20.dp)
    )

    NotificationItem(
        icon = com.aold.advers.R.drawable.ic_launcher,
        mainText = "Вы скачали наше приложение!",
        subText = "Если у вас есть вопросы или предложения по приложению, вы можете связаться с нами по адресу info@dd-inform.com"
    )
    NotificationItem(
        icon = com.aold.advers.R.drawable.ic_launcher,
        mainText = "Вы скачали наше приложение!",
        subText = "Если у вас есть вопросы или предложения по приложению, вы можете связаться с нами по адресу info@dd-inform.com"
    )
    NotificationItem(
        icon = com.aold.advers.R.drawable.ic_launcher,
        mainText = "Вы скачали наше приложение!",
        subText = "Если у вас есть вопросы или предложения по приложению, вы можете связаться с нами по адресу info@dd-inform.com"
    )
    NotificationItem(
        icon = com.aold.advers.R.drawable.ic_launcher,
        mainText = "Вы скачали наше приложение!",
        subText = "Если у вас есть вопросы или предложения по приложению, вы можете связаться с нами по адресу info@dd-inform.com"
    )

    Text(
        text = "Mon, 22 jan",
        fontWeight = FontWeight.SemiBold,
        fontSize = 14.sp,
        modifier = Modifier.padding(top = 20.dp)
    )

    NotificationItem(
        icon = com.aold.advers.R.drawable.ic_launcher,
        mainText = "Вы скачали наше приложение!",
        subText = "Если у вас есть вопросы или предложения по приложению, вы можете связаться с нами по адресу info@dd-inform.com"
    )
    NotificationItem(
        icon = com.aold.advers.R.drawable.ic_launcher,
        mainText = "Вы скачали наше приложение!",
        subText = "Если у вас есть вопросы или предложения по приложению, вы можете связаться с нами по адресу info@dd-inform.com"
    )
    NotificationItem(
        icon = com.aold.advers.R.drawable.ic_launcher,
        mainText = "Вы скачали наше приложение!",
        subText = "Если у вас есть вопросы или предложения по приложению, вы можете связаться с нами по адресу info@dd-inform.com"
    )
    NotificationItem(
        icon = com.aold.advers.R.drawable.ic_launcher,
        mainText = "Вы скачали наше приложение!",
        subText = "Если у вас есть вопросы или предложения по приложению, вы можете связаться с нами по адресу info@dd-inform.com"
    )
}


@Composable
fun NotificationItem(icon: Int, mainText: String, subText: String) {
    TextButton(
        onClick = {},
        modifier = Modifier.fillMaxWidth(),
        contentPadding = PaddingValues(0.dp),
    ) {
        Row(
            modifier = Modifier
                .padding(vertical = 10.dp)
                .fillMaxWidth()
                .height(80.dp)
//            .border(width = 1.dp, shape = RectangleShape, color = IconColor)
            ,
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row() {
                Icon(
                    painter = painterResource(id = icon), contentDescription = "",
                    modifier = Modifier.size(30.dp),
                    tint = Color.Black
                )
                Column(
                    modifier = Modifier.padding(start = 10.dp)
                ) {
                    Text(
                        text = mainText,
                        fontWeight = FontWeight.SemiBold,
                        lineHeight = 10.sp,
                        fontSize = 12.sp,
                        letterSpacing = 0.sp
                    )
                    Text(
                        text = subText,
                        fontWeight = FontWeight.Medium,
                        lineHeight = 10.sp,
                        fontSize = 11.sp,
                        letterSpacing = 0.sp,
                        modifier = Modifier
                            .offset(y = (-4).dp)
                    )
                }
            }
        }
    }
}

@PortraitLayouts
@Composable
fun PreviewPortraitAbout(
    @PreviewParameter(PortraitListParams::class) featureParams: FeatureParams,
) {
    AdversBleTheme() {
        AboutScreen(appLayoutInfo = featureParams.appLayoutInfo) {

        }
    }
}

@LandscapeLayouts
@Composable
fun PreviewLandscapeAbout(
    @PreviewParameter(LandscapeListParams::class) featureParams: FeatureParams,
) {
    AdversBleTheme() {
        AboutScreen(appLayoutInfo = featureParams.appLayoutInfo) {

        }
    }
}