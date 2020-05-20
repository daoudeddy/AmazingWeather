package com.github.amazingweather.domain.forecast

import com.github.amazingweather.core.EitherErrorOr
import com.github.amazingweather.core.Result
import com.github.amazingweather.remote.weatherforecast.WeatherForecastRemoteData
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import junit.framework.TestCase.assertNotNull
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class WeatherForecastUseCaseTest {

    private val cityId = 18837

    @MockK
    lateinit var weatherForecastRemoteData: WeatherForecastRemoteData

    lateinit var getRepositoriesUseCase: WeatherForecastUseCase

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        getRepositoriesUseCase = WeatherForecastUseCase(weatherForecastRemoteData)
    }

    @Test
    fun testExecute_Positive() = runBlocking {
        coEvery { weatherForecastRemoteData.getWeatherForecast(cityId) } returns EitherErrorOr.Right(
            WeatherForecast().apply {
                forecast.add(DailyForecast())
            }
        )

        val response = getRepositoriesUseCase.run(
            WeatherForecastUseCase.Params(cityId)
        )

        assert(response is EitherErrorOr.Right)
        assertNotNull((response as EitherErrorOr.Right).b)
        assert(response.b.forecast.isNotEmpty())
    }

    @Test
    fun testExecute_Negative_ApiError() = runBlocking {
        coEvery { weatherForecastRemoteData.getWeatherForecast(cityId) } returns EitherErrorOr.Left(
            Result.Error.ApiError("Api Error")
        )

        val response = getRepositoriesUseCase.run(
            WeatherForecastUseCase.Params(cityId)
        )

        assert(response is EitherErrorOr.Left)
        assertNotNull(response as? EitherErrorOr.Left)
        assertNotNull((response as EitherErrorOr.Left).a)
        assert(response.a is Result.Error.ApiError)
    }

    @Test
    fun testExecute_Negative_NetworkError() = runBlocking {
        coEvery { weatherForecastRemoteData.getWeatherForecast(cityId) } returns EitherErrorOr.Left(
            Result.Error.NetworkError
        )

        val response = getRepositoriesUseCase.run(
            WeatherForecastUseCase.Params(cityId)
        )

        assert(response is EitherErrorOr.Left)
        assertNotNull(response as? EitherErrorOr.Left)
        assertNotNull((response as EitherErrorOr.Left).a)
        assert(response.a is Result.Error.NetworkError)
    }
}