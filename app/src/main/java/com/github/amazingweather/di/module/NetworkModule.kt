package com.github.amazingweather.di.module

import com.github.amazingweather.BuildConfig
import com.github.amazingweather.di.scope.AppScoped
import com.github.amazingweather.network.createNetworkClient
import com.github.amazingweather.remote.citesweather.WeatherService
import com.github.amazingweather.remote.weatherforecast.WeatherForecastService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class NetworkModule {

    @Provides
    @AppScoped
    internal fun providesRetrofit(): Retrofit =
        createNetworkClient("https://api.openweathermap.org/data/2.5/", BuildConfig.DEBUG)
            .build()

    @Provides
    @AppScoped
    internal fun provideWeatherService(retrofit: Retrofit): WeatherService =
        retrofit.create(WeatherService::class.java)

    @Provides
    @AppScoped
    internal fun provideWeatherForecastService(retrofit: Retrofit): WeatherForecastService =
        retrofit.create(WeatherForecastService::class.java)
}