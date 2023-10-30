@file:Suppress("DSL_SCOPE_VIOLATION")

plugins {
    id(libs.plugins.android.library.get().pluginId)
    id(libs.plugins.kotlin.android.get().pluginId)
    id(libs.plugins.dagger.hilt.get().pluginId)
    id(libs.plugins.android.dagger.hilt.get().pluginId)
    id(libs.plugins.android.kotlin.kapt.get().pluginId)
    id(libs.plugins.kotlin.safeArgs.get().pluginId)
}

android {
    namespace = "com.ebelli.dashboard"
    compileSdk = libs.versions.compileSdk.get().toInt()

    defaultConfig {
        minSdk = libs.versions.minSdk.get().toInt()

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    @Suppress("unstableapiusage")
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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    @Suppress("unstableapiusage")
    buildFeatures{
        viewBinding =true
        dataBinding = true
    }
}

dependencies {
    api(project(":feature:detail"))
    implementation(libs.core.ktx)
    implementation(libs.androidx.constraintLayout)
    implementation(libs.google.material)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx)
    kapt("com.android.databinding:compiler:3.1.4")
    testImplementation(libs.junit)
    //mockito
    testImplementation(libs.unit.test.mockk)
    testImplementation(libs.unit.test.mockito.kotlin)
    testImplementation(libs.unit.test.mockito.core)
    //coroutines test
    testImplementation(libs.unit.test.coroutines)

    //hilt
    implementation(libs.hilt.android)
    kapt(libs.hilt.compiler)

    //viewmodel
    implementation(libs.androidx.lifecycle.viewmodel.ktx)
}