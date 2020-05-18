package com.github.amazingweather.presentation.model

import com.github.amazingweather.presentation.base.BaseUiView
import com.github.amazingweather.presentation.component.ViewHolderFactory

data class CityWeatherUiView(
    val id: Int = 0,
    val name: String = "",
    val humidity: Int = 0,
    val pressure: Int = 0,
    val temp: Double = 0.0,
    val tempMax: Double = 0.0,
    val tempMin: Double = 0.0,
    val windSpeed: Double = 0.0,
    val condition: String = "",
    val icon: String = ""

) : BaseUiView() {

    override var itemViewType: Int = ViewHolderFactory.CITY_WEATHER_ITEM

    override fun getIdentifier(): String {
        return id.toString()
    }

    override fun areContentsTheSame(other: BaseUiView): Boolean {
        return other is CityWeatherUiView &&
                other.name == name && other.humidity == humidity &&
                other.pressure == pressure && other.temp == temp &&
                other.tempMax == tempMax && other.tempMin == tempMin &&
                other.windSpeed == windSpeed
    }

    override fun getChangePayload(other: BaseUiView): Set<String> {
        return super.getChangePayload(other)
    }
}