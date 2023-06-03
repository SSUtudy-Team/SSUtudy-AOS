package com.android.ssutudy.presentation.signup.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.ssutudy.util.extensions.addSourceList

class SignUpViewModel : ViewModel() {
    val name: MutableLiveData<String> = MutableLiveData("")
    val id: MutableLiveData<String> = MutableLiveData("")
    val pw: MutableLiveData<String> = MutableLiveData("")
    val pwCheck: MutableLiveData<String> = MutableLiveData("")

    val isNextBtnEnabled: MediatorLiveData<Boolean> = MediatorLiveData(false).apply {
        addSourceList(name, id, pw, pwCheck) { isValidFirstSignUpInfo() }
    }

    private fun isValidFirstSignUpInfo(): Boolean {
        return (!name.value.isNullOrBlank() && !id.value.isNullOrBlank() && !pw.value.isNullOrBlank() && !pwCheck.value.isNullOrBlank())
                && pw.value == pwCheck.value
    }

    private val _countCategory: MutableLiveData<Int> = MutableLiveData(0)
    val plusCountCategoryOne = {
        _countCategory.value = _countCategory.value?.plus(1)
    }
    val minusCountCategoryOne = {
        _countCategory.value = _countCategory.value?.minus(1)
    }
    val countCategory: LiveData<Int>
        get() = _countCategory

}