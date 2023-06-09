package com.android.ssutudy.data.remote.service

import com.android.ssutudy.data.remote.model.ResponseJoinDto
import retrofit2.http.POST
import retrofit2.http.Path

interface JoinService {
    @POST("join/{studyId}/{userId}")
    suspend fun joinStudy(
        @Path("studyId") studyId: String,
        @Path("userId") userId: String,
    ): ResponseJoinDto
}