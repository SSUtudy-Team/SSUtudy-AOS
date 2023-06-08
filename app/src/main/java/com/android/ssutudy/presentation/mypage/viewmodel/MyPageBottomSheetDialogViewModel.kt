package com.android.ssutudy.presentation.mypage.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.ssutudy.data.local.SharedPreferences
import com.android.ssutudy.data.remote.ServicePool.interestingCategoryService
import com.android.ssutudy.data.remote.model.CategoryCode
import com.android.ssutudy.data.remote.model.RequestUpdateInterestingCategoryList
import com.android.ssutudy.data.remote.model.ResponseUpdateInterestingCategoryList
import com.android.ssutudy.util.publics.PublicFunction.getErrorMessage
import com.android.ssutudy.util.publics.PublicString.USER_ID
import kotlinx.coroutines.launch

class MyPageBottomSheetDialogViewModel : ViewModel() {
    private val _categoryCount: MutableLiveData<Int> = MutableLiveData(0)
    val categoryCount: LiveData<Int> = _categoryCount

    val plusCountCategoryOne = {
        _categoryCount.value = categoryCount.value?.plus(1)
    }
    val minusCountCategoryOne = {
        _categoryCount.value = categoryCount.value?.minus(1)
    }

    private val _updateInterestingCategorySuccessResponse: MutableLiveData<ResponseUpdateInterestingCategoryList> =
        MutableLiveData()
    val updateInterestingCategorySuccessResponse: LiveData<ResponseUpdateInterestingCategoryList> =
        _updateInterestingCategorySuccessResponse

    private val _getErrorResponse: MutableLiveData<String> = MutableLiveData()
    val getErrorResponse: LiveData<String> = _getErrorResponse

    fun updateInterestingCategory(newCategoryList: List<String>) {
        val categoryCodeList: MutableList<CategoryCode> = mutableListOf()
        newCategoryList.map { category ->
            categoryCodeList.add(CategoryCode(category))
        }
        val userId: String = SharedPreferences.getString(USER_ID) ?: return
        viewModelScope.launch {
            kotlin.runCatching {
                interestingCategoryService.updateInterestingCategory(
                    userId, RequestUpdateInterestingCategoryList(categoryCodeList)
                )
            }.fold(onSuccess = { response ->
                _updateInterestingCategorySuccessResponse.value = response
            }, onFailure = { response -> _getErrorResponse.value = getErrorMessage(response) })
        }
    }
}