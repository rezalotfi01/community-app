plugins {
    id("com.android.library")
    id("com.reza.android.plugin")
}

dependencies {
    implementation(Dependencies.Dagger.daggerAndroid)

    testImplementation(project(ProjectModules.coreAndroidTest))
}