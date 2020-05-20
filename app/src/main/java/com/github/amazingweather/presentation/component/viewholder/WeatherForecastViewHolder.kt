package com.github.amazingweather.presentation.component.viewholder

import android.view.View
import com.github.amazingweather.R
import com.github.amazingweather.core.ext.getDayOfTheWeek
import com.github.amazingweather.data.Keys.getTempUnit
import com.github.amazingweather.data.Keys.getWindSpeedUnit
import com.github.amazingweather.presentation.base.BaseViewHolder
import com.github.amazingweather.presentation.component.UiAdapter
import com.github.amazingweather.presentation.component.view.DailyForecastUiView
import com.github.amazingweather.presentation.component.view.WeatherForecastUiView
import kotlinx.android.synthetic.main.weather_forecast_item_layout.view.*

class WeatherForecastViewHolder(itemView: View) : BaseViewHolder<WeatherForecastUiView>(itemView) {

    private val adapter by lazy { UiAdapter() }

    override fun bindView(position: Int, item: WeatherForecastUiView) = with(item) {
        bindTemp(temp)
        bindNameAndDay(name, date)
        bindCondition(condition)
        bindWindAndHumidity(windSpeed, humidity)
        bindForecast(forecast)
    }

    private fun bindForecast(forecast: List<DailyForecastUiView>) {
        itemView.weatherForecastRecyclerView.layoutManager = adapter.layoutManager(itemView.context)
        itemView.weatherForecastRecyclerView.adapter = adapter
        adapter.submitList(forecast)
    }

    private fun bindWindAndHumidity(windSpeed: Int, humidity: Int) {
        itemView.lowHighTempTV.text =
            itemView.resources.getString(R.string.wind_and_humidity).format(
                itemView.resources.getString(
                    getWindSpeedUnit()
                ).format(windSpeed),
                itemView.resources.getString(R.string.humidity).format(humidity)
            )
    }

    private fun bindCondition(condition: String) {
        itemView.forecastConditionTV.text = condition
    }

    private fun bindNameAndDay(name: String, date: Long) {
        itemView.dayAndLocationTV.text = itemView.resources.getString(
            R.string.name_and_day
        ).format(
            name,
            date.getDayOfTheWeek()
        )
    }

    private fun bindTemp(temp: Int) {
        itemView.forecastTempTV.text = itemView.resources.getString(getTempUnit()).format(temp)
    }

    override fun bindViewPayloads(
        position: Int,
        item: WeatherForecastUiView,
        diffSet: Set<String>
    ) {

    }

}