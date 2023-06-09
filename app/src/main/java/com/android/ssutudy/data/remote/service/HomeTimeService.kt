package com.android.ssutudy.data.remote.service

import com.android.ssutudy.data.remote.model.ResponseHomeTimeDto
import retrofit2.http.GET

interface HomeTimeService {
    @GET("studies/time")
    suspend fun getHomeTimeData(): ResponseHomeTimeDto
}