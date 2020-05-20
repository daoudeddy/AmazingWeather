package com.github.amazingweather.presentation.component.view

import com.github.amazingweather.presentation.base.BaseUiView
import com.github.amazingweather.presentation.component.viewholder.ViewHolderFactory

data class CityWeatherUiView(
    val id: Int,
    val name: String,
    val humidity: Int,
    val pressure: Int,
    val temp: Int,
    val tempMax: Int,
    val tempMin: Int,
    val windSpeed: Int,
    val condition: String,
    val icon: String

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
        return if (other !is CityWeatherUiView) super.getChangePayload(other)
        else mutableSetOf<String>().apply {
            if (other.name != name)
                add(NAME_CHANGED)
            if (other.humidity != humidity)
                add(HUMIDITY_CHANGED)
            if (other.pressure != pressure)
                add(PRESSURE_CHANGED)
            if (other.temp != temp)
                add(TEMP_CHANGED)
            if (other.tempMax != tempMax)
                add(TEMP_MAX_CHANGED)
            if (other.tempMin != tempMin)
                add(TEMP_MIN_CHANGED)
            if (other.windSpeed != windSpeed)
                add(WIND_SPEED_CHANGED)
            if (other.condition != condition)
                add(CONDITION_CHANGED)
            if (other.icon != icon)
                add(ICON_CHANGED)
        }
    }

    companion object {
        const val NAME_CHANGED = "NAME_CHANGED"
        const val HUMIDITY_CHANGED = "HUMIDITY_CHANGED"
        const val PRESSURE_CHANGED = "PRESSURE_CHANGED"
        const val TEMP_CHANGED = "TEMP_CHANGED"
        const val TEMP_MAX_CHANGED = "TEMP_MAX_CHANGED"
        const val TEMP_MIN_CHANGED = "TEMP_MIN_CHANGED"
        const val WIND_SPEED_CHANGED = "WIND_SPEED_CHANGED"
        const val CONDITION_CHANGED = "CONDITION_CHANGED"
        const val ICON_CHANGED = "ICON_CHANGED"
    }
}