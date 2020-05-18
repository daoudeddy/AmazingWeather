package com.github.amazingweather.presentation.ui.forecast

import com.github.amazingweather.domain.forecast.DailyForecast
import com.github.amazingweather.presentation.base.BaseUiView
import com.github.amazingweather.presentation.component.ViewHolderFactory

data class WeatherForecastUiView(
    val id: Int,
    val name: String,
    val forecast: List<DailyForecast>
) : BaseUiView() {
    override fun getIdentifier() = id.toString()

    override var itemViewType = ViewHolderFactory.WEATHER_FORECAST_ITEM

    override fun areContentsTheSame(other: BaseUiView): Boolean {
        return other is WeatherForecastUiView && name == other.name
    }
}
