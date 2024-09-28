package com.melo.dogify

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp

class DogifyApplication: Application() {
    override fun onCreate() {
        super.onCreate()


        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
    }

    companion object {
        var hasSubscription : Boolean = true //false olacak
    }
}