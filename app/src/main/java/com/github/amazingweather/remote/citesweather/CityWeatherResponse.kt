package com.github.amazingweather.remote.citesweather

import com.github.amazingweather.domain.citiesweather.CityWeather

data class CityWeatherResponse(
    val base: String,
    val clouds: Clouds,
    val cod: Int,
    val coord: Coord,
    val dt: Int,
    val id: Int,
    val main: Main,
    val name: String,
    val sys: Sys,
    val visibility: Int,
    val weather: List<Weather>,
    val wind: Wind
) {
    fun toDomainModel() =
        CityWeather(
            id,
            name,
            main.humidity,
            main.pressure,
            main.temp,
            main.temp_max,
            main.temp_min,
            wind.speed,
            weather.firstOrNull()?.main ?: "",
            weather.firstOrNull()?.icon ?: ""
        )
}

data class Clouds(
    val all: Int
)

data class Coord(
    val lat: Double,
    val lon: Double
)

data class Main(
    val humidity: Int,
    val pressure: Int,
    val temp: Double,
    val temp_max: Double,
    val temp_min: Double
)

data class Sys(
    val country: String,
    val id: Int,
    val message: Double,
    val sunrise: Int,
    val sunset: Int,
    val type: Int
)

data class Weather(
    val description: String,
    val icon: String,
    val id: Int,
    val main: String
)

data class Wind(
    val deg: Int,
    val speed: Double
)
