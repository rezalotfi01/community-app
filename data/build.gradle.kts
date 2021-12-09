plugins {
    id("com.android.library")
    id("com.reza.android.plugin")
}

dependencies {
    implementation(project(ProjectModules.domain))
    testImplementation(project(ProjectModules.coreAndroidTest))

    implementation(Dependencies.Dagger.daggerAndroid)
}