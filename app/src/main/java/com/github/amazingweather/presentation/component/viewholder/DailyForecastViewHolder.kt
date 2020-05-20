package com.github.amazingweather.presentation.component.viewholder

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.text.SpannableStringBuilder
import android.view.View
import androidx.core.text.bold
import com.github.amazingweather.core.ext.getDayOfTheWeek
import com.github.amazingweather.data.Keys
import com.github.amazingweather.data.Keys.getTempUnit
import com.github.amazingweather.presentation.base.BaseViewHolder
import com.github.amazingweather.presentation.component.view.DailyForecastUiView
import kotlinx.android.synthetic.main.daily_forecast_item_layout.view.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.net.URL

class DailyForecastViewHolder(itemView: View) : BaseViewHolder<DailyForecastUiView>(itemView) {
    override fun bindView(position: Int, item: DailyForecastUiView) = with(item) {
        bindDay(date)
        bindIcon(item)
        bindTemp(temp)
    }

    private fun bindTemp(temp: Int) {
        itemView.tempTextView.text = itemView.resources.getString(getTempUnit()).format(temp)
    }

    private fun bindIcon(item: DailyForecastUiView) {
        item.doAction {
            val url = URL(Keys.getImageUrl(item.icon))
            val bmp: Bitmap = BitmapFactory.decodeStream(url.openConnection().getInputStream())
            withContext(Dispatchers.Main) {
                itemView.weatherIconIV.post { itemView.weatherIconIV.setImageBitmap(bmp) }
            }
        }
    }

    private fun bindDay(date: Long) {
        itemView.forecastDayTV.text = date.getDayOfTheWeek()
    }

    override fun bindViewPayloads(
        position: Int,
        item: DailyForecastUiView,
        diffSet: Set<String>
    ) {

    }
}