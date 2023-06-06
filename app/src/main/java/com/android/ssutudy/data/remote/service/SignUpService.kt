package com.android.ssutudy.data.remote.service

import com.android.ssutudy.data.remote.model.RequestSignUpDto
import com.android.ssutudy.data.remote.model.ResponseSignUpDto
import retrofit2.http.Body
import retrofit2.http.POST

interface SignUpService {
    @POST("users")
    suspend fun signUp(@Body request: RequestSignUpDto): ResponseSignUpDto
}