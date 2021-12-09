plugins {
    id("com.android.library")
    id("com.reza.android.plugin")
}

dependencies {
    implementation(project(ProjectModules.data))
    testImplementation(project(ProjectModules.coreAndroidTest))

    api(Dependencies.Room.room)
    kapt(Dependencies.Room.roomCompiler)
    implementation(Dependencies.Room.roomKtx)

    implementation(Dependencies.Dagger.daggerAndroid)
}