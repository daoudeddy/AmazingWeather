package com.github.amazingweather.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.github.amazingweather.di.ViewModelKey
import com.github.amazingweather.di.scope.AppScoped
import com.github.amazingweather.presentation.ViewModelFactory
import com.github.amazingweather.presentation.ui.forecast.WeatherForecastFragment
import com.github.amazingweather.presentation.ui.forecast.WeatherForecastViewModel
import com.github.amazingweather.presentation.ui.main.MainViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {
    @Binds
    @AppScoped
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun bindMainViewModel(viewModel: MainViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun bindWeatherForecastViewModel(viewModel: WeatherForecastViewModel): ViewModel
}