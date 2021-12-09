plugins {
    id("com.android.library")
    id("com.reza.android.plugin")
}

dependencies {
    implementation(TestDependencies.kotlinxCoroutines)
    implementation(Dependencies.AndroidX.Navigation.fragmentKtx)
    implementation(TestDependencies.livedataTesting)
    implementation(TestDependencies.AndroidX.espressoCore)
    implementation(TestDependencies.AndroidX.espressoContrib)
}