package com.github.amazingweather.core.ext

import com.github.amazingweather.core.EitherErrorOr
import com.github.amazingweather.core.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Response


// Api calls must pass by this function to insure thread safety and exception handling

suspend inline fun <T : Any, R : Any> safeApiCall(
    call: Call<T>,
    crossinline transformation: (T) -> R
): EitherErrorOr<R> = withContext(Dispatchers.IO) {
    try {
        val response: Response<T> = call.execute()
        val responseBody: T? = response.body()

        if (response.isSuccessful && responseBody != null)
            EitherErrorOr.Right(transformation(responseBody))
        else
            EitherErrorOr.Left(Result.Error.ApiError("Api Error"))

    } catch (exception: Exception) {
        EitherErrorOr.Left(exception.toResultError())
    }
}


