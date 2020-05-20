package com.github.amazingweather.domain.forecast

data class DailyForecast(
    val condition: String = "",
    val icon: String = "",
    val date: Long = 0L,
    val windSpeed: Int = 0,
    val humidity: Int = 0,
    val temp: Int = 0
)