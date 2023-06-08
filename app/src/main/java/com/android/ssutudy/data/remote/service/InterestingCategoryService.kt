package com.android.ssutudy.data.remote.service

import com.android.ssutudy.data.remote.model.RequestUpdateInterestingCategoryList
import com.android.ssutudy.data.remote.model.ResponseUpdateInterestingCategoryList
import retrofit2.http.Body
import retrofit2.http.PUT
import retrofit2.http.Path

interface InterestingCategoryService {
    @PUT("users/{userId}")
    suspend fun updateInterestingCategory(
        @Path("userId") userId: String,
        @Body request: RequestUpdateInterestingCategoryList,
    ): ResponseUpdateInterestingCategoryList
}