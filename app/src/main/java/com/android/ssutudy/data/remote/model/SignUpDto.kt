package com.android.ssutudy.data.remote.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RequestSignUpDto(
    @SerialName("studentId") val studentId: String,
    @SerialName("password") val password: String,
    @SerialName("name") val name: String,
    @SerialName("grade") val grade: Int,
    @SerialName("department") val department: String,
    @SerialName("categoryCodeDtos") val categoryCodeDtos: List<CategoryCode>,
)

@Serializable
data class ResponseSignUpDto(
    @SerialName("success") val success: Boolean,
    @SerialName("message") val message: String,
    @SerialName("data") val data: Long,
)