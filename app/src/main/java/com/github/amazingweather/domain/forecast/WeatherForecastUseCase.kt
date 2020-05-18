package com.github.amazingweather.domain.forecast

import com.github.amazingweather.core.EitherErrorOr
import com.github.amazingweather.core.UseCase
import com.github.amazingweather.remote.weatherforecast.WeatherForecastRemoteData
import javax.inject.Inject

class WeatherForecastUseCase @Inject constructor(
    private val weatherForecast: WeatherForecastRemoteData
) : UseCase<WeatherForecastUseCase.Params, WeatherForecast>() {
    data class Params(val id: Int)

    override suspend fun run(params: Params): EitherErrorOr<WeatherForecast> {
        return weatherForecast.getWeatherForecast(params.id)
    }
}