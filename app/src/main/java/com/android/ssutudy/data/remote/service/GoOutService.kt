package com.android.ssutudy.data.remote.service

import com.android.ssutudy.data.remote.model.ResponseGoOutDto
import retrofit2.http.DELETE
import retrofit2.http.POST
import retrofit2.http.Path

interface GoOutService {
    @DELETE("out/{studyId}/{userId}")
    suspend fun goOutStudy(
        @Path("studyId") studyId: String,
        @Path("userId") userId: String,
    ): ResponseGoOutDto
}