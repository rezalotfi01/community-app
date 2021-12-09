object ProjectModules {
    const val core = ":core"
    const val remote = ":remote"
    const val local = ":local"
    const val data = ":data"
    const val domain = ":domain"
    const val presentation = ":presentation"
    const val coreAndroidTest = ":core-android-test"
}

object AndroidSettings {
    const val versionCode = 1
    const val versionName = "1.0"
    const val compileSdk = 31
    const val buildTools = "31.0.0"
    const val minSdk = 21
    const val targetSdk = 31
    const val testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
}

object Versions {
    const val core = "1.3.2"
    const val fragment = "1.3.2"
    const val navigation = "2.3.5"
    const val constraintLayout = "2.1.0"
    const val legacySupport = "1.0.0"
    const val lifecycle = "2.2.0"
    const val livedataTesting = "1.2.0"
    const val paging = "3.1.0"
    const val dagger = "2.40.5"
    const val androidxTest = "1.3.0"
    const val espresso = "3.4.0"
    const val androidxJunit = "1.1.2"
    const val junit = "4.12"
    const val junitPlatformRunner = "1.0.2"
    const val mockito = "3.12.3"
    const val mockitoKotlin = "1.6.0"
    const val stetho = "1.5.1"
    const val gradle = "7.0.0"
    const val kotlin = "1.6.0"
    const val timber = "5.0.1"
    const val lottie = "4.2.2"
    const val retrofit = "2.9.0"
    const val room = "2.3.0"
    const val okhttp = "4.9.3"
    const val glide = "4.12.0"
    const val viewBindingDelegate = "1.4.7"
    const val kotlinxCoroutines = "1.5.2"
    const val internetCheckerDialog = "2.0.0"
}

object BuildDependencies {
    const val androidGradle =
        "com.android.tools.build:gradle:${Versions.gradle}"
    const val kotlinGradlePlugin =
        "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}"
}

object Dependencies {

    object AndroidX {
        const val fragmentKtx =
            "androidx.fragment:fragment-ktx:${Versions.fragment}"
        const val coreKtx =
            "androidx.core:core-ktx:${Versions.core}"
        const val constraintLayout =
            "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"
        const val legacySupport =
            "androidx.legacy:legacy-support-v4:${Versions.legacySupport}"
        const val lifecycleLivedataKtx =
            "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.lifecycle}"
        const val lifecycleCompiler =
            "androidx.lifecycle:lifecycle-compiler:${Versions.lifecycle}"
        const val lifecycleViewModel =
            "androidx.lifecycle:lifecycle-viewmodel:${Versions.lifecycle}"
        const val lifecycleExtensions =
            "androidx.lifecycle:lifecycle-extensions:${Versions.lifecycle}"
        const val pagingRuntime =
            "androidx.paging:paging-runtime-ktx:${Versions.paging}"
        const val paging =
            "androidx.paging:paging-common-ktx:${Versions.paging}"

        object Navigation {
            const val fragmentKtx =
                "androidx.navigation:navigation-fragment-ktx:${Versions.navigation}"
            const val uiKtx =
                "androidx.navigation:navigation-ui-ktx:${Versions.navigation}"
        }
    }

    object Dagger {
        const val dagger =
            "com.google.dagger:dagger:${Versions.dagger}"
        const val daggerAndroid =
            "com.google.dagger:dagger-android:${Versions.dagger}"
        const val daggerAndroidSupport =
            "com.google.dagger:dagger-android-support:${Versions.dagger}"
        const val daggerCompiler =
            "com.google.dagger:dagger-compiler:${Versions.dagger}"
        const val daggerAndroidProcessor =
            "com.google.dagger:dagger-android-processor:${Versions.dagger}"
    }

    object Retrofit {
        const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
        const val retrofitConverterGson =
            "com.squareup.retrofit2:converter-gson:${Versions.retrofit}"
    }

    object Room {
        const val room = "androidx.room:room-runtime:${Versions.room}"
        const val roomCompiler = "androidx.room:room-compiler:${Versions.room}"
        const val roomKtx = "androidx.room:room-ktx:${Versions.room}"
    }

    const val kotlinxCoroutines =
        "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.kotlinxCoroutines}"

    const val lottie = "com.airbnb.android:lottie:${Versions.lottie}"

    const val timber = "com.jakewharton.timber:timber:${Versions.timber}"
    const val okHttpLoggingInterceptor =
        "com.squareup.okhttp3:logging-interceptor:${Versions.okhttp}"

    const val glide = "com.github.bumptech.glide:glide:${Versions.glide}"

    const val viewBindingDelegate = "com.github.kirich1409:viewbindingpropertydelegate:${Versions.viewBindingDelegate}"

    const val internetCheckerDialog = "org.imaginativeworld.oopsnointernet:oopsnointernet:${Versions.internetCheckerDialog}"
}

object TestDependencies {

    object AndroidX {
        const val core =
            "androidx.test:core:${Versions.androidxTest}"
        const val coreKtx =
            "androidx.test:core-ktx:${Versions.androidxTest}"

        const val runner =
            "androidx.test:runner:${Versions.androidxTest}"
        const val rules =
            "androidx.test:rules:${Versions.androidxTest}"
        const val espressoCore =
            "androidx.test.espresso:espresso-core:${Versions.espresso}"
        const val espressoContrib =
            "androidx.test.espresso:espresso-contrib:${Versions.espresso}"
        const val junit =
            "androidx.test.ext:junit:${Versions.androidxJunit}"
    }

    const val kotlinxCoroutines =
        "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.kotlinxCoroutines}"

    object JUnit {
        const val junit =
            "junit:junit:${Versions.junit}"
        const val junitPlatformRunner =
            "org.junit.platform:junit-platform-runner:${Versions.junitPlatformRunner}"
    }

    const val livedataTesting =
        "com.jraska.livedata:testing-ktx:${Versions.livedataTesting}"

    object Mockito {
        const val mockitoCore =
            "org.mockito:mockito-core:${Versions.mockito}"
        const val mockitoInline =
            "org.mockito:mockito-inline:${Versions.mockito}"
        const val mockitoKotlin =
            "com.nhaarman:mockito-kotlin:${Versions.mockitoKotlin}"
    }

    const val mockWebServer = "com.squareup.okhttp3:mockwebserver:${Versions.okhttp}"

    const val stetho = "com.facebook.stetho:stetho:${Versions.stetho}"
    const val stethoOKHttp = "com.facebook.stetho:stetho-okhttp3:${Versions.stetho}"

}
