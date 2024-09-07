package com.melo.dogify.preferences


import android.content.Context
import com.melo.dogify.extensions.set
import com.melo.dogify.preferences.PreferenceConstants.AND_GPT_TOKEN
import com.melo.dogify.preferences.PreferenceConstants.IS_FIRST_TIME_LAUNCH
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DogPreferenceManager @Inject constructor(
    @ApplicationContext
    private val
    context: Context
): DogSharedPreferences(context), Preferences {

    override fun getPrefName() = "DogifyPref"

    override fun setFirstLaunch(isFirstTime: Boolean) {
        prefs.set(
            IS_FIRST_TIME_LAUNCH,
            isFirstTime
        )
    }

    override fun getFirstLaunch(): Boolean {
        return prefs.getBoolean(
            IS_FIRST_TIME_LAUNCH,
            true
        )
    }

    override fun getToken(): String {
        return prefs.getString(
            AND_GPT_TOKEN,
            null
        ).toString()
    }

}