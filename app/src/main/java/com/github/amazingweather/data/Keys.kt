package com.github.amazingweather.data

object Keys {
    const val EXTRA_ID = "EXTRA_ID"

    fun getImageUrl(icon: String) = "https://openweathermap.org/img/wn/${icon}@2x.png"
}