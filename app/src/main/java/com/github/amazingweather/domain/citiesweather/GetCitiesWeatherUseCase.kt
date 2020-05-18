package com.github.amazingweather.domain.citiesweather

import com.github.amazingweather.core.EitherErrorOr
import com.github.amazingweather.core.UseCase
import com.github.amazingweather.remote.citesweather.WeatherRemoteData
import javax.inject.Inject

class GetCitiesWeatherUseCase @Inject constructor(
    private val weatherRemoteData: WeatherRemoteData
) : UseCase<GetCitiesWeatherUseCase.Params, List<CityWeather>>() {

    data class Params(val cityIds: List<Int>)

    override suspend fun run(params: Params): EitherErrorOr<List<CityWeather>> {
        return weatherRemoteData.getWeatherByIds(params.cityIds)
    }
}