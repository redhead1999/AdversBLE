object Dependencies {

    object AndroidX {
        const val core = "androidx.core:core-ktx:${Versions.core}"
        const val appcompat = "androidx.appcompat:appcompat:${Versions.appcompat}"
        const val material = "androidx.compose.material3:material3:${Versions.material}"
        const val googleMaterial = "com.google.android.material:material:${Versions.googleMaterial}"
        const val systemUiController = "com.google.accompanist:accompanist-systemuicontroller:${Versions.accompanist}"
        const val lifecycleViewModel = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycle}"
        const val lifecycleRuntime = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.lifecycle}"
    }

    object Google {
        const val gson = "com.google.code.gson:gson:${Versions.gson}"
    }

    object Charts {
        val library = "ma.hu:compose-charts:${Versions.charts}"
        val libraryHimanshoe = "com.himanshoe:charty:${Versions.chartsHm}"
    }

    object Compose {
        const val ui = "androidx.compose.ui:ui:${Versions.compose}"
        const val foundation = "androidx.compose.foundation:foundation:${Versions.compose}"
        const val layout = "androidx.compose.foundation:foundation-layout:${Versions.compose}"
        const val activity = "androidx.activity:activity-compose:${Versions.activityCompose}"
        const val preview = "androidx.compose.ui:ui-tooling-preview:${Versions.compose}"
        const val uiTooling = "androidx.compose.ui:ui-tooling:${Versions.compose}"
        const val uiTestManifest = "androidx.compose.ui:ui-test-manifest:${Versions.compose}"
    }


    object Leakcanary {
        const val library = "com.squareup.leakcanary:leakcanary-android:${Versions.leakcanary}"
    }


    //check
    object Room {
        const val core = "androidx.room:room-runtime:${Versions.room}"
        const val ktx = "androidx.room:room-ktx:${Versions.room}"
        const val kapt = "androidx.room:room-compiler:${Versions.room}"
    }

    object Test {
        const val jUnit = "junit:junit:${Versions.jUnit}"
        const val composeJUnit = "androidx.compose.ui:ui-test-junit4:${Versions.compose}"
        const val jUnitExt = "androidx.test.ext:junit:${Versions.jUnitExt}"
        const val coroutinesTest = "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.coroutinesTest}"
        const val turbine = "app.cash.turbine:turbine:${Versions.turbine}"
        const val espresso = "androidx.test.espresso:espresso-core:${Versions.espresso}"
    }
}