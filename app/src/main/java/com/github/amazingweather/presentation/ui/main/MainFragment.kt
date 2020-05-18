package com.github.amazingweather.presentation.ui.main

import android.os.Bundle
import com.github.amazingweather.R
import com.github.amazingweather.data.DataHolder
import com.github.amazingweather.di.scope.ActivityScoped
import com.github.amazingweather.presentation.base.BaseListFragment
import javax.inject.Inject

@ActivityScoped
class MainFragment @Inject constructor() : BaseListFragment<MainState, MainViewModel>(
    MainViewModel::class
) {
    override val layoutId: Int = R.layout.fragment_main

    override fun getViewModelDynamicParams(): Array<out Any> {
        return arrayOf(DataHolder.citiesId)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        mViewModel.fetchData(DataHolder.citiesId)
    }
}