plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "net.fio.prototype"
    compileSdk = 34

    defaultConfig {
        applicationId = "net.fio.walletprototype"
        minSdk = 24
        targetSdk = 34
        versionCode = 2
        versionName = "0.1.1"
    }

    // Fixed demo key so every CI build can install over the previous one.
    // Demo only: never use this key for a real release.
    signingConfigs {
        create("demo") {
            storeFile = file("demo-signing.keystore")
            storePassword = "fiodemo123"
            keyAlias = "fiodemo"
            keyPassword = "fiodemo123"
        }
    }

    buildTypes {
        debug {
            isMinifyEnabled = false
            signingConfig = signingConfigs.getByName("demo")
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }

    // keep the web page and fonts uncompressed so the WebView reads them fast
    androidResources {
        noCompress += listOf("html", "woff2")
    }
}
