plugins {
    id("com.android.library")
    id("com.reza.android.plugin")
}

dependencies {
    implementation(project(ProjectModules.domain))
    implementation(project(ProjectModules.core))
    testImplementation(project(ProjectModules.coreAndroidTest))

    implementation(Dependencies.AndroidX.Navigation.fragmentKtx)
    implementation(Dependencies.AndroidX.lifecycleLivedataKtx)
    implementation(Dependencies.AndroidX.paging)

    implementation(Dependencies.timber)
    implementation(Dependencies.Dagger.daggerAndroid)
    implementation(Dependencies.Retrofit.retrofit)

}