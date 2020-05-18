package com.github.amazingweather.presentation.ui.main

import androidx.lifecycle.viewModelScope
import com.github.amazingweather.domain.citiesweather.CityWeather
import com.github.amazingweather.domain.citiesweather.GetCitiesWeatherUseCase
import com.github.amazingweather.presentation.base.BaseEvent
import com.github.amazingweather.presentation.base.BaseViewModel
import com.github.amazingweather.presentation.model.CityWeatherUiView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

data class MainState(val ids: List<Int> = mutableListOf())

class MainViewModel @Inject constructor(
    getCitiesWeatherUseCase: GetCitiesWeatherUseCase
) : BaseViewModel<MainState, GetCitiesWeatherUseCase.Params, List<CityWeather>>(
    MainState(), getCitiesWeatherUseCase
) {

    fun fetchData(ids: MutableList<Int>) {
        setState { copy(ids = ids) }
        fetchData(false)
    }

    override fun getParams() = GetCitiesWeatherUseCase.Params(state.ids)

    override suspend fun toUiViews(
        result: List<CityWeather>
    ): MutableList<CityWeatherUiView> = result.map {
        it.toUiView().apply {
            doAction = ::onDoAction
        }
    }.toMutableList()

    private fun onDoAction(action: () -> Unit) {
        viewModelScope.launch { withContext(Dispatchers.IO) { action.invoke() } }
    }

    private fun CityWeather.toUiView(): CityWeatherUiView {
        return CityWeatherUiView(
            id,
            name,
            humidity,
            pressure,
            temp,
            tempMax,
            tempMin,
            windSpeed,
            condition,
            icon
        )
    }

    override fun onItemRemoved(adapterPosition: Int) {
        val cityId = state.ids[adapterPosition]
        setBaseState { copy(list = list.filter { it.getIdentifier() != cityId.toString() }) }
        setState { copy(ids = ids.filter { it != cityId }) }
    }

    sealed class MainViewModelCommand : BaseEvent() {

    }
}

