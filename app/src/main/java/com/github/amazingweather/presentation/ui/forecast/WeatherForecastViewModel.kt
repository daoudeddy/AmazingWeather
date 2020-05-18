package com.github.amazingweather.presentation.ui.forecast

import com.github.amazingweather.domain.forecast.WeatherForecast
import com.github.amazingweather.domain.forecast.WeatherForecastUseCase
import com.github.amazingweather.presentation.base.BaseViewModel
import javax.inject.Inject

data class WeatherForecastState(val id: Int = 0)

class WeatherForecastViewModel @Inject constructor(
    weatherForecastUseCase: WeatherForecastUseCase
) : BaseViewModel<WeatherForecastState, WeatherForecastUseCase.Params, WeatherForecast>(
    WeatherForecastState(), weatherForecastUseCase
) {

    override fun getParams() = WeatherForecastUseCase.Params(state.id)

    fun fetchDate(id: Int) {
        setState { copy(id = id) }
        fetchData(false)
    }

}