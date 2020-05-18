package com.github.amazingweather.presentation.ui.main

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.view.View
import com.github.amazingweather.R
import com.github.amazingweather.data.Keys.getImageUrl
import com.github.amazingweather.presentation.base.BaseViewHolder
import com.github.amazingweather.presentation.model.CityWeatherUiView
import kotlinx.android.synthetic.main.city_weather_item_layout.view.*
import java.net.URL


class MainViewHolder(itemView: View) : BaseViewHolder<CityWeatherUiView>(itemView) {

    override fun bindView(position: Int, item: CityWeatherUiView) {
        bindCityName(item.name)
        bindWeatherTempAndCondition(item.temp, item.condition)
        bindTempLowHigh(item.tempMin, item.tempMax)
        bindIcon(item)
    }

    private fun bindIcon(item: CityWeatherUiView) {
        item.doAction {
            val url = URL(getImageUrl(item.icon))
            val bmp: Bitmap = BitmapFactory.decodeStream(url.openConnection().getInputStream())
            itemView.cityWeatherIconIV.post { itemView.cityWeatherIconIV.setImageBitmap(bmp) }
        }
    }

    private fun bindTempLowHigh(tempMin: Double, tempMax: Double) {
        itemView.cityTempHighLowTextView.text =
            itemView.resources.getString(R.string.min_max).format(tempMin, tempMax)
    }

    private fun bindWeatherTempAndCondition(temp: Double, condition: String) {
        itemView.cityTempAndCondTextView.text =
            itemView.resources.getString(R.string.weather_condition).format(temp, condition)
    }

    private fun bindCityName(name: String) {
        itemView.cityNameTV.text = name
    }

    override fun bindViewPayloads(position: Int, item: CityWeatherUiView, diffSet: Set<String>) {
        TODO("add payload checking")
    }

}