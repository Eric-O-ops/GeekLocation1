plugins {
    id(Plugins.androidLibrary)
    id(Plugins.kotlinAndroid)

    // Kapt
    kotlin(Plugins.kapt)
}

android {
    namespace = Config.Data.namespace
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
}

dependencies {

    // Core
    implementation(Dependencies.Core.core)

    // Retrofit
    implementation(Dependencies.Retrofit.retrofit)

    // OkHttp
    implementation(Dependencies.OkHttp.okHttp)
    implementation(Dependencies.OkHttp.bom)

    // LoggingInterceptor
    implementation(Dependencies.OkHttp.loggingInterceptor)

    // Room
    implementation(Dependencies.Room.roomRuntime)
    kapt(Dependencies.Room.roomCompiler)

    // GsonConverter
    implementation(Dependencies.GsonConverter.gsonConverter)

    // Domain
    implementation(project(":domain"))

    // Lifecycle
    implementation(Dependencies.Lifecycle.liveData)
    implementation(Dependencies.Lifecycle.viewModel)
    implementation(Dependencies.Lifecycle.viewModelCompose)

    // JUnit
    testImplementation(Dependencies.Test.jUnit)
    androidTestImplementation(Dependencies.Test.extJUnit)

    // EspressoCore
    androidTestImplementation(Dependencies.Test.espressoCore)

    // Firebase auth
    implementation(Dependencies.Firebase.auth)

    // Firebase firestore
    implementation (Dependencies.Firebase.firestore)

}