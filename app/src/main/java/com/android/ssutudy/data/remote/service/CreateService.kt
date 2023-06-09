package com.android.ssutudy.data.remote.service

import com.android.ssutudy.data.remote.model.RequestCreateDto
import com.android.ssutudy.data.remote.model.ResponseCreateDto
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Path

interface CreateService {
    @POST("studies/{userId}")
    suspend fun createStudy(
        @Path("userId") userId: String,
        @Body request: RequestCreateDto,
    ): ResponseCreateDto
}