package com.github.amazingweather.core.ext

import android.content.pm.PackageManager
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.github.amazingweather.R
import com.github.amazingweather.presentation.ui.activity.FragmentContainerActivity

fun FragmentContainerActivity.openFragment(
    fragment: Fragment,
    replace: Boolean,
    addToBackStack: Boolean,
    bundle: Bundle? = null
) {
    if (bundle == null)
        lifecycleScope.launchWhenStarted {
            hideSearch()
            supportFragmentManager.beginTransaction().apply {
                if (replace)
                    replace(R.id.container, fragment, fragment.javaClass.simpleName)
                else
                    add(R.id.container, fragment, fragment.javaClass.simpleName)

                if (addToBackStack)
                    addToBackStack(fragment.javaClass.simpleName)
                commit()
            }
        }
}

fun IntArray.areAllGranted() = all { it == PackageManager.PERMISSION_GRANTED }
