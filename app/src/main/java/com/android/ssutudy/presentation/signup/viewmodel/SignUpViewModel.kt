package com.android.ssutudy.presentation.signup.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.ssutudy.data.remote.ServicePool.signUpService
import com.android.ssutudy.data.remote.model.RequestSignUpDto
import com.android.ssutudy.data.remote.model.RequestSignUpDto.CategoryCodeDto
import com.android.ssutudy.data.remote.model.ResponseSignUpDto
import com.android.ssutudy.util.extensions.addSourceList
import com.android.ssutudy.util.publics.PublicFunction.getErrorMessage
import kotlinx.coroutines.launch

class SignUpViewModel : ViewModel() {
    val name: MutableLiveData<String> = MutableLiveData("")
    val studentId: MutableLiveData<String> = MutableLiveData("")
    val pw: MutableLiveData<String> = MutableLiveData("")
    val pwCheck: MutableLiveData<String> = MutableLiveData("")

    val isNextBtnEnabled: MediatorLiveData<Boolean> = MediatorLiveData(false).apply {
        addSourceList(name, studentId, pw, pwCheck) { isValidFirstSignUpInfo() }
    }

    private fun isValidFirstSignUpInfo(): Boolean {
        return !name.value.isNullOrBlank() && !studentId.value.isNullOrBlank() && studentId.value?.length == 8 && !pw.value.isNullOrBlank() && !pwCheck.value.isNullOrBlank() && pw.value == pwCheck.value && (pw.value?.length
            ?: 0) >= 8
    }

    val grade: MutableLiveData<Int> = MutableLiveData()
    val major: MutableLiveData<String> = MutableLiveData()

    private val _countCategory: MutableLiveData<Int> = MutableLiveData(0)
    val plusCountCategoryOne = {
        _countCategory.value = _countCategory.value?.plus(1)
    }
    val minusCountCategoryOne = {
        _countCategory.value = _countCategory.value?.minus(1)
    }
    val countCategory: LiveData<Int>
        get() = _countCategory

    private val _signUpSuccessResponse: MutableLiveData<ResponseSignUpDto> = MutableLiveData()
    val signUpSuccessResponse: LiveData<ResponseSignUpDto> = _signUpSuccessResponse

    private val _signUpErrorResponse: MutableLiveData<String> = MutableLiveData()
    val signUpErrorResponse: LiveData<String> = _signUpErrorResponse
    fun signUp(categoryList: List<String>) {

        val categoryCodeList: MutableList<CategoryCodeDto> = mutableListOf()
        categoryList.map { category ->
            categoryCodeList.add(CategoryCodeDto(category))
        }

        viewModelScope.launch {
            kotlin.runCatching {
                signUpService.signUp(
                    RequestSignUpDto(
                        studentId = studentId.value ?: "",
                        password = pw.value ?: "",
                        name = name.value ?: "",
                        grade = grade.value ?: 0,
                        department = major.value ?: "",
                        categoryCodeList
                    )
                )
            }.fold(onSuccess = { response -> _signUpSuccessResponse.value = response },
                onFailure = { response ->
                    _signUpErrorResponse.value = getErrorMessage(response)
                })
        }
    }
}