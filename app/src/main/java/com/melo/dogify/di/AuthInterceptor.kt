package com.melo.dogify.di

import com.melo.dogify.preferences.Preferences
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class AuthInterceptor @Inject constructor(
    private val preferences: Preferences
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {

        val newRequestBuilder = chain.request().newBuilder()

        return chain.proceed(newRequestBuilder.build())
    }
}