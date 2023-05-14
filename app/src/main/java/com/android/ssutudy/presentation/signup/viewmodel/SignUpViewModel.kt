package com.android.ssutudy.presentation.signup.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel

class SignUpViewModel : ViewModel() {
    val name: MutableLiveData<String> = MutableLiveData("")
    val isNameValid: LiveData<Boolean> = Transformations.map(name) { name ->
        name.isNotBlank()
    }
    val id: MutableLiveData<String> = MutableLiveData("")
    val isIdValid: LiveData<Boolean> = Transformations.map(id) { id ->
        id.isNotBlank()
    }
    val pw: MutableLiveData<String> = MutableLiveData("")
    val isPwValid: LiveData<Boolean> = Transformations.map(pw) { pw ->
        pw.isNotBlank()
    }
    val pwCheck: MutableLiveData<String> = MutableLiveData("")
    val isPwCheckSameWithPw: LiveData<Boolean> = Transformations.map(pwCheck) { pwCheck ->
        pwCheck.isNotBlank() && pwCheck == pw.value
    }

    fun isAbleToNavigateNextPage(): Boolean {
        return (isNameValid.value == true && isIdValid.value == true && isPwValid.value == true && isPwCheckSameWithPw.value == true)
    }
}