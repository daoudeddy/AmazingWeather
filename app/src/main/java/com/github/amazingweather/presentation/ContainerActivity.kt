package com.github.amazingweather.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class ContainerActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // most basic container, if saved instance not null we let android restore the fragment for us
        if (savedInstanceState != null) return
        TODO("open fragment from here")
    }
}