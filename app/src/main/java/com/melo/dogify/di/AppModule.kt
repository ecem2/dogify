package com.melo.dogify.di

import android.content.Context
import com.melo.dogify.preferences.DogPreferenceManager
import com.melo.dogify.preferences.DogSharedPreferences
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object AppModule {

    @Singleton
    @Provides
    fun providePreferences(context: Context): DogSharedPreferences =
        DogPreferenceManager(context)

}