package com.github.amazingweather.domain.citiesweather

data class CityWeather(
    val id: Int = 0,
    val name: String = "",
    val humidity: Int = 0,
    val pressure: Int = 0,
    val temp: Double = 0.0,
    val tempMax: Double = 0.0,
    val tempMin: Double = 0.0,
    val windSpeed: Double = 0.0,
    val condition: String = "",
    val icon: String = ""
)

