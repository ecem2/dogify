package com.melo.dogify.preferences

import android.content.Context

abstract class DogSharedPreferences(context: Context) {

    abstract fun getPrefName(): String

    protected val prefs = context.getSharedPreferences(this.getPrefName(), Context.MODE_PRIVATE)

}