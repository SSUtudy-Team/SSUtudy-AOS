package com.android.ssutudy.data.remote.service

import com.android.ssutudy.data.remote.model.ResponseDeleteDto
import retrofit2.http.DELETE
import retrofit2.http.Path

interface DeleteService {
    @DELETE("studies/{studyId}")
    suspend fun deleteStudy(
        @Path("studyId") studyId: String,
    ): ResponseDeleteDto
}