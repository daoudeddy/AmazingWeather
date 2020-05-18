package com.github.amazingweather.presentation.component

import android.view.LayoutInflater
import android.view.ViewGroup
import com.github.amazingweather.R
import com.github.amazingweather.presentation.base.BaseUiView
import com.github.amazingweather.presentation.base.BaseViewHolder
import com.github.amazingweather.presentation.ui.main.MainViewHolder

/** This class returns the right view holder for the right type */

object ViewHolderFactory {

    const val EMPTY = 0
    const val ERROR = 1
    const val CITY_WEATHER_ITEM = 2
    const val WEATHER_FORECAST_ITEM = 3
    const val DAILY_FORECAST_ITEM = 4

    inline fun <reified I : BaseUiView, reified T : BaseViewHolder<I>> getViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): T {
        val layoutRest = getLayoutResFromViewType(viewType)
        val view = LayoutInflater.from(parent.context).inflate(layoutRest, null)

        return when (viewType) {
            CITY_WEATHER_ITEM -> MainViewHolder(view)
            WEATHER_FORECAST_ITEM -> MainViewHolder(view)
            DAILY_FORECAST_ITEM -> MainViewHolder(view)
            else -> EmptyViewHolder(view)
        } as T
    }

    fun getLayoutResFromViewType(viewType: Int) = when (viewType) {
        CITY_WEATHER_ITEM -> R.layout.city_weather_item_layout
        WEATHER_FORECAST_ITEM -> R.layout.weather_forecast_item_layout
        DAILY_FORECAST_ITEM -> R.layout.daily_forecast_item_layout
        else -> R.layout.empty_layout
    }
}