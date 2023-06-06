package com.android.ssutudy.data.remote.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ErrorResponseDto(
    @SerialName("code") val code: Int,
    @SerialName("field") val field: String,
    @SerialName("httpStatus") val httpStatus: String,
    @SerialName("errorMessage") val errorMessage: String,
)
