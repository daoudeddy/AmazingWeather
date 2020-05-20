package com.github.amazingweather.domain.forecast

data class WeatherForecast(
    val id: Int = 0,
    val name: String = "",
    val forecast: MutableList<DailyForecast> = mutableListOf()
)