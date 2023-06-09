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
        @SerialName("recommendStudy") val recommendStudy: List<Study>,
    ) {
        @Serializable
        data class JoinStudy(
            @SerialName("title") val title: String,
            @SerialName("studyId") val studyId: Long,
        )
    }
}