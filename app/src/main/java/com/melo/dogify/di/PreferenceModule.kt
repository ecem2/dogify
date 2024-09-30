package com.melo.dogify.di

import com.melo.dogify.preferences.DogPreferenceManager
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import com.melo.dogify.preferences.Preferences

@Module
@InstallIn(SingletonComponent::class)
abstract class PreferenceModule {


    @Binds
    abstract fun providePreferences(preferences: DogPreferenceManager): Preferences

}
