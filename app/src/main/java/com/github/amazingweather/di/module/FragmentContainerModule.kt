package com.github.amazingweather.di.module

import com.github.amazingweather.di.scope.FragmentScoped
import com.github.amazingweather.presentation.ui.forecast.WeatherForecastFragment
import com.github.amazingweather.presentation.ui.main.MainFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module(includes = [FragmentContainerModule.MainAbstractModule::class, FragmentContainerModule.ForecastAbstractModule::class])
class FragmentContainerModule {

    @Module
    interface MainAbstractModule {
        @FragmentScoped
        @ContributesAndroidInjector
        fun mainFragment(): MainFragment
    }

    @Module
    interface ForecastAbstractModule {
        @FragmentScoped
        @ContributesAndroidInjector
        fun forecastFragment(): WeatherForecastFragment
    }
}