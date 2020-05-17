package com.github.amazingweather.core

import java.net.SocketTimeoutException
import javax.net.ssl.SSLException

fun Exception.toResultError() = when (this) {
    is SocketTimeoutException, is SSLException -> Result.Error.NetworkError
    else -> Result.Error.Exception(this)
}