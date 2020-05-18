package com.github.amazingweather.core

import android.util.Log
import kotlinx.coroutines.*

/**
This class belongs to the domain or business layer.
To extend this class whenever we need to move to the background thread and come back to Main Thread to update for example the UI.
Built using Kotlin Coroutines for easy context switching.
Main purpose to communicate with data layer however it can also be use as a switch between bg and ui threads
*/

abstract class UseCase<ParamsType, ResultType> {

    private var parent: Job = SupervisorJob()
    private lateinit var scope: CoroutineScope

    private fun getHandler(onFailure: (errorResult: Result.Error) -> Unit) =
        CoroutineExceptionHandler { _, exception ->
            Log.e("BaseDataResultUseCase", exception.message, exception)
            onFailure(Result.Error.Exception(exception))
        }

    abstract suspend fun run(params: ParamsType): EitherErrorOr<ResultType>

    operator fun invoke(
        scope: CoroutineScope,
        params: ParamsType,
        onSuccess: suspend (ResultType) -> Unit = {},
        onFailure: (errorResult: Result.Error) -> Unit = {}
    ) {
        this.scope = scope
        parent.cancel()
        parent = SupervisorJob()

        this.scope.launch(Dispatchers.IO + parent + getHandler(onFailure)) {
            val result = run(params)
            withContext(Dispatchers.Main) {
                result.fold({ onFailure(it) }) { onSuccess(it) }
            }
        }
    }
}