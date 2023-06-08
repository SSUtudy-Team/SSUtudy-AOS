package com.android.ssutudy.data.remote.service

import com.android.ssutudy.data.remote.model.ResponseHomeDto
import retrofit2.http.GET
import retrofit2.http.Path

interface HomeService {
    @GET("users/home/{userId}")
    suspend fun getHomeData(
        @Path("userId") userId: String,
    ): ResponseHomeDto
}