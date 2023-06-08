package com.android.ssutudy.data.remote.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RequestUpdateInterestingCategoryList(
    @SerialName("categoryCodeDtos") val categoryCodeDtos: List<CategoryCode>,
)

@Serializable
data class ResponseUpdateInterestingCategoryList(
    @SerialName("success") val success: Boolean,
    @SerialName("message") val message: String,
)