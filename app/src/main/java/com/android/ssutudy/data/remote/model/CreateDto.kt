package com.android.ssutudy.data.remote.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RequestCreateDto(
    @SerialName("college") val college: String,
    @SerialName("department") val department: String,
    @SerialName("className") val className: String,
    @SerialName("title") val title: String,
    @SerialName("content") val content: String,
    @SerialName("userCount") val userCount: Int,
    @SerialName("roomLink") val roomLink: String,
    @SerialName("categoryCodeDtos") val categoryCodeDtos: List<CategoryCode>,
)

@Serializable
data class ResponseCreateDto(
    @SerialName("success") val success: Boolean,
    @SerialName("message") val message: String,
    @SerialName("data") val data: Long,
)