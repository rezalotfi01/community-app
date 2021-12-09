package com.reza.android.plugin

import AndroidSettings
import Dependencies
import TestDependencies
import com.android.build.gradle.BaseExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.create
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.invoke
import org.gradle.kotlin.dsl.kotlin
import org.gradle.kotlin.dsl.withType
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

open class AndroidPlugin : Plugin<Project> {
    override fun apply(project: Project) {
        @Suppress("UnstableApiUsage")
        val extension = project.extensions.create<AndroidPluginExtension>("androidPlugin")

        project.configurePlugins(extension.buildType)
        project.configureAndroid()
        project.configureDependencies()

        project.afterEvaluate {
            with(project) {
                tasks {
                    withType<KotlinCompile> {
                        with(kotlinOptions) {
                            jvmTarget = "1.8"
                            freeCompilerArgs = listOf("-Xallow-result-return-type")
                        }
                    }
                }
            }
        }
    }

    private fun androidPlugins() = listOf(
        "kotlin-android"
    )

    private fun Project.configurePlugins(buildType: BuildType) = listOf(
        when (buildType) {
            BuildType.AndroidLibrary, BuildType.App -> androidPlugins()
            BuildType.Library -> listOf("kotlin")
        },
        listOf("kotlin-kapt")
    )
        .flatten()
        .also { println("AndroidPlugin: applying plugins $it") }
        .forEach(plugins::apply)

    @SuppressWarnings("WeakerAccess")
    private fun Project.configureAndroid() = extensions.getByType(BaseExtension::class.java).run {
        compileSdkVersion(AndroidSettings.compileSdk)
        buildToolsVersion(AndroidSettings.buildTools)


        defaultConfig {
            versionCode = AndroidSettings.versionCode
            versionName = AndroidSettings.versionName

            minSdk = AndroidSettings.minSdk
            targetSdk = AndroidSettings.targetSdk

            testInstrumentationRunner = AndroidSettings.testInstrumentationRunner

            packagingOptions {
                resources.excludes.add("META-INF/DEPENDENCIES")
                resources.excludes.add("META-INF/LICENSE")
                resources.excludes.add("META-INF/LICENSE.txt")
                resources.excludes.add("META-INF/license.txt")
                resources.excludes.add("META-INF/NOTICE")
                resources.excludes.add("META-INF/NOTICE.txt")
                resources.excludes.add("META-INF/notice.txt")
                resources.excludes.add("META-INF/ASL2.0")
                resources.excludes.add("META-INF/*.kotlin_module")
                resources.excludes.add("META-INF/AL2.0")
                resources.excludes.add("META-INF/LGPL2.1")
            }
        }

        testOptions {
            unitTests.isReturnDefaultValues = true
            animationsDisabled = true
        }

        lintOptions {
            isAbortOnError = false
        }
    }

    private fun Project.configureDependencies() = dependencies {
        fun kapt(definition: Any) = "kapt"(definition)
        fun implementation(definition: Any) = "implementation"(definition)
        fun testImplementation(definition: Any) = "testImplementation"(definition)
        fun androidTestImplementation(definition: Any) = "androidTestImplementation"(definition)

        implementation(kotlin("stdlib-jdk7"))
        testImplementation(kotlin("test"))

        implementation(Dependencies.kotlinxCoroutines)

        implementation(Dependencies.timber)

        kapt(Dependencies.Dagger.daggerCompiler)
        kapt(Dependencies.Dagger.daggerAndroidProcessor)

        androidTestImplementation(TestDependencies.AndroidX.core)
        androidTestImplementation(TestDependencies.AndroidX.coreKtx)
        androidTestImplementation(TestDependencies.AndroidX.runner)
        androidTestImplementation(TestDependencies.AndroidX.rules)
        androidTestImplementation(TestDependencies.AndroidX.espressoCore)
        androidTestImplementation(TestDependencies.AndroidX.espressoContrib)
        androidTestImplementation(TestDependencies.AndroidX.junit)
        testImplementation(TestDependencies.livedataTesting)

        testImplementation(TestDependencies.kotlinxCoroutines)
        androidTestImplementation(TestDependencies.kotlinxCoroutines)

        testImplementation(TestDependencies.JUnit.junit)
        testImplementation(TestDependencies.JUnit.junitPlatformRunner)

        testImplementation(TestDependencies.Mockito.mockitoCore)
        testImplementation(TestDependencies.Mockito.mockitoInline)
        testImplementation(TestDependencies.Mockito.mockitoKotlin)
    }
}

open class AndroidPluginExtension {
    var buildType = BuildType.AndroidLibrary
}

enum class BuildType {
    Library,
    AndroidLibrary,
    App
}

fun androidPluginId() = "com.reza.android.plugin"
