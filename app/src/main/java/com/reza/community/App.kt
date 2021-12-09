package com.reza.community

import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import com.reza.community.di.DaggerAppComponent

open class App : DaggerApplication() {
    override fun applicationInjector(): AndroidInjector<out DaggerApplication> =
        DaggerAppComponent.builder()
            .application(this)
            .build()
}
