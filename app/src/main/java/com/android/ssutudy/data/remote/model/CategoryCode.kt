package com.android.ssutudy.data.remote.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CategoryCode(
    @SerialName("categoryCode") val categoryCode: String,
)