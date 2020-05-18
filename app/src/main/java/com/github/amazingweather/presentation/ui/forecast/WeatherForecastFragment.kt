package com.github.amazingweather.presentation.ui.forecast

import android.os.Bundle
import com.github.amazingweather.core.args
import com.github.amazingweather.data.Keys.EXTRA_ID
import com.github.amazingweather.di.scope.ActivityScoped
import com.github.amazingweather.presentation.base.BaseListFragment
import javax.inject.Inject

@ActivityScoped
class WeatherForecastFragment @Inject constructor() :
    BaseListFragment<WeatherForecastState, WeatherForecastViewModel>(WeatherForecastViewModel::class) {

    private val cityId: Int by args(EXTRA_ID, 0)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        mViewModel.fetchDate(cityId)
    }
}