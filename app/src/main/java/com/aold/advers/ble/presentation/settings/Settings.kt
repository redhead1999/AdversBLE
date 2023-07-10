package com.aold.advers.ble.presentation.settings

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.BorderStroke
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.platform.UriHandler
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
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
import com.aold.advers.ble.presentation.test.components.CircularSlider
import com.aold.advers.ble.presentation.theme.AdversBleTheme
import com.aold.advers.ble.presentation.theme.appBarTitle
import com.aold.advers.ble.presentation.theme.pagerHeaders
import com.aold.advers.ble.utils.windowinfo.AppLayoutInfo
import com.aold.advers.presentation.components.dialog.AgreementAlertDialog
import com.aold.advers.presentation.components.dialog.InfoAlertDialog

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Settings(
    appLayoutInfo: AppLayoutInfo,
    onBackClicked: () -> Unit
) {

    val appSnackBarHostState = remember { SnackbarHostState() }

    val uriHandler = LocalUriHandler.current

    val pagingItems = listOf("Настройки")
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
//                        AboutPager(currentPagingIndex = currentPagingIndex,
//                            pagingItemCount = pagingItemCount, pagingItems = pagingItems, onMove =
//                            {
//                                if (!it) currentPagingIndex-- else currentPagingIndex++
//                            }
//                        )
//
//                        when (currentPagingIndex) {
//                            0 -> AboutAndPrivacy(
//                                uriHandler = uriHandler,
//                                aboutLink = aboutLink,
//                                privacyPolicyLink = privacyPolicy,
//                                termsLink = termsLink
//                            )
//
//                            1 -> HelpCard(
//                                uriHandler = uriHandler,
//                                discussionsLink = discussionsLink,
//                            )
//
//                            else ->
//                                BugCard(uriHandler = uriHandler, bugLink = bugLink)
//                        }
//                    }
                    }
                }

            } else {
//                Row(
//                    modifier = Modifier
//                        .background(MaterialTheme.colorScheme.secondaryContainer)
//                        .fillMaxWidth(),
//                    horizontalArrangement = Arrangement.SpaceBetween,
//                    verticalAlignment = Alignment.CenterVertically
//                ) {
//                    AppInfo()
//                    //SocialIcons(uriHandler, youTubeLink, linkedInLink, gitHubLink)
//                }
//                Spacer(modifier = Modifier.height(20.dp))
//                AboutPager(currentPagingIndex = currentPagingIndex,
//                    pagingItemCount = pagingItemCount, pagingItems = pagingItems, onMove =
//                    {
//                        if (!it) currentPagingIndex-- else currentPagingIndex++
//                    }
//                )

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(rememberScrollState()),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                        SupportOptionsUI()
                        displayVersion()
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
    }

    @Composable
    fun AboutAndPrivacy(
        uriHandler: UriHandler,
        aboutLink: String,
        privacyPolicyLink: String,
        termsLink: String
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
                        Text(text = "Политика конфиденциальности")
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
    fun BugCard(
        uriHandler: UriHandler,
        bugLink: String,
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
                            text = "GitHub Issues",
                            style = pagerHeaders,
                            color = MaterialTheme.colorScheme.onSecondary
                        )
                    }
                    Divider(modifier = Modifier.padding(top = 8.dp))
                    Spacer(modifier = Modifier.height(20.dp))
                    Text(
                        text = "orem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam",
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = "You can also use our GitHub issues " +
                                "to search for existing, work-in-progress bugs and features.",
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
                            uriHandler.openUri(bugLink)
                        }) {
                        Text(text = "Go to GitHub Issues")
                    }
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
    private fun SocialIcons(
        uriHandler: UriHandler,
        youTubeLink: String,
        linkedInLink: String,
        gitHubLink: String
    ) {
        Row() {
            FilledIconButton(
                colors = IconButtonDefaults.filledIconButtonColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                ),
                onClick = { uriHandler.openUri(youTubeLink) },
                content = {
                    Icon(
                        painter = painterResource(id = R.drawable.youtube_logo),
                        contentDescription = "YouTube",
                    )
                })

            FilledIconButton(
                colors = IconButtonDefaults.filledIconButtonColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                ),
                onClick = { uriHandler.openUri(linkedInLink) },
                content = {
                    Icon(
                        painter = painterResource(
                            id = R.drawable.linkedin_logo
                        ),
                        contentDescription = "LinkedIn"
                    )
                })

            FilledIconButton(
                colors = IconButtonDefaults.filledIconButtonColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                ),
                onClick = { uriHandler.openUri(gitHubLink) },
                content = {
                    Icon(
                        painter = painterResource(
                            id = R.drawable.github_logo
                        ),
                        contentDescription = "LinkedIn",
                    )
                })

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

@Composable
fun GeneralOptionsUI() {
    Column(
        modifier = Modifier
            .padding(horizontal = 10.dp)
            .padding(top = 10.dp)
    ) {
        GeneralSettingItem(
            mainText = stringResource(id = R.string.app_setup),
            subText = "",
            onClick = {}
        )
        GeneralSettingItem(
            mainText = stringResource(id = R.string.notifications_setting_string),
            subText = "",
            onClick = {}
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GeneralSettingItem(mainText: String, subText: String, onClick: () -> Unit) {
    Card(
        onClick = {},
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.onPrimary),
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
@Composable
fun SupportOptionsUI() {
    Column(
        modifier = Modifier
            .padding(horizontal = 10.dp)

    ) {
        SettingsItem(
            mainText = stringResource(id = R.string.app_setup),
            onClick = {}
        )

        val showDialog = remember { mutableStateOf(false) }
        if (showDialog.value) {
            InfoAlertDialog(name = "Cправочная информация - это очень ванжая информация." +
                    "Cправочная информация - это очень ванжая информация",
                showDialog = showDialog.value,
                onDismiss = { showDialog.value = false })
        }
        SettingsItem(
            mainText = stringResource(id = R.string.notifications_setting_string),
            onClick = { showDialog.value = true })

        if (showDialog.value) {
           AgreementAlertDialog(name = "Даете ли вы согласие на отправку данных с вашего изделия для его дистанционной диагностики?",
                showDialog = showDialog.value,
                onDismiss = { showDialog.value = false })
        }
        SettingsItem(
            mainText = stringResource(id = R.string.agreement_string),
            onClick = { showDialog.value = true })

        Spacer(modifier = Modifier.height(5.dp))

        val context = LocalContext.current
        val intent = remember {
            Intent(
                Intent.ACTION_VIEW,
                Uri.parse("https://advers.ru/storage/app/media/certificate/privacy_policy.pdf")
            )
        }
    }
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
            .fillMaxHeight()
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