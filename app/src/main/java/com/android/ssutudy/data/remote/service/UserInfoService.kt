package com.android.ssutudy.data.remote.service

import com.android.ssutudy.data.remote.model.ResponseGetUserInfo
import retrofit2.http.GET
import retrofit2.http.Path

interface UserInfoService {
    @GET("users/{userId}")
    suspend fun getUserInfo(
        @Path("userId") userId: String,
    ): ResponseGetUserInfo
}