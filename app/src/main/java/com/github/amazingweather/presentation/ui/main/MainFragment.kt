package com.github.amazingweather.presentation.ui.main

import android.os.Bundle
import android.view.MenuItem
import com.github.amazingweather.R
import com.github.amazingweather.core.ext.newFragment
import com.github.amazingweather.core.ext.openFragment
import com.github.amazingweather.core.ext.snackbar
import com.github.amazingweather.data.DataHolder
import com.github.amazingweather.data.Keys.EXTRA_ID
import com.github.amazingweather.di.scope.ActivityScoped
import com.github.amazingweather.presentation.base.BaseEvent
import com.github.amazingweather.presentation.base.BaseListFragment
import com.github.amazingweather.presentation.ui.forecast.WeatherForecastFragment
import com.github.amazingweather.presentation.ui.map.MapsFragment
import com.github.amazingweather.presentation.ui.settings.SettingsFragment
import kotlinx.android.synthetic.main.fragment_main.*
import javax.inject.Inject


@ActivityScoped
class MainFragment @Inject constructor() : BaseListFragment<MainState, MainViewModel>(
    MainViewModel::class
), MapsFragment.FragmentCallback, SettingsFragment.FragmentCallback {

    override val layoutId: Int = R.layout.fragment_main

    override fun getViewModelDynamicParams(): Array<out Any> {
        return arrayOf(DataHolder.citiesId)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        mViewModel.fetchData(DataHolder.citiesId)
        bar.setOnMenuItemClickListener {
            handleItemClicked(it); true
        }
        fab.setOnClickListener {
            getBaseActivity().openFragment(
                newFragment<MapsFragment>().apply { addCallback(this@MainFragment) },
                replace = false,
                addToBackStack = true
            )
        }
    }

    private fun handleItemClicked(item: MenuItem) {
        when (item.itemId) {
            R.id.search -> getBaseActivity().showSearch(::filterSearch)
            R.id.settings -> getBaseActivity().openFragment(
                newFragment<SettingsFragment>()
                    .apply { setCallback(this@MainFragment) },
                replace = false,
                addToBackStack = true
            )
        }
    }


    private fun filterSearch(query: String) {
        mViewModel.filter(query)
    }

    override suspend fun onNewCommand(it: BaseEvent) {
        when (it) {
            is MainViewModel.MainViewModelCommand.OpenForecastFragment -> {
                getBaseActivity().openFragment(
                    newFragment<WeatherForecastFragment>(
                        EXTRA_ID to it.cityId
                    ),
                    replace = false,
                    addToBackStack = true
                )
            }

            is MainViewModel.MainViewModelCommand.ShowDismissHint -> {
                view?.snackbar(R.string.dismiss_card)
            }
        }
    }

    override fun onPlaceAdded(longitude: Double, latitude: Double) {
        mViewModel.addCity(longitude, latitude)
    }

    override fun onSettingsChanged() {
        mViewModel.fetchData(true)
    }
}