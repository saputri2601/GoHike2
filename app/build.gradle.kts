plugins {
    alias(libs.plugins.android.application)  // Plugin untuk aplikasi Android
    alias(libs.plugins.google.gms.google.services) // Plugin untuk Firebase dan Google Services
}


android {
    namespace = "com.example.gohike2"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.gohike2"
        minSdk = 23
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

    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    implementation (libs.osmdroid.android)
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
    implementation(libs.recyclerview)
    implementation(libs.cardview)
    implementation(libs.osmdroid.android)
    implementation(libs.firebase.auth)
    implementation(libs.firebase.database)
    implementation(libs.circleimageview)
    implementation(libs.okhttp)
    implementation(libs.moshi)
    implementation(libs.glide)
    implementation(libs.firebase.firestore)
    annotationProcessor(libs.glide.compiler)
    implementation(libs.play.services.auth)
    // Jika perlu, tambahkan dependensi Firebase lainnya seperti Firestore atau Analytics
}
