package com.github.amazingweather.presentation.ui.main

import androidx.lifecycle.viewModelScope
import com.github.amazingweather.R
import com.github.amazingweather.data.DataHolder
import com.github.amazingweather.domain.citiesweather.CityWeather
import com.github.amazingweather.domain.citiesweather.GetCitiesWeatherUseCase
import com.github.amazingweather.domain.citiesweather.GetCityWeatherUseCase
import com.github.amazingweather.presentation.base.BaseEvent
import com.github.amazingweather.presentation.base.BaseUiView
import com.github.amazingweather.presentation.base.BaseViewModel
import com.github.amazingweather.presentation.component.view.CityWeatherUiView
import com.github.amazingweather.presentation.component.view.ErrorUiView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

data class MainState(
    val ids: List<Int> = mutableListOf(),
    val firstItem: Boolean = true
)

class MainViewModel @Inject constructor(
    getCitiesWeatherUseCase: GetCitiesWeatherUseCase,
    private val getCityWeatherUseCase: GetCityWeatherUseCase
) : BaseViewModel<MainState, GetCitiesWeatherUseCase.Params, List<CityWeather>>(
    MainState(), getCitiesWeatherUseCase
) {
    private val originalList: MutableList<BaseUiView> by lazy { baseState.list.toMutableList() }


    fun fetchData(ids: MutableList<Int>) {
        setState { copy(ids = ids) }
        fetchData(true)
    }

    override fun getParams() = GetCitiesWeatherUseCase.Params(state.ids)

    override suspend fun toUiViews(
        result: List<CityWeather>
    ): MutableList<CityWeatherUiView> = result.map {
        it.toUiView()
    }.toMutableList()

    private fun onItemClick(item: CityWeatherUiView) {
        fireCommand(MainViewModelCommand.OpenForecastFragment(item.id))
    }

    private fun onDoAction(action: suspend () -> Unit) {
        viewModelScope.launch { withContext(Dispatchers.IO) { action.invoke() } }
    }

    private fun CityWeather.toUiView(): CityWeatherUiView {
        return CityWeatherUiView(
            id,
            name,
            humidity,
            pressure,
            temp.toInt(),
            tempMax.toInt(),
            tempMin.toInt(),
            windSpeed.toInt(),
            condition,
            icon
        ).apply {
            doAction = ::onDoAction
            onItemClicked = { onItemClick(this) }
        }
    }

    override fun onItemRemoved(adapterPosition: Int) {
        val cityId = state.ids[adapterPosition]
        DataHolder.citiesId.remove(cityId)
        val item = originalList.map { it as CityWeatherUiView }.first { it.id == cityId }
        setState { copy(ids = DataHolder.citiesId) }
        originalList.removeAll { it.getIdentifier() == item.getIdentifier() }
        setBaseState {
            copy(list = originalList.distinctBy { it.getIdentifier() })
        }
        if (state.ids.isEmpty()) {
            setBaseState {
                copy(
                    list = listOf(
                        ErrorUiView(
                            0,
                            R.string.empty_result_title,
                            R.string.empty_result_subtitle
                        )
                    )
                )
            }
        }
    }

    fun addCity(longitude: Double, latitude: Double) {

        setBaseState { copy(refreshing = true) }
        getCityWeatherUseCase(
            viewModelScope,
            GetCityWeatherUseCase.Params(longitude, latitude),
            onSuccess = {
                if (it.name.isNotEmpty()) {
                    DataHolder.citiesId.add(it.id)
                    setState { copy(ids = DataHolder.citiesId) }
                    originalList.addAll(mutableListOf<BaseUiView>().apply {
                        if (!baseState.list.any { it is ErrorUiView }) addAll(baseState.list)
                        add(it.toUiView())
                    })
                    setBaseState {
                        copy(
                            list = originalList.distinctBy { item -> item.getIdentifier() },
                            refreshing = false
                        )
                    }

                    if (state.firstItem) {
                        fireCommand(MainViewModelCommand.ShowDismissHint)
                        setState { copy(firstItem = false) }
                    }
                } else {
                    setBaseState { copy(refreshing = false) }
                }
            },
            onFailure = {}
        )
    }

    fun filter(query: String) {
        setBaseState {
            copy(list = originalList.map {
                it as CityWeatherUiView
            }.filter {
                query.isEmpty() || it.name.contains(query, true)
            }.distinctBy { it.id })
        }
    }

    sealed class MainViewModelCommand : BaseEvent() {
        data class OpenForecastFragment(val cityId: Int) : MainViewModelCommand()
        object ShowDismissHint : MainViewModelCommand()
    }
}

