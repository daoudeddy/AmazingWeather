package com.github.amazingweather.di

import android.app.Application
import com.github.amazingweather.di.module.ActivityBindingModule
import com.github.amazingweather.di.module.AppModule
import com.github.amazingweather.di.module.NetworkModule
import com.github.amazingweather.di.module.ViewModelModule
import com.github.amazingweather.di.scope.AppScoped
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import dagger.android.support.AndroidSupportInjectionModule

@AppScoped
@Component(
    modules = [
        AppModule::class,
        ActivityBindingModule::class,
        AndroidSupportInjectionModule::class,
        ViewModelModule::class,
        NetworkModule::class
    ]
)
interface AppComponent : AndroidInjector<DaggerApplication> {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

}