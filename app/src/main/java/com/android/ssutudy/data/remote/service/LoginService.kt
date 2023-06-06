package com.android.ssutudy.data.remote.service

import com.android.ssutudy.data.remote.model.RequestLoginDto
import com.android.ssutudy.data.remote.model.ResponseLoginDto
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginService {
    @POST("users/login")
    suspend fun login(@Body request: RequestLoginDto): ResponseLoginDto
}