package com.android.ssutudy.data.remote.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RequestLoginDto(
    @SerialName("studentId") val studentId: String,
    @SerialName("password") val password: String,
)

@Serializable
data class ResponseLoginDto(
    @SerialName("success") val success: Boolean,
    @SerialName("message") val message: String,
    @SerialName("data") val data: UserData,
) {
    @Serializable
    data class UserData(
        @SerialName("token") val token: String,
        @SerialName("userId") val userId: Long,
    )
}