package com.github.amazingweather.core.ext

import android.view.View
import androidx.annotation.StringRes
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.google.android.material.snackbar.Snackbar

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

fun View.snackbar(@StringRes text: Int, @StringRes buttonText: Int, action: () -> Unit) {
    Snackbar
        .make(this, text, Snackbar.LENGTH_LONG)
        .setAction(buttonText) { action() }
        .show()
}

fun View.snackbar(@StringRes text: Int) {
    Snackbar
        .make(this, text, Snackbar.LENGTH_LONG)
        .show()
}
