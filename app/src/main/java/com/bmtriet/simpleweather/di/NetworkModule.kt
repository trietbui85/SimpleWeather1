package com.bmtriet.simpleweather.di

import com.bmtriet.simpleweather.BuildConfig
import com.bmtriet.simpleweather.Secrets
import com.bmtriet.simpleweather.data.network.error.ErrorParser
import com.bmtriet.simpleweather.data.network.interceptor.WeatherAppIdInterceptor
import com.bmtriet.simpleweather.data.network.service.WeatherApi
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
@Suppress("MagicNumber")
object NetworkModule {

    private const val BASE_URL = "https://api.openweathermap.org/data/2.5/"

    @Provides
    @Singleton
    @Named("BaseUrl")
    fun provideBaseUrl() = BASE_URL

    @Provides
    @Singleton
    @Named("OpenWeatherMapAppId")
    fun provideOpenWeatherMapAppId() = Secrets().getOpenWeatherMapAppId(BuildConfig.APPLICATION_ID)

    @Provides
    @Singleton
    fun provideGson(): Gson = Gson()

    @Provides
    @Singleton
    internal fun provideErrorParser() = ErrorParser()

    @Provides
    @Singleton
    fun provideGsonConverterFactory(gson: Gson): GsonConverterFactory {
        return GsonConverterFactory.create(gson)
    }

    @Provides
    @Singleton
    fun provideLogLevel() =
        if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor.Level.BODY
        } else {
            HttpLoggingInterceptor.Level.NONE
        }

    @Provides
    @Singleton
    fun provideHttpLoggingInterceptor(
        logLevel: HttpLoggingInterceptor.Level,
    ) = HttpLoggingInterceptor().apply { level = logLevel }

    @Provides
    @Singleton
    fun provideWeatherAppIdInterceptor(
        @Named("OpenWeatherMapAppId") openWeatherMapAppId: String,
    ) = WeatherAppIdInterceptor(openWeatherMapAppId)

    @Provides
    @Singleton
    fun provideOkHttpClient(
        loggingInterceptor: HttpLoggingInterceptor,
        weatherAppIdInterceptor: WeatherAppIdInterceptor,
    ) = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .addInterceptor(weatherAppIdInterceptor)
        .connectTimeout(30L, TimeUnit.SECONDS)
        .readTimeout(30L, TimeUnit.SECONDS)
        .build()

    @Provides
    @Singleton
    fun provideRetrofitBuilder(
        @Named("BaseUrl") baseUrl: String,
        client: OkHttpClient,
        converterFactory: GsonConverterFactory,
    ): Retrofit {
        val retrofitBuilder = Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(client)
            .addConverterFactory(converterFactory)

        return retrofitBuilder.client(client).build()
    }

    @Provides
    @Singleton
    fun provideWeatherApi(retrofit: Retrofit): WeatherApi {
        return retrofit.create(WeatherApi::class.java)
    }
}
