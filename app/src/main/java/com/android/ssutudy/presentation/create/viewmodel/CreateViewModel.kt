package com.android.ssutudy.presentation.create.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.ssutudy.util.extensions.addSourceList

class CreateViewModel : ViewModel() {
    val topic: MutableLiveData<String> = MutableLiveData()

    val intro: MutableLiveData<String> = MutableLiveData()

    private fun isValidToCreate() =
        !topic.value.isNullOrBlank() && !intro.value.isNullOrEmpty() && isCategorySelected()

    private fun isCategorySelected(): Boolean {
        return if (countCategory.value == null)
            false
        else {
            countCategory.value!! > 0
        }
    }

    private val _categoryList: MutableLiveData<List<String>> =
        MutableLiveData(emptyList())
    val categoryList: LiveData<List<String>> = _categoryList

    fun setCategoryList(newCategoryList: List<String>) {
        _categoryList.value = newCategoryList
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
        addSourceList(topic, intro, _countCategory) {
            isValidToCreate()
        }
    }
}