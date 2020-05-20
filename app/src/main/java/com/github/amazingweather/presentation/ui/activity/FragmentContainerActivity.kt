package com.github.amazingweather.presentation.ui.activity

import android.os.Bundle
import android.text.TextWatcher
import android.view.MenuItem
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.inputmethod.InputMethodManager
import android.view.inputmethod.InputMethodManager.SHOW_IMPLICIT
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat.getSystemService
import androidx.core.view.isVisible
import com.github.amazingweather.R
import com.github.amazingweather.core.ext.afterTextChanged
import com.github.amazingweather.core.ext.areAllGranted
import com.github.amazingweather.core.ext.newFragment
import com.github.amazingweather.core.ext.openFragment
import com.github.amazingweather.presentation.ui.main.MainFragment
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activty_container.*

class FragmentContainerActivity : DaggerAppCompatActivity() {

    private var textWatcher: TextWatcher? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activty_container)
        setSupportActionBar(toolbar)
        supportFragmentManager.addOnBackStackChangedListener {
            supportActionBar?.setDisplayHomeAsUpEnabled(supportFragmentManager.fragments.count() > 1)
        }

        // most basic container, if saved instance not null we let android restore the fragment for us
        if (savedInstanceState != null) return
        openFragment(newFragment<MainFragment>(), replace = false, addToBackStack = false)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                onBackPressed(); false
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    companion object {
        var permissionCallback: ((Int, Array<out String>, IntArray) -> Unit)? = null
    }

    fun requestPermissions(
        permissions: Array<String>,
        requestCode: Int,
        onSuccess: () -> Unit,
        onFailure: () -> Unit = {}
    ) {
        requestPermissions(permissions, requestCode) { code, _, grantResults ->
            if (requestCode == code) {
                if (grantResults.areAllGranted()) {
                    onSuccess()
                } else {
                    onFailure()
                }
            }
        }
    }

    private fun requestPermissions(
        permissions: Array<String>,
        requestCode: Int,
        callback: ((Int, Array<out String>, IntArray) -> Unit)
    ) {
        permissionCallback = callback
        ActivityCompat.requestPermissions(this, permissions, requestCode)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if (permissionCallback != null) {
            permissionCallback?.invoke(requestCode, permissions, grantResults)
            permissionCallback = null
        } else super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    override fun onBackPressed() {
        if (searchContainer.isVisible) {
            hideSearch()
        } else
            super.onBackPressed()
    }

    fun showSearch(onQueryChange: (String) -> Unit) {
        searchContainer.visibility = VISIBLE

        searchClear.setOnClickListener {
            if (searchView.text.isNullOrEmpty()) {
                hideSearch()
            } else {
                searchView.setText("")
            }
        }

        textWatcher = searchView.afterTextChanged(onQueryChange)
        searchView.requestFocus()
        getSystemService(this, InputMethodManager::class.java)?.showSoftInput(
            searchView,
            SHOW_IMPLICIT
        )
    }

    fun hideSearch() {
        if (searchContainer.isVisible) {
            if (searchView.text?.isNotEmpty() == true) searchView.setText("")
            searchView.removeTextChangedListener(textWatcher)
            searchView.setText("")
            searchView.clearFocus()
            searchContainer.visibility = GONE
            getSystemService(this, InputMethodManager::class.java)?.hideSoftInputFromWindow(
                searchView.windowToken,
                SHOW_IMPLICIT
            )
        }
    }
}