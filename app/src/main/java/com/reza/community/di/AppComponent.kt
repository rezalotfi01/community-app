package com.reza.community.di

import android.app.Application
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import com.reza.core.di.FragmentBindingModule
import com.reza.core.di.ViewModelBindingModule
import com.reza.data.di.CommunityRepositoryModule
import com.reza.remote.di.ApiModule
import com.reza.remote.di.NetworkModule
import com.reza.domain.di.DomainModule
import com.reza.presentation.di.PresentationModule
import com.reza.community.App
import com.reza.local.di.DatabaseModule
import com.reza.local.di.LocalModule
import dagger.BindsInstance
import javax.inject.Singleton

@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        AppModule::class,
        ApiModule::class,
        MainActivityModule::class,
        PresentationModule::class,
        NetworkModule::class,
        DomainModule::class,
        CommunityRepositoryModule::class,
        ViewModelBindingModule::class,
        FragmentBindingModule::class,
        LocalModule::class,
        DatabaseModule::class
    ]
)
@Singleton
//interface AppComponent : AndroidInjector<App>

interface AppComponent : AndroidInjector<App> {
    override fun inject(app: App)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): AppComponent.Builder
        fun build(): AppComponent
    }
}
