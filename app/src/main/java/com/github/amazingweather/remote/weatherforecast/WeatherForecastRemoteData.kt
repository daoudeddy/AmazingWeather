package com.github.amazingweather.remote.weatherforecast

import com.github.amazingweather.network.safeApiCall
import retrofit2.http.GET
import retrofit2.http.Query
import javax.inject.Inject

class WeatherForecastRemoteData @Inject constructor(
    private val weatherForecastService: WeatherForecastService
) {
    @GET("forecast")
    suspend fun getWeatherForecast(@Query("id") id: Int) = safeApiCall(
        weatherForecastService.getWeatherForecast(id)
    ) {
        it.toDomain()
    }
}