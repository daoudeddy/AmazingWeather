package com.github.amazingweather.core

sealed class Result<T> {

    class Success<T>(val data: T)

    sealed class Error : Result<Nothing>() {

        object NetworkError : Error()

        class ApiError(val message: String) : Error()

        class Exception(val throwable: Throwable) : Error()
    }

}