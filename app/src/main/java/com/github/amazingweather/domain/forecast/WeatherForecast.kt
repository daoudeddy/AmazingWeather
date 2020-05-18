package com.github.amazingweather.domain.forecast

data class WeatherForecast(
    val id: Int,
    val name: String,
    val forecast: List<DailyForecast>
)