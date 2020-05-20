package com.github.amazingweather.presentation.component.viewholder

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.view.View
import com.github.amazingweather.R
import com.github.amazingweather.data.Keys
import com.github.amazingweather.data.Keys.getImageUrl
import com.github.amazingweather.data.Keys.getWindSpeedUnit
import com.github.amazingweather.presentation.base.BaseViewHolder
import com.github.amazingweather.presentation.component.view.CityWeatherUiView
import com.github.amazingweather.presentation.component.view.CityWeatherUiView.Companion.CONDITION_CHANGED
import com.github.amazingweather.presentation.component.view.CityWeatherUiView.Companion.HUMIDITY_CHANGED
import com.github.amazingweather.presentation.component.view.CityWeatherUiView.Companion.ICON_CHANGED
import com.github.amazingweather.presentation.component.view.CityWeatherUiView.Companion.NAME_CHANGED
import com.github.amazingweather.presentation.component.view.CityWeatherUiView.Companion.TEMP_CHANGED
import com.github.amazingweather.presentation.component.view.CityWeatherUiView.Companion.TEMP_MAX_CHANGED
import com.github.amazingweather.presentation.component.view.CityWeatherUiView.Companion.TEMP_MIN_CHANGED
import com.github.amazingweather.presentation.component.view.CityWeatherUiView.Companion.WIND_SPEED_CHANGED
import kotlinx.android.synthetic.main.city_weather_item_layout.view.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.net.URL


class MainViewHolder(itemView: View) : BaseViewHolder<CityWeatherUiView>(itemView) {

    override fun bindView(position: Int, item: CityWeatherUiView) {
        bindCityName(item.name)
        bindWeatherTempAndCondition(item.temp, item.condition)
        bindTempLowHigh(item.tempMin, item.tempMax)
        bindIcon(item)
        bindWind(item.windSpeed)
        bindHumidity(item.humidity)
    }

    private fun bindHumidity(humidity: Int) {
        itemView.humidityTextView.text =
            itemView.resources.getString(R.string.humidity).format(humidity)
    }

    private fun bindWind(windSpeed: Int) {
        itemView.windTextView.text =
            itemView.resources.getString(getWindSpeedUnit()).format(windSpeed)

    }

    private fun bindIcon(item: CityWeatherUiView) {
        item.doAction {
            val url = URL(getImageUrl(item.icon))
            val bmp: Bitmap = BitmapFactory.decodeStream(url.openConnection().getInputStream())
            withContext(Dispatchers.Main) {
                itemView.cityWeatherIconIV.post { itemView.cityWeatherIconIV.setImageBitmap(bmp) }
            }
        }
    }

    private fun bindTempLowHigh(tempMin: Int, tempMax: Int) {
        itemView.cityTempHighLowTextView.text =
            itemView.resources.getString(R.string.min_max).format(tempMax, tempMin)
    }

    private fun bindWeatherTempAndCondition(temp: Int, condition: String) {
        itemView.cityTempAndCondTextView.text =
            itemView.resources.getString(R.string.weather_condition).format(
                itemView.resources.getString(
                    Keys.getTempUnit()
                ).format(temp), condition
            )
    }

    private fun bindCityName(name: String) {
        itemView.cityNameTV.text = name
    }

    override fun bindViewPayloads(position: Int, item: CityWeatherUiView, diffSet: Set<String>) {
        diffSet.forEach {
            when (it) {
                ICON_CHANGED -> bindIcon(item)
                CONDITION_CHANGED, TEMP_CHANGED -> bindWeatherTempAndCondition(
                    item.temp,
                    item.condition
                )
                NAME_CHANGED -> bindCityName(item.name)
                HUMIDITY_CHANGED -> bindHumidity(item.humidity)
                TEMP_MAX_CHANGED, TEMP_MIN_CHANGED -> bindTempLowHigh(item.tempMin, item.tempMax)
                WIND_SPEED_CHANGED -> bindWind(item.windSpeed)
            }
        }
    }

}