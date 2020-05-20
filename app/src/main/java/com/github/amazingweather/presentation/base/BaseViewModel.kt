package com.github.amazingweather.presentation.base;

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.amazingweather.R
import com.github.amazingweather.core.Result
import com.github.amazingweather.core.UseCase
import com.github.amazingweather.presentation.component.view.ErrorUiView
import com.github.amazingweather.presentation.model.Event
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/** Will handle fetching data, loading more data and mapping the data from domain to ui layer
Exposes some public functions to update the base state and the specific fragment state
Too easy to add a local data source, the mediator livedata can accept a new source of data when needed
 */

abstract class BaseViewModel<StateType : Any, ParamsType, ResultType>(
    private val mExtraState: StateType,
    private val fetchDataUseCase: UseCase<ParamsType, ResultType>? = null
) : ViewModel() {

    private val commands: SingleLiveData<Event<BaseEvent>> = SingleLiveData()

    @PublishedApi
    internal val liveDataState: MediatorLiveData<BaseState<StateType>> = MediatorLiveData()

    @PublishedApi
    internal val baseState: BaseState<StateType>
        get() = liveDataState.value!!.copy()

    @PublishedApi
    internal val state: StateType
        get() = baseState.state

    init {
        setInitialUiState()
    }

    abstract fun getParams(): ParamsType

    open suspend fun toUiViews(result: ResultType): List<BaseUiView> = emptyList()

    private fun setInitialUiState() {
        liveDataState.value = BaseState(
            state = mExtraState,
            list = emptyList(),
            refreshing = false
        )
    }

    fun getLiveDataState(): LiveData<BaseState<StateType>> = liveDataState

    @Suppress("unused")
    inline fun setState(func: StateType.() -> StateType) {
        setBaseState { copy(state = state.func()) }
    }

    inline fun setBaseState(func: BaseState<StateType>.() -> BaseState<StateType>) {
        liveDataState.value = baseState.func()
    }

    fun getCommandsLiveData() = commands

    fun <T : BaseEvent> fireCommand(command: T) {
        getCommandsLiveData().value =
            Event(command)
    }

    fun fetchData(refreshing: Boolean) {

        suspend fun onFetchDataSuccess(result: ResultType) {
            val mappedViews: List<BaseUiView> = withContext(Dispatchers.Default) {
                toUiViews(result)
            }

            val listToRender = if (mappedViews.isEmpty()) listOf(
                ErrorUiView(
                    R.drawable.empty_result,
                    R.string.empty_result_title,
                    R.string.empty_result_subtitle
                )
            ) else mappedViews

            setBaseState { copy(list = listToRender, refreshing = false) }
        }

        @Suppress("unused_parameter")
        fun onFetchDataFailure(error: Result.Error) {

            val errorUiView: List<ErrorUiView>? =
                when (error) {
                    is Result.Error.ApiError -> listOf(
                        ErrorUiView(
                            R.drawable.ic_server_error,
                            R.string.server_error_title,
                            R.string.server_error_subitle
                        )

                    )
                    is Result.Error.NetworkError -> listOf(
                        ErrorUiView(
                            R.drawable.ic_network_error,
                            R.string.network_error_title,
                            R.string.network_error_subitle
                        )
                    )
                    is Result.Error.Exception -> {
                        error.throwable.printStackTrace(); null
                    }
                }

            if (errorUiView != null)
                setBaseState { copy(refreshing = false, list = errorUiView) }
            else
                setBaseState { copy(refreshing = false) }
        }

        fetchDataUseCase?.run {
            setBaseState { copy(refreshing = refreshing) }
            invoke(
                params = getParams(),
                scope = viewModelScope,
                onSuccess = ::onFetchDataSuccess,
                onFailure = ::onFetchDataFailure
            )
        }
    }

    open fun onItemRemoved(adapterPosition: Int) {

    }

}

