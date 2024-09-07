package com.melo.dogify

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp

class DogifyApplication: Application() {
    override fun onCreate() {
        super.onCreate()

    }

    companion object {
        var hasSubscription : Boolean = true //false olacak
    }
}