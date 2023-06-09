package com.android.ssutudy.data.remote.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResponseGetStudyDto(
    @SerialName("success") val success: Boolean,
    @SerialName("message") val message: String,
    @SerialName("data") val data: StudyInfo,
) {
    @Serializable
    data class StudyInfo(
        @SerialName("subject") val subject: Subject,
        @SerialName("title") val title: String,
        @SerialName("content") val content: String,
        @SerialName("userCount") val userCount: Long,
        @SerialName("curUserCount") val curUserCount: Long,
        @SerialName("roomLink") val roomLink: String,
        @SerialName("studyStatus") val studyStatus: String,
        @SerialName("categoryList") val categoryList: List<String>,
        @SerialName("userName") val userName: List<String>,
        @SerialName("joinOrNot") val isUserJoining: Boolean,
        @SerialName("mineOrNot") val isUserCreator: Boolean,
    ) {
        @Serializable
        data class Subject(
            @SerialName("college") val college: String,
            @SerialName("department") val department: String,
            @SerialName("className") val className: String,
        )
    }
}