package com.github.amazingweather.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.github.amazingweather.R
import com.github.amazingweather.core.newFragment
import com.github.amazingweather.presentation.ui.main.MainFragment
import dagger.android.support.DaggerAppCompatActivity

class FragmentContainerActivity : DaggerAppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activty_container)
        // most basic container, if saved instance not null we let android restore the fragment for us
        if (savedInstanceState != null) return
        openFragment(newFragment<MainFragment>(), replace = false, addToBackStack = false)
    }

    fun openFragment(
        fragment: Fragment,
        replace: Boolean,
        addToBackStack: Boolean,
        bundle: Bundle? = null
    ) {
        if (bundle == null)
            lifecycleScope.launchWhenStarted {
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
}