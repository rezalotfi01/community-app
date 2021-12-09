buildscript {
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath(BuildDependencies.androidGradle)
        classpath(BuildDependencies.kotlinGradlePlugin)
    }
}

allprojects {
    repositories {
        google()
        mavenCentral()
        maven("https://jitpack.io")
    }
}

task("clean") {
    delete(rootProject.buildDir)
}
