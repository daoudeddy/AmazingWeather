package com.github.amazingweather.data

import com.github.amazingweather.R

object Keys {
    const val EXTRA_ID = "EXTRA_ID"

    fun getImageUrl(icon: String) = "https://openweathermap.org/img/wn/${icon}@2x.png"

    fun getWindSpeedUnit(): Int =
        if (DataHolder.unit == "metric") R.string.wind_kmh else R.string.wind_mph

    fun getTempUnit(): Int =
        if (DataHolder.unit == "metric") R.string.temp_celsius else R.string.temp_fahrenheit
}