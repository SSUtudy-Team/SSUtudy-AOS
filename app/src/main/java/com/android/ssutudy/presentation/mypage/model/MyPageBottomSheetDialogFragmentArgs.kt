package com.android.ssutudy.presentation.mypage.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MyPageBottomSheetDialogFragmentArgs(
    val getData: () -> Unit,
) : Parcelable