package com.github.amazingweather.presentation.model

class Event<out T>(private val content: T) {

    private var hasBeenHandled: Boolean  = false

    // Returns the content and prevents its use again.
    fun getContentIfNotHandled(): T? = if (hasBeenHandled) null else {
        hasBeenHandled = true
        content
    }
}