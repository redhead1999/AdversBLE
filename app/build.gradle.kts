import java.io.FileInputStream
import java.util.Properties

plugins {
//    kotlin("multiplatform")
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-parcelize")
    kotlin("kapt")
    id ("org.jetbrains.kotlin.plugin.serialization")
    id ("de.mannodermaus.android-junit5")
    id ("com.google.gms.google-services")
    id ("com.google.firebase.crashlytics")
}

android {
    signingConfigs {
        register("release") {

            val keystorePropertiesFile = file("../key.properties")

            if (!keystorePropertiesFile.exists()) {
                logger.warn("Release builds may not work: signing config not found.")
                return@register
            }

            val keystoreProperties = Properties()
            keystoreProperties.load(FileInputStream(keystorePropertiesFile))

            keyAlias = keystoreProperties["keyAlias"] as String
            keyPassword = keystoreProperties["keyPassword"] as String
            storeFile = file(keystoreProperties["storeFile"] as String)
            storePassword = keystoreProperties["storePassword"] as String
        }
    }
    //configurations { implementation.get().exclude(mapOf("group" to "org.jetbrains", "module" to "annotations")) }
    namespace = Config.applicationId
    compileSdk = Config.compileSdkVersion

    defaultConfig {
        applicationId = Config.applicationId
        minSdk = Config.minSdkVersion
        targetSdk = Config.targetSdkVersion
        versionCode = Config.versionCode
        versionName = Config.versionName

        testInstrumentationRunner = Config.testInstrumentRunner
        testInstrumentationRunnerArgument(
            "runnerBuilder",
            "de.mannodermaus.junit5.AndroidJUnit5Builder"
        )

        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            signingConfig = signingConfigs.getByName("release")
            isDebuggable = false
        }
        getByName("debug") {
            signingConfig = signingConfigs.getByName("debug")
            isDebuggable = true
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = Config.jvmTarget
    }

    buildFeatures {
        buildConfig = true
        compose = true
        viewBinding = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = Config.kotlinCompiler
    }
    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }

    tasks.withType<Test> {
        useJUnitPlatform()
    }

//    sourceSets.getByName("main") {
//            assets {
//                srcDirs 'src\\main\\assets', 'src\\main\\assets\\fonts'
//            }
//        }
//    }

//TODO KMM
//kotlin{
//    android()
//    ios()
//    sourceSets{
//        val commonMain by getting{
//            dependencies{}
//        }
//        val commonTest by getting {
//            dependencies{}
//        }
//        val androidMain by getting {
//            dependencies {}
//        }
//        val androidUnitTest by getting {
//            dependencies{}
//        }
//        val iosMain by getting {
//            dependencies{}
//        }
//        val iosTest by getting
//    }
//}

    dependencies {

        implementation(Dependencies.AndroidX.core)

        //Modules
        implementation(project(":bluetooth"))
        androidTestImplementation (project (":sharedTest"))

        //Room
        implementation(Dependencies.Room.room_runtime)
        implementation(Dependencies.Room.room_ktx)
        kapt(Dependencies.Room.room_compiler)
        kapt(Dependencies.Room.room_test)
        androidTestImplementation(Dependencies.Room.room_test)
        kaptAndroidTest(Dependencies.Room.room_compiler)

        implementation(Dependencies.Koin.koin_android)
        implementation(Dependencies.Koin.koin_android_compat)
        implementation(Dependencies.Koin.koin_android_workmanager)
        implementation(Dependencies.Koin.koin_android_navigation)
        implementation(Dependencies.Koin.koin_android_compose)
        androidTestImplementation(Dependencies.Koin.koin_android_test)
        androidTestImplementation(Dependencies.Koin.koin_test_junit5)

        implementation(Dependencies.Timber.timber)

        //Test
        androidTestImplementation(Dependencies.Test.jupiter)
        testRuntimeOnly(Dependencies.Test.jupiter_engine)
        testImplementation(Dependencies.Test.kotlinx_coroutines_test)
        androidTestImplementation(Dependencies.Test.io_mockk)
        androidTestImplementation(Dependencies.Test.junit5_android_test_core)

        androidTestRuntimeOnly(Dependencies.Test.junit5_android_test_runner)

        androidTestImplementation (Dependencies.Test.junit5_platform_suite_engine)
        androidTestImplementation(Dependencies.Test.turbine_flow_testing)
        androidTestImplementation(Dependencies.Test.work_testing)

        // Compose testing dependencies
        androidTestImplementation(Dependencies.Test.compose_ui_test)
        androidTestImplementation(Dependencies.Test.compose_junit4_ui_test)
        debugImplementation(Dependencies.Test.ui_test_manifest)
        androidTestImplementation(Dependencies.Test.snapshot_test)

        implementation(Dependencies.Kotlin.serialization)
        implementation(Dependencies.Kotlin.work_runtime)
        implementation(Dependencies.Kotlin.lifecycle_runtime)

        //Compose
        implementation(Dependencies.Compose.activity_compose)
        implementation(platform(Dependencies.Compose.compose_bom))
        implementation(Dependencies.Compose.compose_ui)
        implementation(Dependencies.Compose.compose_ui_graphics)
        implementation(Dependencies.Compose.compose_ui_preview)
        implementation(Dependencies.Material3.material3)
        implementation(Dependencies.Compose.lifecycle_runtime_compose)
        implementation(Dependencies.Compose.material_dialog_datetime_picker)
        implementation(Dependencies.Compose.color_picker_compose)

        implementation(Dependencies.Compose.navigation_compose)
        implementation(Dependencies.AndroidX.constraint_layout)
        implementation(Dependencies.AndroidX.recyclerview)

        implementation(Dependencies.Accompanist.accompanist_permission)
        debugImplementation(Dependencies.Compose.compose_ui_tooling)

        // Window layout
        implementation(Dependencies.Window.window)
        implementation(Dependencies.Window.window_size)

        // Firebase
        implementation(platform(Dependencies.Firebase.firebase_bom))
        implementation(Dependencies.Firebase.firebase_analytics)
        implementation(Dependencies.Firebase.firebase_crashlytics)

        //LeakCanary
        debugImplementation(Dependencies.Leakcanary.leak_canary)

        //NordicLib
        implementation(Dependencies.Nordic.nordic)

        implementation("com.github.commandiron:WheelPickerCompose:1.1.11")

        //Old but gold
        implementation("com.github.PhilJay:MPAndroidChart:v3.1.0")
        implementation("me.tankery.lib:circularSeekBar:1.4.2")



    }
}
dependencies {
    implementation("androidx.test:runner:1.5.2")
    implementation("com.google.android.material:material:1.9.0")
}


