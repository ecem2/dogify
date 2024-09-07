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

@Module
@InstallIn(SingletonComponent::class)

object AppModule {

    @Provides
    @Singleton

    fun provideContext(@ApplicationContext context: Context): Context = context

}