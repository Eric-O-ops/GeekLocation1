// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id(Plugins.application) version Version.applicationAndKotlinAndroid apply false
    id(Plugins.androidLibrary) version Version.applicationAndKotlinAndroid apply false
    id(Plugins.kotlinAndroid) version Version.kotlinAndroid apply false
    id(Plugins.kotlinJVM) version Version.kotlinAndroid apply false

    // Hilt
    id(Plugins.hilt) version Version.hilt apply false

    // MapsPlatform
    id(Plugins.mapsPlatform) version Version.mapsPlatform apply false
}