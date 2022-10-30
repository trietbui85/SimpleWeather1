package com.bmtriet.simpleweather.data.network.interceptor

import okhttp3.Interceptor
import okhttp3.Response

class WeatherAppIdInterceptor(
    private val weatherAppId: String,
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()
        val originalHttpUrl = original.url

        val url = originalHttpUrl.newBuilder()
            .addQueryParameter(WEATHER_APP_ID, weatherAppId)
            .build()

        val requestBuilder = original.newBuilder()
            .url(url)

        val request = requestBuilder.build()
        return chain.proceed(request)
    }

    companion object {
        private const val WEATHER_APP_ID = "appid"
    }
}
