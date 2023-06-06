package com.android.ssutudy.data.remote.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RequestLoginDto(
    @SerialName("studentId")
    val studentId: String,
    @SerialName("password")
    val password: String,
)

@Serializable
data class ResponseLoginDto(
    @SerialName("data")
    val token: String,
)