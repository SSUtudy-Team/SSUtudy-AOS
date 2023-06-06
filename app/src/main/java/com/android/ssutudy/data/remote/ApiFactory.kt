package com.android.ssutudy.data.remote

import com.android.ssutudy.BuildConfig
import com.android.ssutudy.data.remote.interceptor.TokenInterceptor
import com.android.ssutudy.data.remote.service.LoginService
import com.android.ssutudy.data.remote.service.SignUpService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit

object ApiFactory {
    private val client by lazy {
        OkHttpClient.Builder().addInterceptor(TokenInterceptor())
            .addInterceptor(HttpLoggingInterceptor().apply {
                level =
                    if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
            })
            .build()
    }

    val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(client)
            .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
            .build()
    }

    inline fun <reified T> create(): T = retrofit.create<T>(T::class.java)
}

object ServicePool {
    val signUpService = ApiFactory.create<SignUpService>()
    val loginService = ApiFactory.create<LoginService>()
}