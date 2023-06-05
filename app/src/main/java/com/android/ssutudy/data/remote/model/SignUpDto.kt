package com.android.ssutudy.data.remote.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RequestSignUpDto(
    @SerialName("student_Id") val studentId: String,
    @SerialName("password") val password: String,
    @SerialName("name") val name: String,
    @SerialName("grade") val grade: Int,
    @SerialName("department") val department: String,
    @SerialName("categories") val categories: List<String>,
)

@Serializable
data class ResponseSignUpDto(
    @SerialName("student_Id") val studentId: String,
    @SerialName("password") val password: String,
    @SerialName("name") val name: String,
    @SerialName("grade") val grade: Int,
    @SerialName("department") val department: String,
    @SerialName("categories") val categories: List<String>,
)