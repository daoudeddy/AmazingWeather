package com.github.amazingweather.network

import com.github.amazingweather.data.DataHolder
import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import okio.IOException

class QueryInterceptor : Interceptor {

    private val API_KEY = "43663a13097e8b65dc6312b99cd32e88"

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        var request: Request = chain.request()
        val url: HttpUrl = request.url.newBuilder()
            .addQueryParameter("appid", API_KEY)
            .addQueryParameter("units", DataHolder.unit)
            .build()
        request = request.newBuilder().url(url).build()
        return chain.proceed(request)
    }
}