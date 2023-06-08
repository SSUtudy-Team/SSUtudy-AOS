package com.android.ssutudy.presentation.mypage.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import androidx.lifecycle.viewModelScope
import com.android.ssutudy.data.local.SharedPreferences
import com.android.ssutudy.data.remote.ServicePool.userInfoService
import com.android.ssutudy.data.remote.model.ResponseGetUserInfo
import com.android.ssutudy.data.remote.model.ResponseUpdateInterestingCategoryList
import com.android.ssutudy.util.publics.PublicFunction.getErrorMessage
import com.android.ssutudy.util.publics.PublicString.USER_ID
import kotlinx.coroutines.launch

class MyPageViewModel : ViewModel() {

    val name: MutableLiveData<String> = MutableLiveData("")
    val studentId: MutableLiveData<String> = MutableLiveData("")
    private val gradeInt: MutableLiveData<Int> = MutableLiveData(1)
    val gradeString: LiveData<String> = gradeInt.map { gradeInt ->
        gradeInt.toString() + "학년"
    }
    val major: MutableLiveData<String> = MutableLiveData()
    private val categoryList: MutableLiveData<List<String>> = MutableLiveData()
    val categorySummary: LiveData<String> = categoryList.map { categoryList ->
        categoryList[0] + "외 ${categoryList.size - 1}"
    }

    private val _getErrorResponse: MutableLiveData<String> = MutableLiveData()
    val getErrorResponse: LiveData<String> = _getErrorResponse

    fun getUserData() {
        val userId: String = SharedPreferences.getString(USER_ID) ?: return
        viewModelScope.launch {
            kotlin.runCatching { userInfoService.getUserInfo(userId) }
                .fold(onSuccess = { response ->
                    setUserData(response.data)
                }, onFailure = { response ->
                    _getErrorResponse.value = getErrorMessage(response)
                })
        }
    }

    private fun setUserData(userInfo: ResponseGetUserInfo.UserInfo) {
        name.value = userInfo.name
        studentId.value = userInfo.studentId
        gradeInt.value = userInfo.grade
        major.value = userInfo.department
        categoryList.value = userInfo.categoryList
    }

    private val _updateInterestingCategorySuccessResponse: MutableLiveData<ResponseUpdateInterestingCategoryList> =
        MutableLiveData()
    val updateInterestingCategorySuccessResponse: LiveData<ResponseUpdateInterestingCategoryList> =
        _updateInterestingCategorySuccessResponse
}