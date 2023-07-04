package com.aold.advers.ble.presentation.new.help

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.platform.UriHandler
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.aold.advers.BuildConfig
import com.aold.advers.R
import com.aold.advers.ble.presentation.new.components.BasicBackTopAppBar
import com.aold.advers.ble.presentation.new.components.aboutLink
import com.aold.advers.ble.presentation.new.components.bugLink
import com.aold.advers.ble.presentation.new.components.discussionsLink
import com.aold.advers.ble.presentation.new.components.gitHubLink
import com.aold.advers.ble.presentation.new.components.linkedInLink
import com.aold.advers.ble.presentation.new.components.privacyPolicy
import com.aold.advers.ble.presentation.new.components.termsLink
import com.aold.advers.ble.presentation.new.components.youTubeLink
import com.aold.advers.ble.presentation.new.previewparams.FeatureParams
import com.aold.advers.ble.presentation.new.previewparams.LandscapeLayouts
import com.aold.advers.ble.presentation.new.previewparams.LandscapeListParams
import com.aold.advers.ble.presentation.new.previewparams.PortraitLayouts
import com.aold.advers.ble.presentation.new.previewparams.PortraitListParams
import com.aold.advers.ble.presentation.new.theme.SanTanScanTheme
import com.aold.advers.ble.presentation.new.theme.appBarTitle
import com.aold.advers.ble.presentation.new.theme.pagerHeaders
import com.aold.advers.ble.utils.windowinfo.AppLayoutInfo

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AboutScreen(
    appLayoutInfo: AppLayoutInfo,
    onBackClicked: () -> Unit
) {

    val appSnackBarHostState = remember { SnackbarHostState() }

    val uriHandler = LocalUriHandler.current

    val pagingItems = listOf("About, Terms, Privacy", "Questions & Help", "Report a Bug")
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
                        text = "SanTanScan",
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
                                SocialIcons(
                                    uriHandler, youTubeLink,
                                    linkedInLink, gitHubLink
                                )
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
                        AboutPager(currentPagingIndex = currentPagingIndex,
                            pagingItemCount = pagingItemCount, pagingItems = pagingItems, onMove =
                            {
                                if (!it) currentPagingIndex-- else currentPagingIndex++
                            }
                        )

                        when (currentPagingIndex) {
                            0 -> AboutAndPrivacy(
                                uriHandler = uriHandler,
                                aboutLink = aboutLink,
                                privacyPolicyLink = privacyPolicy,
                                termsLink = termsLink
                            )

                            1 -> HelpCard(
                                uriHandler = uriHandler,
                                discussionsLink = discussionsLink,
                            )

                            else ->
                                BugCard(uriHandler = uriHandler, bugLink = bugLink)
                        }
                    }
                }

            } else {
                Row(
                    modifier = Modifier
                        .background(MaterialTheme.colorScheme.secondaryContainer)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    AppInfo()
                    SocialIcons(uriHandler, youTubeLink, linkedInLink, gitHubLink)
                }
                Spacer(modifier = Modifier.height(20.dp))
                AboutPager(currentPagingIndex = currentPagingIndex,
                    pagingItemCount = pagingItemCount, pagingItems = pagingItems, onMove =
                    {
                        if (!it) currentPagingIndex-- else currentPagingIndex++
                    }
                )

                Column(
                    modifier = Modifier.verticalScroll(rememberScrollState())
                ) {
                    when (currentPagingIndex) {
                        0 -> AboutAndPrivacy(
                            uriHandler = uriHandler,
                            aboutLink = aboutLink,
                            privacyPolicyLink = privacyPolicy,
                            termsLink = termsLink
                        )

                        1 -> HelpCard(
                            uriHandler = uriHandler,
                            discussionsLink = discussionsLink,
                        )

                        else ->
                            BugCard(uriHandler = uriHandler, bugLink = bugLink)
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
                    text = "SanTanScan is a Bluetooth Low Energy (BLE) scanner and debugger " +
                            "available for Android 9+. Features include BLE scanning, sorting, " +
                            "reading and writing to devices, and more. "
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
                    Text(text = "About this App")
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
                    text = "Use our GitHub Discussions to connect with other users," +
                            "request new features, provide feedback, and ask for help.",
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
                    text = "To report a bug, create a GitHub issue. Be sure to include all " +
                            "relevant information, including the type of BLE device you're " +
                            "trying to communicate with.",
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
            painter = painterResource(id = R.drawable.santanscan_logo_print),
            contentDescription = "App Logo"
        )

        Column(
            modifier = Modifier.padding(start = 6.dp)
        ) {
            Text(
                text = "Sarah Brenner",
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onSecondaryContainer
            )
            Text(
                text = "Phoenix, AZ",
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.onSecondaryContainer
            )
            Text(
                text = "Version: ${BuildConfig.VERSION_NAME}",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSecondaryContainer
            )
        }
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
        withStyle(style = SpanStyle(
            fontSize = 12.sp,
            color = MaterialTheme.colorScheme.onSurface)
        ) {
            append("Note: By using SanTanScan, you agree to the ")
        }

        pushStringAnnotation(tag = "policy", annotation = privacyPolicyLink)
        withStyle(style = SpanStyle(
            fontSize = 12.sp,
            color = MaterialTheme.colorScheme.primary)) {
            append("Privacy Policy")
        }
        pop()

        withStyle(style = SpanStyle(
            fontSize = 12.sp,
            color = MaterialTheme.colorScheme.onSurface)) {
            append(" and ")
        }
        pushStringAnnotation(tag = "terms", annotation = termsLink)

        withStyle(style = SpanStyle(
            fontSize = 12.sp,
            color = MaterialTheme.colorScheme.primary)) {
            append("Terms of Service.")
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

@PortraitLayouts
@Composable
fun PreviewPortraitAbout(
    @PreviewParameter(PortraitListParams::class) featureParams: FeatureParams
) {
    SanTanScanTheme() {
        AboutScreen(appLayoutInfo = featureParams.appLayoutInfo) {

        }
    }
}

@LandscapeLayouts
@Composable
fun PreviewLandscapeAbout(
    @PreviewParameter(LandscapeListParams::class) featureParams: FeatureParams
) {
    SanTanScanTheme() {
        AboutScreen(appLayoutInfo = featureParams.appLayoutInfo) {

        }
    }
}