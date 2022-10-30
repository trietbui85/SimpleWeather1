package com.bmtriet.simpleweather.presentation.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bmtriet.simpleweather.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .add(R.id.fragmentContainerView, ForecastFragment()).commit()
        }
    }
}
