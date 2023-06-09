package com.android.ssutudy.data.remote.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResponseHomeDto(
    @SerialName("success") val success: Boolean,
    @SerialName("message") val message: String,
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
            @SerialName("studyId") val studyId: Long,
        )

        @Serializable
        data class RecommendStudy(
            @SerialName("department") val department: String,
            @SerialName("className") val className: String,
            @SerialName("title") val title: String,
            @SerialName("content") val content: String,
            @SerialName("userCount") val userCount: Int,
            @SerialName("curUserCount") val curUserCount: Int,
            @SerialName("studyStatus") val studyStatus: String,
            @SerialName("studyId") val studyId: Long,
        )
    }
}