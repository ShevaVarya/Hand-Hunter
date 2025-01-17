plugins {
    id("ru.practicum.android.diploma.plugins.developproperties")
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.ksp)
}

android {
    namespace = "ru.practicum.android.diploma"
    compileSdk = libs.versions.compileSdk.get().toInt()

    defaultConfig {
        applicationId = "ru.practicum.android.diploma"
        minSdk = libs.versions.minSdk.get().toInt()
        targetSdk = libs.versions.targetSdk.get().toInt()
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        buildConfigField(type = "String", name = "HH_ACCESS_TOKEN", value = "\"${developProperties.hhAccessToken}\"")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }

    buildFeatures {
        viewBinding = true
        buildConfig = true
    }
}

dependencies {
    implementation(libs.androidX.core)
    implementation(libs.androidX.appCompat)
    implementation(libs.androidX.activity)
    implementation(libs.androidX.fragment)

    // UI layer libraries
    implementation(libs.ui.material)
    implementation(libs.ui.constraintLayout)

    // region AndroidX Navigation
    implementation(libs.navigation.fragment.ktx)
    implementation(libs.navigation.ui.ktx)
    // endregion

    // Gson
    implementation(libs.gson)

    // region Retrofit
    implementation(libs.retrofit)
    implementation(libs.converter.gson)
    // endregion

    // region OkHttp
    implementation(libs.okhttp)
    implementation(libs.logging.interceptor)
    // endregion

    // region Room
    implementation(libs.room.runtime)
    implementation(libs.room.ktx)
    ksp(libs.room.compiler)
    // endregion

    // Koin
    implementation(libs.koin.android)

    // Glide
    implementation(libs.glide)

    // region Unit tests
    testImplementation(libs.unitTests.junit)
    // endregion
}
