plugins {
    id("com.android.library")
    id("com.reza.android.plugin")
}

dependencies {
    implementation(project(ProjectModules.data))
    testImplementation(project(ProjectModules.coreAndroidTest))

    api(Dependencies.Retrofit.retrofit)
    api(Dependencies.Retrofit.retrofitConverterGson)
    api(Dependencies.okHttpLoggingInterceptor)

    implementation(Dependencies.Dagger.daggerAndroid)

}