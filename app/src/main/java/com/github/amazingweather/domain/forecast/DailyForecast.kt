package com.github.amazingweather.domain.forecast

data class DailyForecast(
    val condition: String,
    val icon: String,
    val date: Int,
    val windSpeed: Double,
    val humidity: Int,
    val temp: Double,
    val tempMin: Double,
    val tempMax: Double
)