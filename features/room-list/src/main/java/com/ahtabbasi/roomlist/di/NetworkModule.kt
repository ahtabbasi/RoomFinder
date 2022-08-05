package com.ahtabbasi.roomlist.di

import com.ahtabbasi.roomlist.BuildConfig
import com.ahtabbasi.roomlist.data.remote.api.ApiService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    private val jsonConverterFactory = Json {
        isLenient = true
        ignoreUnknownKeys = true
    }

    @Singleton
    @Provides
    internal fun provideOkHttpClient(): OkHttpClient {
        val logger = HttpLoggingInterceptor()
        logger.level = if (BuildConfig.DEBUG)
            HttpLoggingInterceptor.Level.BODY
        else HttpLoggingInterceptor.Level.NONE

        return OkHttpClient.Builder()
            .callTimeout(25, TimeUnit.SECONDS)
            .retryOnConnectionFailure(true)
            .addInterceptor(logger)
            .build()
    }

    @ExperimentalSerializationApi
    @Singleton
    @Provides
    internal fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        val contentType = "application/json".toMediaType()
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(
                jsonConverterFactory.asConverterFactory(contentType)
            )
            .build()
    }

    @Singleton
    @Provides
    internal fun provideApiService(retrofit: Retrofit): ApiService =
        retrofit.create(ApiService::class.java)

}