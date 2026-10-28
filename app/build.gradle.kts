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
        versionCode = 1
        versionName = "0.1.0"
    }

    buildTypes {
        debug {
            isMinifyEnabled = false
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
