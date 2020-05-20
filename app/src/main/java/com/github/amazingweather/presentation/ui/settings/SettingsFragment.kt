package com.github.amazingweather.presentation.ui.settings

import android.os.Bundle
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.preference.ListPreference
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.SwitchPreference
import com.github.amazingweather.R
import com.github.amazingweather.core.ext.setDefaultUiMode
import com.github.amazingweather.data.DataHolder


class SettingsFragment : PreferenceFragmentCompat() {

    private var callback: FragmentCallback? = null

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        addPreferencesFromResource(R.xml.app_preferences)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = super.onCreateView(inflater, container, savedInstanceState)
        val typedValue = TypedValue()
        context?.theme?.resolveAttribute(R.attr.colorSurface, typedValue, true)
        view?.setBackgroundColor(ContextCompat.getColor(requireContext(), typedValue.resourceId))
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        findPreference<SwitchPreference>(getString(R.string.use_metric))?.apply {
            isChecked = DataHolder.unit == "metric"
            setOnPreferenceChangeListener { _, newValue ->
                DataHolder.unit = if (newValue == true) "metric" else "imperial"
                callback?.onSettingsChanged()
                return@setOnPreferenceChangeListener true
            }
        }

        findPreference<ListPreference>(getString(R.string.app_theme))?.apply {
            setOnPreferenceChangeListener { _, newValue ->
                context?.setDefaultUiMode(newValue as String)
                return@setOnPreferenceChangeListener true
            }
        }
    }

    fun setCallback(fragmentCallback: FragmentCallback) {
        callback = fragmentCallback
    }

    interface FragmentCallback {
        fun onSettingsChanged()
    }
}