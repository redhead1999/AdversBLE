import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties
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

repositories {
    mavenCentral()
    google()
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
    compileSdk =  33

    defaultConfig {
        applicationId = Config.applicationId
        minSdk = Config.minSdkVersion
        targetSdk = Config.targetSdkVersion
        versionCode = Config.versionCode
        versionName = Config.versionName
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        testInstrumentationRunnerArgument("runnerBuilder", "de.mannodermaus.junit5.AndroidJUnit5Builder")

        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
            signingConfig = signingConfigs.getByName("release")
            isDebuggable = false
        }
        getByName("debug") {
            signingConfig = signingConfigs.getByName("debug")
            isDebuggable = true
        }
    }
    compileOptions {
        sourceCompatibility =  JavaVersion.VERSION_11
        targetCompatibility =  JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = Config.jvmTarget
    }

    buildFeatures {
        buildConfig = true
        compose = true
        viewBinding =  true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = Config.kotlinCompiler
    }
    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }

//    testOptions {
//        unitTests.all {
//            useJUnitPlatform()
//        }
//        packagingOptions {
//            jniLibs {
//                // https://github.com/mockk/mockk/issues/297#issuecomment-901924678
//                useLegacyPackaging = true
//            }
//        }
//    }

//    sourceSets.getByName("main") {
//            assets {
//                srcDirs 'src\\main\\assets', 'src\\main\\assets\\fonts'
//            }
//        }
    }

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

    implementation ("androidx.core:core-ktx:1.9.0")
    implementation ("androidx.lifecycle:lifecycle-runtime-ktx:2.3.1")
    implementation ("androidx.activity:activity-compose:1.5.1")
    implementation (platform("androidx.compose:compose-bom:2022.10.00"))
    implementation ("androidx.compose.ui:ui")
    implementation ("androidx.compose.ui:ui-graphics")
    implementation ("androidx.compose.ui:ui-tooling-preview")
    implementation ("androidx.compose.material3:material3")
    implementation ("androidx.lifecycle:lifecycle-runtime-compose:2.6.0-alpha05")
    implementation ("androidx.navigation:navigation-compose:2.5.3")
    implementation ("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation ("androidx.recyclerview:recyclerview:1.3.0")

    debugImplementation ("androidx.compose.ui:ui-tooling")
    debugImplementation ("androidx.compose.ui:ui-test-manifest")

    implementation ("com.google.accompanist:accompanist-permissions:0.29.1-alpha")

    implementation ("com.jakewharton.timber:timber:5.0.1")
    implementation ("org.jetbrains.kotlinx:kotlinx-serialization-json:1.4.1")

    val room_version = "2.5.0"

    implementation ("androidx.room:room-runtime:$room_version")
    implementation ("androidx.room:room-ktx:$room_version")
    annotationProcessor ("androidx.room:room-compiler:$room_version")
    kapt ("androidx.room:room-compiler:$room_version")

    val koin_android_version= ("3.3.2")
    val koin_android_compose_version= ("3.4.1")

    implementation ("io.insert-koin:koin-android:$koin_android_version")
    implementation ("io.insert-koin:koin-android-compat:$koin_android_version")
    implementation ("io.insert-koin:koin-androidx-workmanager:$koin_android_version")
    implementation ("io.insert-koin:koin-androidx-navigation:$koin_android_version")
    implementation ("io.insert-koin:koin-androidx-compose:$koin_android_compose_version")

    implementation ("androidx.work:work-runtime-ktx:2.8.1")
    implementation ("io.insert-koin:koin-androidx-workmanager:3.3.3")

    implementation ("com.github.skydoves:colorpicker-compose:1.0.1")

    // window layout
    implementation ("androidx.window:window:1.1.0-alpha04")
    implementation ("androidx.compose.material3:material3-window-size-class:1.1.0-alpha06")

    // firebase
    implementation(platform("com.google.firebase:firebase-bom:28.4.1"))
    implementation("com.google.firebase:firebase-analytics-ktx")
    implementation("com.google.firebase:firebase-crashlytics-ktx")


    //testing
    testImplementation ("io.insert-koin:koin-test:$koin_android_version")
    testImplementation ("io.insert-koin:koin-test-junit5:$koin_android_version")

    testImplementation ("androidx.room:room-testing:$room_version")
    testImplementation ("org.junit.jupiter:junit-jupiter-api:5.8.2")
    testRuntimeOnly ("org.junit.jupiter:junit-jupiter-engine:5.8.2")
    testImplementation ("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.6.4")
    testImplementation ("io.mockk:mockk:1.13.3")

    //TODO ВЕРНУТЬ МОДУЛЬ ТЕСТОВ
    //androidTestImplementation project(":sharedTest")

    androidTestImplementation ("androidx.room:room-testing:$room_version")
    kaptAndroidTest ("androidx.room:room-compiler:$room_version")

    androidTestImplementation ("io.insert-koin:koin-test:$koin_android_version")
    androidTestImplementation ("io.insert-koin:koin-test-junit5:$koin_android_version")
    androidTestImplementation ("androidx.test:core:1.5.0")
    androidTestImplementation ("androidx.test:runner:1.5.2")
    androidTestImplementation ("org.junit.jupiter:junit-jupiter-api:5.8.2")
    androidTestImplementation ("de.mannodermaus.junit5:android-test-core:1.3.0")
    androidTestRuntimeOnly ("de.mannodermaus.junit5:android-test-runner:1.3.0")
    androidTestImplementation ("org.junit.platform:junit-platform-suite-engine:1.9.2")
    androidTestImplementation ("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.6.4")
    androidTestImplementation ("io.mockk:mockk-android:1.13.3")
    androidTestImplementation ("app.cash.turbine:turbine:0.12.1")
    androidTestImplementation("androidx.work:work-testing:2.8.0")

    // Compose testing dependencies
    androidTestImplementation ("androidx.compose.ui:ui-test")
    androidTestImplementation ("androidx.compose.ui:ui-test-junit4:1.4.0-beta02")
    debugImplementation ("androidx.compose.ui:ui-test-manifest")

    androidTestImplementation ("de.mannodermaus.junit5:android-test-compose:0.1.0-SNAPSHOT")


    //LeakCanary
//    debugImplementation 'com.squareup.leakcanary:leakcanary-android:{}'



    implementation ("com.github.PhilJay:MPAndroidChart:v3.1.0")
    implementation ("me.tankery.lib:circularSeekBar:1.4.2")
}


tasks.withType<Test> {
    useJUnitPlatform()
}