package com.android.ssutudy.data.remote.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResponseHomeDto(
    @SerialName("message") val message: String,
    @SerialName("success") val success: Boolean,
    @SerialName("data") val data: Data,
) {
    @Serializable
    data class Data(
        @SerialName("joinStudy") val joinStudy: List<JoinStudy>,
        @SerialName("recommendStudy") val recommendStudy: List<RecommendStudy>,
    ) {
        @Serializable
        data class JoinStudy(
            @SerialName("title") val title: String,
        )

        @Serializable
        data class RecommendStudy(
            @SerialName("className") val className: String,
            @SerialName("content") val content: String,
            @SerialName("curUserCount") val curUserCount: Int,
            @SerialName("department") val department: String,
            @SerialName("studyStatus") val studyStatus: String,
            @SerialName("title") val title: String,
            @SerialName("userCount") val userCount: Int,
        )
    }
}