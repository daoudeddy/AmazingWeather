package com.github.amazingweather.remote.weatherforecast

import com.github.amazingweather.domain.forecast.WeatherForecast
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherForecastService {
    @GET("forecast")
    fun getWeatherForecast(@Query("id") id: Int): Call<WeatherForecastResponse>
}