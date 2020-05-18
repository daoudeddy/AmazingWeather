package com.github.amazingweather.presentation.ui.forecast

import android.view.View
import com.github.amazingweather.presentation.base.BaseViewHolder

class WeatherForecastViewHolder (itemView: View):BaseViewHolder<WeatherForecastUiView>(itemView){
    override fun bindView(position: Int, item: WeatherForecastUiView) {

    }

    override fun bindViewPayloads(
        position: Int,
        item: WeatherForecastUiView,
        diffSet: Set<String>
    ) {
        TODO("add payload check")
    }

}