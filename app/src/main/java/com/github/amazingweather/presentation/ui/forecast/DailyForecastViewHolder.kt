package com.github.amazingweather.presentation.ui.forecast

import android.view.View
import com.github.amazingweather.presentation.base.BaseViewHolder

class DailyForecastViewHolder(itemView: View) : BaseViewHolder<DailyForecastUiView>(itemView) {
    override fun bindView(position: Int, item: DailyForecastUiView) {
        
    }

    override fun bindViewPayloads(
        position: Int,
        item: DailyForecastUiView,
        diffSet: Set<String>
    ) {
        TODO("add payload check")
    }
}