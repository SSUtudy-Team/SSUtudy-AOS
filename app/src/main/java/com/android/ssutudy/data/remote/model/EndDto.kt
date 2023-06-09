package com.android.ssutudy.data.remote.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResponseEndDto(
    @SerialName("success") val success: Boolean,
    @SerialName("message") val message: String,
)