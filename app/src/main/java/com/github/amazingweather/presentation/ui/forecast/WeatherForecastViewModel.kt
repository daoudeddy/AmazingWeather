package com.github.amazingweather.presentation.ui.forecast

import androidx.lifecycle.viewModelScope
import com.github.amazingweather.domain.forecast.DailyForecast
import com.github.amazingweather.domain.forecast.WeatherForecast
import com.github.amazingweather.domain.forecast.WeatherForecastUseCase
import com.github.amazingweather.presentation.base.BaseUiView
import com.github.amazingweather.presentation.base.BaseViewModel
import com.github.amazingweather.presentation.component.view.DailyForecastUiView
import com.github.amazingweather.presentation.component.view.WeatherForecastUiView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

data class WeatherForecastState(val id: Int = 0)

class WeatherForecastViewModel @Inject constructor(
    weatherForecastUseCase: WeatherForecastUseCase
) : BaseViewModel<WeatherForecastState, WeatherForecastUseCase.Params, WeatherForecast>(
    WeatherForecastState(), weatherForecastUseCase
) {

    override fun getParams() = WeatherForecastUseCase.Params(state.id)

    fun fetchData(id: Int) {
        setState { copy(id = id) }
        fetchData(true)
    }

    override suspend fun toUiViews(result: WeatherForecast): List<BaseUiView> {
        return listOf(result.toUiView())
    }

    private fun onDoAction(action: suspend () -> Unit) {
        viewModelScope.launch { withContext(Dispatchers.IO) { action.invoke() } }
    }


    private fun WeatherForecast.toUiView(): WeatherForecastUiView {
        val currentDayItem = forecast.first()
        return WeatherForecastUiView(
            name = name,
            date = currentDayItem.date,
            humidity = currentDayItem.humidity,
            temp = currentDayItem.temp,
            windSpeed = currentDayItem.windSpeed,
            condition = currentDayItem.condition,
            icon = currentDayItem.icon,
            forecast = forecast.map { it.toUiView() }
        )
    }

    private fun DailyForecast.toUiView(): DailyForecastUiView {
        return DailyForecastUiView(
            condition,
            icon,
            date,
            windSpeed,
            humidity,
            temp
        ).apply { doAction = ::onDoAction }
    }
}

