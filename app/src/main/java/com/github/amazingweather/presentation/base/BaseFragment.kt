package com.github.amazingweather.presentation.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import com.github.amazingweather.presentation.ui.activity.FragmentContainerActivity
import dagger.android.support.DaggerFragment
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

abstract class BaseFragment : DaggerFragment() {

    abstract val layoutId: Int

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflater.inflate(layoutId, container, false)

    fun getBaseActivity() = activity as FragmentContainerActivity

    fun espressoTestIdle(delayTime:Long){
        lifecycleScope.launch {
            getBaseActivity().testIdlingResource?.setIdleState(false)
            delay(delayTime)
            getBaseActivity().testIdlingResource?.setIdleState(true)
        }
    }
}