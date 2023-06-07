package com.android.ssutudy.data.remote.interceptor

import com.android.ssutudy.data.local.SharedPreferences
import com.android.ssutudy.util.publics.PublicString.TOKEN
import okhttp3.Interceptor
import okhttp3.Response

class TokenInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val token = SharedPreferences.getString(TOKEN)
        if (token != null) {
            val tokenAddedRequest = originalRequest.newBuilder()
                .header(
                    "Authorization",
                    token
                )
                .build()
            return chain.proceed(tokenAddedRequest)
        }
        return chain.proceed(originalRequest)
    }
}