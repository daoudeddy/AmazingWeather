package com.github.amazingweather.presentation.ui.forecast

import com.github.amazingweather.presentation.base.BaseUiView
import com.github.amazingweather.presentation.component.ViewHolderFactory

data class DailyForecastUiView(
    val condition: String,
    val icon: String,
    val date: Int,
    val windSpeed: Double,
    val humidity: Int,
    val temp: Double,
    val tempMin: Double,
    val tempMax: Double
) : BaseUiView() {

    override var itemViewType = ViewHolderFactory.DAILY_FORECAST_ITEM
    override fun getIdentifier() = date.toString()

    override fun areContentsTheSame(other: BaseUiView): Boolean {
        return other is DailyForecastUiView && other.condition == condition &&
                other.icon == icon && other.windSpeed == windSpeed &&
                other.humidity == humidity && other.temp == temp &&
                other.tempMin == tempMin && other.tempMax == tempMax
    }
}