package com.github.amazingweather.remote.citesweather

import com.github.amazingweather.remote.citesweather.CitiesWeatherResponse
import com.github.amazingweather.remote.citesweather.CityWeatherResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherService {

    @GET("weather")
    fun getWeatherByCoordination(
        @Query("lat") latitude: String,
        @Query("lon") longitude: String
    ): Call<CityWeatherResponse>

    @GET("weather")
    fun getWeatherById(@Query("id") cityId: String): Call<CityWeatherResponse>

    @GET("group")
    fun getWeatherByIds(@Query("id") id: String): Call<CitiesWeatherResponse>
}