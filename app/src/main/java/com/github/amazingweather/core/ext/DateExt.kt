package com.github.amazingweather.core.ext

import java.text.SimpleDateFormat
import java.util.*

fun Long.getDayOfTheWeek(): String {
    return SimpleDateFormat("EEE", Locale.US).format(Date(this * 1000))
}