package com.android.ssutudy.data.remote.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResponseGetUserInfo(
    @SerialName("success")
    val success: Boolean,
    @SerialName("message")
    val message: String,
    @SerialName("data")
    val data: UserInfo,
) {
    @Serializable
    data class UserInfo(
        @SerialName("studentId")
        val studentId: String,
        @SerialName("password")
        val password: String,
        @SerialName("name")
        val name: String,
        @SerialName("grade")
        val grade: Int,
        @SerialName("department")
        val department: String,
        @SerialName("categoryList")
        val categoryList: List<String>,
    )
}