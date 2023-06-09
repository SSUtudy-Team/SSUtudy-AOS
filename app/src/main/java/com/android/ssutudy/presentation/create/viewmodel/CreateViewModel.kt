package com.android.ssutudy.presentation.create.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import androidx.lifecycle.viewModelScope
import com.android.ssutudy.data.local.SharedPreferences
import com.android.ssutudy.data.remote.ServicePool.createService
import com.android.ssutudy.data.remote.model.CategoryCode
import com.android.ssutudy.data.remote.model.RequestCreateDto
import com.android.ssutudy.data.remote.model.ResponseCreateDto
import com.android.ssutudy.util.extensions.addSourceList
import com.android.ssutudy.util.publics.PublicFunction.getErrorMessage
import com.android.ssutudy.util.publics.PublicString
import kotlinx.coroutines.launch

class CreateViewModel : ViewModel() {
    val topic: MutableLiveData<String> = MutableLiveData()
    val intro: MutableLiveData<String> = MutableLiveData()
    val openChatLink: MutableLiveData<String> = MutableLiveData()

    private fun isValidToCreate() =
        !topic.value.isNullOrBlank() && !intro.value.isNullOrBlank() && isCategorySelected() && !openChatLink.value.isNullOrBlank()

    private fun isCategorySelected(): Boolean {
        return if (countCategory.value == null) false
        else {
            countCategory.value!! > 0
        }
    }

    private val _categoryCodeList: MutableLiveData<List<CategoryCode>> =
        MutableLiveData(emptyList())
    val categoryCodeList: LiveData<List<CategoryCode>> = _categoryCodeList
    val categoryList: LiveData<List<String>> = _categoryCodeList.map {
        val categoryList = mutableListOf<String>()
        it.forEach { categoryCode -> categoryList.add(categoryCode.categoryCode) }
        categoryList
    }

    fun setCategoryList(newCategoryList: List<String>) {
        val newCategoryCodeList = mutableListOf<CategoryCode>()
        newCategoryList.forEach {
            newCategoryCodeList.add(CategoryCode(it))
        }
        _categoryCodeList.value = newCategoryCodeList
    }

    private val _countCategory: MutableLiveData<Int> = MutableLiveData(0)
    val plusCountCategoryOne = {
        _countCategory.value = _countCategory.value?.plus(1)
    }
    val minusCountCategoryOne = {
        _countCategory.value = _countCategory.value?.minus(1)
    }

    fun resetCountCategory() {
        _countCategory.value = 0
    }

    val countCategory: LiveData<Int>
        get() = _countCategory

    val canUserCreate: MediatorLiveData<Boolean> = MediatorLiveData<Boolean>().apply {
        addSourceList(topic, intro, _countCategory, openChatLink) {
            isValidToCreate()
        }
    }

    private val _createStudySuccessResponse: MutableLiveData<ResponseCreateDto> = MutableLiveData()
    val createStudySuccessResponse: LiveData<ResponseCreateDto> = _createStudySuccessResponse

    private val _createStudyErrorResponse: MutableLiveData<String> = MutableLiveData()
    val createStudyErrorResponse: LiveData<String> = _createStudyErrorResponse

    fun createStudy(college: String, department: String, className: String, userCount: Int) {
        val userId: String = SharedPreferences.getString(PublicString.USER_ID) ?: return
        viewModelScope.launch {
            kotlin.runCatching {
                createService.createStudy(
                    userId, RequestCreateDto(
                        college,
                        department,
                        className,
                        topic.value ?: "",
                        intro.value ?: "",
                        userCount,
                        openChatLink.value ?: "",
                        categoryCodeList.value ?: emptyList()
                    )
                )

            }.fold(onSuccess = { response -> _createStudySuccessResponse.value = response },
                onFailure = { response ->
                    _createStudyErrorResponse.value = getErrorMessage(response)
                })
        }
    }
}