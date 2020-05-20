package com.github.amazingweather.domain.citiesweather

import com.github.amazingweather.core.EitherErrorOr
import com.github.amazingweather.core.Result
import com.github.amazingweather.remote.citesweather.WeatherRemoteData
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import junit.framework.TestCase.assertNotNull
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class GetCitiesWeatherUseCaseTest {
    private val citiesId = listOf(524901, 703448, 2643743)

    @MockK
    lateinit var weatherRemoteData: WeatherRemoteData

    lateinit var getCitiesWeatherUseCase: GetCitiesWeatherUseCase

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        getCitiesWeatherUseCase = GetCitiesWeatherUseCase(weatherRemoteData)
    }

    @Test
    fun testExecute_Positive() = runBlocking {
        coEvery { weatherRemoteData.getWeatherByIds(citiesId) } returns EitherErrorOr.Right(
            listOf(
                CityWeather()
            )
        )

        val response = getCitiesWeatherUseCase.run(
            GetCitiesWeatherUseCase.Params(citiesId)
        )

        assert(response is EitherErrorOr.Right)
        assert((response as EitherErrorOr.Right).b.isNotEmpty())
        assertNotNull(response.b.first())
    }

    @Test
    fun testExecute_Negative_NetworkError() = runBlocking {
        coEvery { weatherRemoteData.getWeatherByIds(citiesId) } returns EitherErrorOr.Left(
           Result.Error.NetworkError
        )

        val response = getCitiesWeatherUseCase.run(
            GetCitiesWeatherUseCase.Params(citiesId)
        )

        assert(response is EitherErrorOr.Left)
        assertNotNull(response as? EitherErrorOr.Left)
        assertNotNull((response as EitherErrorOr.Left).a)
        assert(response.a is Result.Error.NetworkError)
    }

    @Test
    fun testExecute_Negative_ApiError() = runBlocking {
        coEvery { weatherRemoteData.getWeatherByIds(citiesId) } returns EitherErrorOr.Left(
           Result.Error.ApiError("Api Error")
        )

        val response = getCitiesWeatherUseCase.run(
            GetCitiesWeatherUseCase.Params(citiesId)
        )

        assert(response is EitherErrorOr.Left)
        assertNotNull(response as? EitherErrorOr.Left)
        assertNotNull((response as EitherErrorOr.Left).a)
        assert(response.a is Result.Error.ApiError)
    }

}