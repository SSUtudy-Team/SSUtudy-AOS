package com.android.ssutudy.presentation.home.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class HomeViewModel : ViewModel() {
    private val _doesMySSUtudyExist: MutableLiveData<Boolean> = MutableLiveData<Boolean>(false)
    val doesMySSUtudyExist: LiveData<Boolean> = _doesMySSUtudyExist
    fun checkIsMySSUtudyExist(doesMySSUtudyExist: Boolean) {
        this._doesMySSUtudyExist.value = doesMySSUtudyExist
    }
}