package com.melo.dogify.preferences

import android.content.Context

abstract class DogSharedPreferences(context: Context) {

    abstract fun getPrefName(): String

    protected val prefs = context.getSharedPreferences(getPrefName(), Context.MODE_PRIVATE)
}