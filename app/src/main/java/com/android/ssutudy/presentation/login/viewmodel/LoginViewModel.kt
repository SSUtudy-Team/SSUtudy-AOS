package com.android.ssutudy.presentation.login.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.ssutudy.data.remote.ServicePool.loginService
import com.android.ssutudy.data.remote.model.RequestLoginDto
import com.android.ssutudy.data.remote.model.ResponseLoginDto
import com.android.ssutudy.util.publics.PublicFunction.getErrorMessage
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {

    val studentId: MutableLiveData<String> = MutableLiveData()
    val pw: MutableLiveData<String> = MutableLiveData()

    private val _loginSuccessResponse: MutableLiveData<ResponseLoginDto> = MutableLiveData()
    val loginSuccessResponse: LiveData<ResponseLoginDto> = _loginSuccessResponse

    private val _loginErrorResponse: MutableLiveData<String> = MutableLiveData()
    val loginErrorResponse: LiveData<String> = _loginErrorResponse

    fun login() {
        if (studentId.value == null || pw.value == null)
            throw NullPointerException("id or pw is null")
        viewModelScope.launch {
            kotlin.runCatching {
                loginService.login(RequestLoginDto(studentId.value!!, pw.value!!))
            }.fold(onSuccess = { response -> _loginSuccessResponse.value = response },
                onFailure = { response -> _loginErrorResponse.value = getErrorMessage(response) })
        }
    }
}