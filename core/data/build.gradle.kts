@file:Suppress("DSL_SCOPE_VIOLATION")

plugins {
    id(libs.plugins.android.library.get().pluginId)
    id(libs.plugins.kotlin.android.get().pluginId)
    kotlin(libs.plugins.serialization.get().pluginId) version "${libs.plugins.serialization.get().version}"
    id(libs.plugins.dagger.hilt.get().pluginId)
    id(libs.plugins.android.dagger.hilt.get().pluginId)
    id(libs.plugins.android.kotlin.kapt.get().pluginId)
}

android {
    namespace = "${libs.versions.namespace}.core.data"
    compileSdk = libs.versions.compileSdk.get().toInt()

    defaultConfig {
        minSdk = libs.versions.minSdk.get().toInt()
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    api(project(":core:common"))
    implementation(project(":core:asset"))
    implementation(libs.core.ktx)
    implementation(libs.androidx.appcompat)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.test.ext.junit)
    androidTestImplementation(libs.espresso.core)

    //serialization
    implementation(libs.kotlinx.serialization)

    //room
    implementation(libs.room.runtime)
    kapt(libs.room.compiler)
    implementation(libs.room.ktx)

    //hilt
    implementation(libs.hilt.android)
    kapt(libs.hilt.compiler)
}