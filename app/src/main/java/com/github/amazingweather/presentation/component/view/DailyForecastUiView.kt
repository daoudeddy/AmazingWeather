package com.github.amazingweather.presentation.component.view

import com.github.amazingweather.presentation.base.BaseUiView
import com.github.amazingweather.presentation.component.viewholder.ViewHolderFactory

data class DailyForecastUiView(
    val condition: String,
    val icon: String,
    val date: Long,
    val windSpeed: Int,
    val humidity: Int,
    val temp: Int
) : BaseUiView() {

    override var itemViewType = ViewHolderFactory.DAILY_FORECAST_ITEM
    override fun getIdentifier() = date.toString()
    override var spanSize: Int = 1

    override fun areContentsTheSame(other: BaseUiView): Boolean {
        return other is DailyForecastUiView && other.condition == condition &&
                other.icon == icon && other.windSpeed == windSpeed &&
                other.humidity == humidity && other.temp == temp
    }
}