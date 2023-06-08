package com.android.ssutudy.presentation.home.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import androidx.lifecycle.viewModelScope
import com.android.ssutudy.data.local.SharedPreferences
import com.android.ssutudy.data.remote.ServicePool.homeService
import com.android.ssutudy.data.remote.model.ResponseHomeDto
import com.android.ssutudy.util.publics.PublicFunction.getErrorMessage
import com.android.ssutudy.util.publics.PublicString.USER_ID
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {

    private val _getHomeDataSuccessResponse: MutableLiveData<ResponseHomeDto> = MutableLiveData()
    val getHomeDataSuccessResponse: LiveData<ResponseHomeDto> = _getHomeDataSuccessResponse

    private val _getHomeDataErrorResponse: MutableLiveData<String> = MutableLiveData()
    val getHomeDataErrorResponse: LiveData<String> = _getHomeDataErrorResponse

    val doesMyStudyExist: LiveData<Boolean> = _getHomeDataSuccessResponse.map {
        it.data.joinStudy.isNotEmpty()
    }

    fun getHomeData() {
        val userId: String? = SharedPreferences.getString(USER_ID)
        if (userId != null) {
            viewModelScope.launch {
                kotlin.runCatching {
                    homeService.getHomeData(userId = userId)
                }.fold(onSuccess = { response -> _getHomeDataSuccessResponse.value = response },
                    onFailure = { response ->
                        _getHomeDataErrorResponse.value = getErrorMessage(response)
                    })
            }
        }
    }
}