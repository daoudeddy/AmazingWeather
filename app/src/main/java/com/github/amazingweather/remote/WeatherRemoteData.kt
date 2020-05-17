package com.github.amazingweather.remote

class WeatherRemoteData constructor(
    private val weatherService: WeatherService
) {
    fun getWeatherByCoordination(
        latitude: String,
        longitude: String
    ) = weatherService.getWeatherByCoordination(latitude, longitude)

    fun getWeatherById(
        id: String
    ) = weatherService.getWeatherById(id)

    fun getWeatherById(
        ids: List<String>
    ) = weatherService.getWeatherByIds(ids)
}