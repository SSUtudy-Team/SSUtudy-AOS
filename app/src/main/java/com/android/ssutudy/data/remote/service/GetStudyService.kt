package com.android.ssutudy.data.remote.service

import com.android.ssutudy.data.remote.model.ResponseGetStudyDto
import retrofit2.http.GET
import retrofit2.http.Path

interface GetStudyService {
    @GET("studies/{studyId}/{userId}")
    suspend fun getStudy(
        @Path("studyId") studyId: String,
        @Path("userId") userId: String,
    ): ResponseGetStudyDto
}