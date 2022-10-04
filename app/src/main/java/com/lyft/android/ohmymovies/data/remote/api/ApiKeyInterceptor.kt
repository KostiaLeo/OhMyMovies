package com.lyft.android.ohmymovies.data.remote.api

import com.lyft.android.ohmymovies.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

private const val API_KEY_NAME = "api_key"

class ApiKeyInterceptor @Inject constructor(): Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val requestWithApiKey = chain.request().run {
            newBuilder().url(
                url.newBuilder().addQueryParameter(API_KEY_NAME, BuildConfig.API_KEY).build()
            ).build()
        }

        return chain.proceed(requestWithApiKey)
    }
}