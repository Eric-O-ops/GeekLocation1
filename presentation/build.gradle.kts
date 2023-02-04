plugins {
    id(Plugins.androidLibrary)
    id(Plugins.kotlinAndroid)

    // Kapt
    kotlin(Plugins.kapt)

    // Hilt
    id(Plugins.hilt)
    id(Plugins.mapsPlatform)
    id(Plugins.googleServices)
}

android {
    namespace = Config.Presentation.namespace
    compileSdk = Config.targetAndCompileSdk

    defaultConfig {
        minSdk = Config.minSdk
        targetSdk = Config.targetAndCompileSdk

        testInstrumentationRunner = Config.testInstrumentationRunner
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
        jvmTarget = Config.jvmTarget
    }

    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    // Core
    implementation(Dependencies.Core.core)

    // AppCompat
    implementation(Dependencies.UIComponents.appCompat)

    // Material
    implementation(Dependencies.UIComponents.material)

    // ConstraintLayout
    implementation(Dependencies.UIComponents.constraintLayout)

    // NavComponents
    implementation(Dependencies.NavComponents.navigationUI)
    implementation(Dependencies.NavComponents.navigationFragment)

    // Coil
    implementation(Dependencies.Coil.coil)

    // Lifecycle
    implementation(Dependencies.Lifecycle.liveData)
    implementation(Dependencies.Lifecycle.viewModel)
    implementation(Dependencies.Lifecycle.viewModelCompose)

    // ViewBindingPropertyDelegate
    implementation(Dependencies.ViewBindingPropertyDelegate.viewBindingPropertyDelegate)

    // Domain
    implementation(project(":domain"))

    // Hilt
    implementation(Dependencies.Hilt.hilt)
    kapt(Dependencies.Hilt.compiler)

    // JUnit
    testImplementation(Dependencies.Test.jUnit)
    androidTestImplementation(Dependencies.Test.extJUnit)

    // EspressoCore
    androidTestImplementation(Dependencies.Test.espressoCore)

    implementation(Dependencies.Maps.playServicesMaps)

    // Sign with google
    implementation (Dependencies.GoogleServices.googleAuthServies)

    implementation("com.google.firebase:firebase-auth-ktx:21.1.0")
}