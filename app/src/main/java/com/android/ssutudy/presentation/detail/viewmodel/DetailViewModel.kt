package com.android.ssutudy.presentation.detail.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class DetailViewModel : ViewModel() {
    val isUserInStudy: MutableLiveData<Boolean> = MutableLiveData(true)
}