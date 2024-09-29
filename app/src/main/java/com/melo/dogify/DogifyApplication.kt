package com.melo.dogify

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import com.google.firebase.ktx.BuildConfig
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp

class DogifyApplication: Application() {

    companion object {
        const val isPremium = false // Testte premium acmak icin true yap
        var hasSubscription = if (BuildConfig.DEBUG) {
            isPremium
        } else {
            false
        }
    }
    override fun onCreate() {
        super.onCreate()


        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
    }

}