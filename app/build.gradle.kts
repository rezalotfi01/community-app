plugins {
    id("com.android.application")
    id("com.reza.android.plugin")
}

androidPlugin {
    buildType = com.reza.android.plugin.BuildType.App
}

android {
    defaultConfig {
        applicationId = "com.reza.community"
    }

    buildFeatures {
        viewBinding = true
    }

    buildTypes {
        getByName("debug") {
            isDebuggable = true
            buildConfigField("Integer", "PORT", "8080")
        }
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                file("proguard-rules.pro")
            )
        }
    }
}

dependencies {
    implementation(project(ProjectModules.core))
    implementation(project(ProjectModules.remote))
    implementation(project(ProjectModules.local))
    implementation(project(ProjectModules.presentation))
    implementation(project(ProjectModules.domain))
    implementation(project(ProjectModules.data))
    androidTestImplementation(project(ProjectModules.coreAndroidTest))
    testImplementation(project(ProjectModules.coreAndroidTest))

    implementation(Dependencies.AndroidX.fragmentKtx)
    implementation(Dependencies.AndroidX.lifecycleLivedataKtx)
    annotationProcessor(Dependencies.AndroidX.lifecycleCompiler)
    implementation(Dependencies.AndroidX.lifecycleExtensions)
    implementation(Dependencies.AndroidX.pagingRuntime)

    implementation(Dependencies.glide)
    implementation(Dependencies.lottie)
    implementation(Dependencies.viewBindingDelegate)
    implementation(Dependencies.internetCheckerDialog)

    implementation(Dependencies.AndroidX.constraintLayout)
    implementation(Dependencies.AndroidX.legacySupport)
    implementation(Dependencies.AndroidX.Navigation.fragmentKtx)
    implementation(Dependencies.AndroidX.Navigation.uiKtx)
    implementation(TestDependencies.stetho)
    implementation(TestDependencies.stethoOKHttp)

    androidTestImplementation(TestDependencies.AndroidX.runner)
    androidTestImplementation(TestDependencies.AndroidX.rules)
    androidTestImplementation(TestDependencies.mockWebServer)
    androidTestImplementation(TestDependencies.AndroidX.core)
    androidTestImplementation(TestDependencies.AndroidX.coreKtx)
}
