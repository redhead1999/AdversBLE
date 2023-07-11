/**
 * @author Kirilin Yury on 11.07.2023.
 */
object Dependencies {

    object AndroidX {
        const val core = "androidx.core:core-ktx:${Versions.core}"
        const val constraint_layout = "androidx.constraintlayout:constraintlayout:${Versions.constraint_layout}"
        const val recyclerview = "androidx.recyclerview:recyclerview:${Versions.recyclerview}"
    }

    object Google {

    }

    object Charts {

    }

    object Window{
        const val window = "androidx.window:window:${Versions.window}"
        const val window_size = "androidx.compose.material3:material3-window-size-class:${Versions.window_size}"
    }

    object Compose {
        const val material_dialog_datetime_picker = "io.github.vanpra.compose-material-dialogs:datetime:${Versions.material_dialog_datetime_picker}"
        const val compose_ui = "androidx.compose.ui:ui"
        const val compose_ui_graphics = "androidx.compose.ui:ui-graphics"
        const val compose_ui_preview = "androidx.compose.ui:ui-tooling-preview"
        const val compose_ui_tooling = "androidx.compose.ui:ui-tooling"
        const val lifecycle_runtime_compose = "androidx.lifecycle:lifecycle-runtime-compose:${Versions.lifecycle_runtime_compose}"
        const val activity_compose = "androidx.activity:activity-compose:${Versions.activity_compose}"
        const val compose_bom = "androidx.compose:compose-bom:${Versions.activity_compose}"
        const val navigation_compose = "androidx.navigation:navigation-compose:${Versions.navigation_compose}"
        const val color_picker_compose = "com.github.skydoves:colorpicker-compose:${Versions.color_picker_compose}"
    }

    object Firebase{
        const val firebase_bom = "com.google.firebase:firebase-bom:${Versions.firebase_bom}"
        const val firebase_analytics = "com.google.firebase:firebase-analytics-ktx"
        const val firebase_crashlytics = "com.google.firebase:firebase-crashlytics-ktx"
    }

    object Accompanist{
        const val accompanist_permission = "com.google.accompanist:accompanist-permissions:${Versions.accompanist_permission}"
    }

    object Material3{
        const val material3 = "androidx.compose.material3:material3"
    }

    object Kotlin{
        const val serialization = "org.jetbrains.kotlinx:kotlinx-serialization-json:${Versions.serialization}"
        const val work_runtime = "androidx.work:work-runtime-ktx:${Versions.work_runtime}"
        const val lifecycle_runtime = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.lifecycle_runtime}"
    }

    object Leakcanary {
        const val leak_canary = "com.squareup.leakcanary:leakcanary-android:${Versions.leak_canary}"
    }

    object Koin {
        const val koin_android = "io.insert-koin:koin-android:${Versions.koin_android_version}"
        const val koin_android_compose = "io.insert-koin:koin-androidx-compose:${Versions.koin_android_compose_version}"
        const val koin_android_navigation = "io.insert-koin:koin-androidx-navigation:${Versions.koin_android_version}"
        const val koin_android_workmanager = "io.insert-koin:koin-androidx-workmanager:${Versions.koin_android_version}"
        const val koin_android_compat = "io.insert-koin:koin-android-compat:${Versions.koin_android_version}"
        const val koin_android_test = "io.insert-koin:koin-test:${Versions.koin_android_version}"
        const val koin_test_junit5 = "io.insert-koin:koin-test-junit5:${Versions.koin_android_version}"
    }

    object Room {
        const val room_runtime = "androidx.room:room-runtime:${Versions.room}"
        const val room_ktx = "androidx.room:room-ktx:${Versions.room}"
        const val room_compiler = "androidx.room:room-compiler:${Versions.room}"
        const val room_test = "androidx.room:room-testing:${Versions.room_test}"
    }

    object Timber{
        const val timber = "com.jakewharton.timber:timber:${Versions.timber}"
    }

    object Test {
        const val jupiter = "org.junit.jupiter:junit-jupiter-api:${Versions.jupiter}"
        const val jupiter_engine = "org.junit.jupiter:junit-jupiter-engine:${Versions.jupiter_engine}"
        const val kotlinx_coroutines_test = "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.kotlinx_coroutines_test}"
        const val io_mockk = "io.mockk:mockk:${Versions.io_mockk}"
        const val test_core = "androidx.test:core:${Versions.test_core}"
        const val test_runner = "androidx.test:runner:${Versions.test_runner}"
        const val junit5_android_test_core = "de.mannodermaus.junit5:android-test-core:${Versions.junit5_android_test_core}"
        const val junit5_android_test_runner = "de.mannodermaus.junit5:android-test-runner:${Versions.junit5_android_test_runner}"
        const val junit5_platform_suite_engine = "org.junit.platform:junit-platform-suite-engine:${Versions.junit5_platform_suite_engine}"
        const val work_testing = "androidx.work:work-testing:${Versions.work_testing}"
        const val turbine_flow_testing = "app.cash.turbine:turbine:${Versions.turbine_flow_testing}"
        const val snapshot_test = "de.mannodermaus.junit5:android-test-compose:${Versions.snapshot_test}"
        const val compose_ui_test = "androidx.compose.ui:ui-test"
        const val compose_junit4_ui_test = "androidx.compose.ui:ui-test-junit4:${Versions.compose_junit4_ui_test}"
        const val ui_test_manifest = "androidx.compose.ui:ui-test-manifest"
    }

    object Nordic{
        const val nordic = "no.nordicsemi.android:ble:${Versions.nordic}"
    }

}