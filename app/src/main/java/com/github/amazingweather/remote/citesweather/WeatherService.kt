package com.github.amazingweather.remote.citesweather

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherService {

    @GET("weather")
    fun getWeatherByCoordination(
        @Query("lat") latitude: Double,
        @Query("lon") longitude: Double
    ): Call<CityWeatherResponse>

    @GET("group")
    fun getWeatherByIds(@Query("id") id: String): Call<CitiesWeatherResponse>
}