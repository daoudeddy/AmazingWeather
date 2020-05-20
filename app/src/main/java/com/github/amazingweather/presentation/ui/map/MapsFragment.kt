package com.github.amazingweather.presentation.ui.map

import android.Manifest
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.github.amazingweather.R
import com.github.amazingweather.core.ext.snackbar
import com.github.amazingweather.di.scope.ActivityScoped
import com.github.amazingweather.presentation.base.BaseFragment
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.android.synthetic.main.activity_maps.*
import kotlinx.android.synthetic.main.activity_maps.view.*
import javax.inject.Inject


@ActivityScoped
class MapsFragment @Inject constructor() : BaseFragment(), OnMapReadyCallback {

    private lateinit var callback: FragmentCallback
    override val layoutId: Int = R.layout.activity_maps

    private lateinit var mMap: GoogleMap
    private var marker: Marker? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val rootView = super.onCreateView(inflater, container, savedInstanceState)
        rootView.mapsView?.onCreate(savedInstanceState)
        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mapsView?.getMapAsync(this)
    }

    override fun onResume() {
        super.onResume()
        mapsView?.onResume()
    }

    override fun onPause() {
        super.onPause()
        mapsView?.onPause()
    }

    override fun onStart() {
        super.onStart()
        mapsView?.onStart()
    }

    override fun onStop() {
        super.onStop()
        mapsView?.onStop()
    }

    override fun onDestroy() {
        super.onDestroy()
        mapsView?.onDestroy()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        mapsView?.onLowMemory()
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        getBaseActivity().requestPermissions(
            arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
            LOCATION_REQ_CODE,
            onSuccess = { mMap.isMyLocationEnabled = true })

        mMap.setOnMapClickListener { point ->
            mMap.moveCamera(CameraUpdateFactory.newLatLng(point))
            marker?.remove()
            marker = mMap.addMarker(MarkerOptions().position(point))

            view?.snackbar(R.string.new_location_added, R.string.add_city) {
                callback.onPlaceAdded(point.longitude, point.latitude)
                getBaseActivity().supportFragmentManager.apply {
                    beginTransaction()
                        .remove(this@MapsFragment)
                        .commit()

                    popBackStack()
                }
            }
        }
    }

    fun addCallback(fragmentCallback: FragmentCallback) {
        callback = fragmentCallback
    }


    interface FragmentCallback {
        fun onPlaceAdded(longitude: Double, latitude: Double)
    }

    companion object {
        const val LOCATION_REQ_CODE = 12
    }
}

