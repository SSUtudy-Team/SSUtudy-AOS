package com.android.ssutudy.data.remote.service

import com.android.ssutudy.data.remote.model.ResponseGetUserInfo
import retrofit2.http.POST
import retrofit2.http.Path

interface UserInfoService {
    @POST("users/{userId}")
    suspend fun getUserInfo(
        @Path("userId") userId: String,
    ): ResponseGetUserInfo
}