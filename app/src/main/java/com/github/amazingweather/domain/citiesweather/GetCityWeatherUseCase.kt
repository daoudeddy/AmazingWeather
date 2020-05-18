package com.github.amazingweather.domain.citiesweather

import com.github.amazingweather.core.EitherErrorOr
import com.github.amazingweather.core.UseCase
import com.github.amazingweather.remote.citesweather.WeatherRemoteData
import javax.inject.Inject

class GetCityWeatherUseCase @Inject constructor(
    private val weatherRemoteData: WeatherRemoteData
) : UseCase<GetCityWeatherUseCase.Params, CityWeather>() {

    data class Params(val cityId: String = "", val longitude: String?, val latitude: String?)

    override suspend fun run(params: Params): EitherErrorOr<CityWeather> {
        with(params) {
            return when {
                latitude != null && longitude != null -> weatherRemoteData.getWeatherByCoordination(
                    latitude,
                    longitude
                )
                else -> weatherRemoteData.getWeatherById(cityId)
            }
        }
    }
}