package com.android.ssutudy.data.remote.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Study(
    @SerialName("department") val department: String,
    @SerialName("className") val className: String,
    @SerialName("title") val title: String,
    @SerialName("content") val content: String,
    @SerialName("userCount") val userCount: Int,
    @SerialName("curUserCount") val curUserCount: Int,
    @SerialName("studyStatus") val studyStatus: String,
    @SerialName("studyId") val studyId: Long,
)