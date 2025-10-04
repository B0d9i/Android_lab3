// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "com.example.mylab3"
    // Рекомендовано використовувати останню stable compileSdk, наприклад, 34 або 35.
    // Я залишив 36, як було у вас, якщо це нестабільна версія.
    compileSdk = 36

    defaultConfig {
        applicationId = "com.example.mylab3"
        // ВИПРАВЛЕННЯ: Змінено minSdk з 16 на 21, щоб задовольнити вимоги Material Design.
        minSdk = 21
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

dependencies {

    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
}