plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    kotlin("kapt")
}

android {
    namespace = "com.example.room"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.room"
        minSdk = 26
        targetSdk = 34
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
        viewBinding = true
    }
}

dependencies {
    // Local Unit Tests
    implementation("androidx.test:core:1.5.0") // Updated to the latest stable version
    testImplementation("junit:junit:4.13") // Stable version, no change needed
    testImplementation("org.hamcrest:hamcrest:2.2") // Latest version for hamcrest
    testImplementation("androidx.arch.core:core-testing:2.1.0") // Latest stable
    testImplementation("org.robolectric:robolectric:4.9.0") // Updated version of Robolectric
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.7.3") // Updated to the latest stable version
    testImplementation("com.google.truth:truth:1.1") // Latest version of Truth
    testImplementation("org.mockito:mockito-core:4.8.0") // Updated to the latest version

    // Instrumented Unit Tests
    androidTestImplementation("junit:junit:4.13") // Same version, no change needed
    androidTestImplementation("androidx.arch.core:core-testing:2.1.0") // Latest stable
    androidTestImplementation("com.google.truth:truth:1.1") // Updated version of Truth
    androidTestImplementation("androidx.test.ext:junit:1.1.5") // Updated to latest stable
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1") // Updated to latest stable
    androidTestImplementation("org.mockito:mockito-core:4.8.0") // Updated to latest stable



    //Retrofit
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation("com.squareup.okhttp3:logging-interceptor:4.7.2")
    implementation("androidx.legacy:legacy-support-v4:1.0.0")
    implementation("androidx.preference:preference-ktx:1.1.1")
    implementation("com.squareup.retrofit2:converter-scalars:2.9.0")

    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.appcompat:appcompat:1.5.1")
    implementation("com.google.android.material:material:1.9.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    testImplementation ("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.3")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.4.0")
    //ROOM
    implementation("androidx.room:room-runtime:2.5.0")
    implementation("androidx.room:room-ktx:2.5.0")
    androidTestImplementation(libs.androidx.core.testing)
    kapt("androidx.room:room-compiler:2.5.0")
    implementation("androidx.room:room-testing:2.5.1")

    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.5.1")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.5.1")
    implementation("androidx.activity:activity-ktx:1.6.0")
    implementation("androidx.fragment:fragment-ktx:1.5.3")
    annotationProcessor("android.arch.persistence.room:compiler:1.1.1")

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}