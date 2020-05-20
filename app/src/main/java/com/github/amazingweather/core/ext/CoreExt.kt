package com.github.amazingweather.core.ext

import android.content.Context
import androidx.appcompat.app.AppCompatDelegate
import androidx.preference.PreferenceManager
import com.github.amazingweather.R
import com.github.amazingweather.core.Result
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import javax.net.ssl.SSLException

fun Exception.toResultError() = when (this) {
    is UnknownHostException, is SocketTimeoutException, is SSLException -> Result.Error.NetworkError
    else -> Result.Error.Exception(this)
}

fun Context.setDefaultUiMode(uiMode: String? = null) {
    when (
        uiMode ?: PreferenceManager.getDefaultSharedPreferences(this.applicationContext).getString(getString(R.string.app_theme), "2")
        ) {
        "0" -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        "1" -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        else -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
    }
}