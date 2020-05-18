package com.github.amazingweather.remote.citesweather

import com.github.amazingweather.core.EitherErrorOr
import com.github.amazingweather.domain.citiesweather.CityWeather
import com.github.amazingweather.network.safeApiCall
import javax.inject.Inject

class WeatherRemoteData @Inject constructor(
    private val weatherService: WeatherService
) {
    suspend fun getWeatherByCoordination(
        latitude: String,
        longitude: String
    ): EitherErrorOr<CityWeather> =
        safeApiCall(weatherService.getWeatherByCoordination(latitude, longitude)) {
            it.toDomainModel()
        }

    suspend fun getWeatherById(
        id: String
    ): EitherErrorOr<CityWeather> =
        safeApiCall(weatherService.getWeatherById(id)) {
            it.toDomainModel()
        }

    suspend fun getWeatherByIds(
        ids: List<Int>
    ): EitherErrorOr<List<CityWeather>> =
        safeApiCall(weatherService.getWeatherByIds(ids.joinToString(separator = ","))) { result ->
            result.list.map { item -> item.toDomainModel() }
        }
}