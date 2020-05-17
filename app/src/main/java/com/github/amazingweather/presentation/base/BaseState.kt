package com.github.amazingweather.presentation.base

/** Wrapper for any ui state, along with some extra generic properties */

data class BaseState<ExtraState> constructor(
    val state: ExtraState,
    val list: List<BaseUiView>,
    val refreshing: Boolean
)
