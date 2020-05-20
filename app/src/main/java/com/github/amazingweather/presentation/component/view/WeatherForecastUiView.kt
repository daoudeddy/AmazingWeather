package com.github.amazingweather.presentation.component.view

import com.github.amazingweather.presentation.base.BaseUiView
import com.github.amazingweather.presentation.component.viewholder.ViewHolderFactory

data class WeatherForecastUiView(
    var id: Int = 0,
    var name: String = "",
    val date: Long,
    val humidity: Int,
    val temp: Int,
    val windSpeed: Int,
    val condition: String,
    val icon: String,
    val forecast: List<DailyForecastUiView>
) : BaseUiView() {
    override fun getIdentifier() = id.toString()

    override var itemViewType = ViewHolderFactory.WEATHER_FORECAST_ITEM

    override fun areContentsTheSame(other: BaseUiView): Boolean {
        return other is WeatherForecastUiView && name == other.name
    }
}
