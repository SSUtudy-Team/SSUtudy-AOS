package com.android.ssutudy.data.remote.service

import com.android.ssutudy.data.remote.model.ResponseEndDto
import retrofit2.http.POST
import retrofit2.http.Path

interface EndService {
    @POST("studies/end/{studyId}")
    suspend fun endStudy(
        @Path("studyId") studyId: String,
    ): ResponseEndDto
}