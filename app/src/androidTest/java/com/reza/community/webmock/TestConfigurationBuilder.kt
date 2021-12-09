package com.reza.community.webmock

import androidx.test.platform.app.InstrumentationRegistry
import com.reza.community.BuildConfig
import com.reza.community.App
import com.reza.community.di.AppComponent
import com.reza.community.di.DaggerAppComponent

class TestConfigurationBuilder {
    private val baseUrl: String = "http://127.0.0.1:${BuildConfig.PORT}"

    fun inject() {
        appComponent {
            networkModule(NetworkModule(baseUrl))
        }.inject(requireTestedApplication())
    }
}

fun injectTestConfiguration(block: TestConfigurationBuilder.() -> Unit) {
    TestConfigurationBuilder().apply(block).inject()
}

private fun appComponent(block: DaggerAppComponent.Builder.() -> Unit = {}): AppComponent =
    DaggerAppComponent.builder().apply(block).build()

private fun requireTestedApplication() =
    (InstrumentationRegistry.getInstrumentation().targetContext.applicationContext as App)