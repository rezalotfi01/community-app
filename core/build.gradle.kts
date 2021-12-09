plugins {
    id("com.android.library")
    id("com.reza.android.plugin")
}

dependencies {
    api(Dependencies.Dagger.dagger)
    api(Dependencies.Dagger.daggerAndroid)
    api(Dependencies.Dagger.daggerAndroidSupport)

    implementation(Dependencies.AndroidX.Navigation.fragmentKtx)
    implementation(Dependencies.AndroidX.Navigation.uiKtx)
    implementation(Dependencies.AndroidX.lifecycleLivedataKtx)
    annotationProcessor(Dependencies.AndroidX.lifecycleCompiler)
    annotationProcessor(Dependencies.AndroidX.lifecycleViewModel)
    implementation(Dependencies.AndroidX.lifecycleExtensions)
}