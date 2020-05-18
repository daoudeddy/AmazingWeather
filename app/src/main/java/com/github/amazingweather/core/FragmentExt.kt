package com.github.amazingweather.core

import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider.Factory
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.github.amazingweather.presentation.ViewModelFactory

fun <T : Any, L : LiveData<T>> Fragment.observe(liveData: L, body: (T) -> Unit) {
    liveData.observe(viewLifecycleOwner, Observer(body))
}

inline fun <reified T : Fragment> newFragment(vararg params: Pair<String, Any>): T {
    return T::class.java.newInstance().apply {
        arguments = bundleOf(*params)
    }
}

inline fun <reified T> Fragment.args(key: String, default: T) = lazy {
    (arguments?.get(key) ?: default) as T
}