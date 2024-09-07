package com.melo.dogify.preferences

interface Preferences {

    fun setFirstLaunch(isFirstTime: Boolean)

    fun getFirstLaunch(): Boolean

    fun getToken(): String
}