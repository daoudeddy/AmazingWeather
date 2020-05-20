package com.github.amazingweather.remote.weatherforecast

import com.github.amazingweather.domain.forecast.DailyForecast
import com.github.amazingweather.domain.forecast.WeatherForecast

data class WeatherForecastResponse(
    val city: City,
    val cnt: Int,
    val cod: String,
    val list: List<CityWeather>,
    val message: Double
) {
    fun toDomain() = WeatherForecast(
        city.id, city.name, list.filter {
            it.dt_txt.contains("12:00:00")
        }.map {
            DailyForecast(
                it.weather.firstOrNull()?.main.orEmpty(),
                it.weather.firstOrNull()?.icon.orEmpty(),
                it.dt,
                it.wind.speed.toInt(),
                it.main.humidity,
                it.main.temp.toInt()
            )
        }.toMutableList()
    )
}

data class Weather(
    val description: String,
    val icon: String,
    val id: Int,
    val main: String
)

data class Wind(
    val deg: Double,
    val speed: Double
)

data class City(
    val coord: Coord,
    val country: String,
    val id: Int,
    val name: String
)

data class CityWeather(
    val clouds: Clouds,
    val dt: Long,
    val dt_txt: String,
    val main: Main,
    val rain: Rain,
    val snow: Snow,
    val sys: Sys,
    val weather: List<Weather>,
    val wind: Wind
)

data class Clouds(
    val all: Int
)

data class Coord(
    val lat: Double,
    val lon: Double
)

data class Main(
    val grnd_level: Double,
    val humidity: Int,
    val pressure: Double,
    val sea_level: Double,
    val temp: Double,
    val temp_kf: Double,
    val temp_max: Double,
    val temp_min: Double
)

data class Rain(
    val `3h`: Double
)

data class Snow(
    val `3h`: Double
)

data class Sys(
    val pod: String
)