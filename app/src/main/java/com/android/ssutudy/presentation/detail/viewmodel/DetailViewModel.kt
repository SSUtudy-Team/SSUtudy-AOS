package com.android.ssutudy.presentation.detail.viewmodel

import android.graphics.drawable.Drawable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import androidx.lifecycle.viewModelScope
import com.android.ssutudy.data.local.SharedPreferences
import com.android.ssutudy.data.remote.ServicePool.deleteService
import com.android.ssutudy.data.remote.ServicePool.endService
import com.android.ssutudy.data.remote.ServicePool.getStudyService
import com.android.ssutudy.data.remote.ServicePool.goOutService
import com.android.ssutudy.data.remote.ServicePool.joinService
import com.android.ssutudy.data.remote.model.ResponseDeleteDto
import com.android.ssutudy.data.remote.model.ResponseEndDto
import com.android.ssutudy.data.remote.model.ResponseGetStudyDto
import com.android.ssutudy.data.remote.model.ResponseGoOutDto
import com.android.ssutudy.data.remote.model.ResponseJoinDto
import com.android.ssutudy.util.publics.PublicFunction.getErrorMessage
import com.android.ssutudy.util.publics.PublicString
import kotlinx.coroutines.launch

class DetailViewModel : ViewModel() {
    lateinit var studyId: String

    private val _getSuccessResponse: MutableLiveData<ResponseGetStudyDto> = MutableLiveData()
    val getSuccessResponse: LiveData<ResponseGetStudyDto> = _getSuccessResponse

    private val _getErrorResponse: MutableLiveData<String> = MutableLiveData()
    val getErrorResponse: LiveData<String> = _getErrorResponse

    val isUserJoining: LiveData<Boolean> = _getSuccessResponse.map {
        it.data.isUserJoining
    }
    val isUserCreator: LiveData<Boolean> = _getSuccessResponse.map {
        it.data.isUserCreator
    }

    val isUserJoiningButNotCreator: LiveData<Boolean> = _getSuccessResponse.map {
        it.data.isUserJoining && !it.data.isUserCreator
    }

    val studyStateDrawable: MutableLiveData<Drawable> = MutableLiveData()
    val studyStateText: MutableLiveData<String> = MutableLiveData()
    val department: MutableLiveData<String> = MutableLiveData()
    val subject: MutableLiveData<String> = MutableLiveData()
    val content: MutableLiveData<String> = MutableLiveData()
    val userName: MutableLiveData<String> = MutableLiveData()
    val participants: MutableLiveData<String> = MutableLiveData()
    val title: MutableLiveData<String> = MutableLiveData()
    val openChatLink: MutableLiveData<String> = MutableLiveData()

    fun getStudy() {
        val userId: String = SharedPreferences.getString(PublicString.USER_ID) ?: return
        viewModelScope.launch {
            kotlin.runCatching {
                getStudyService.getStudy(studyId, userId)
            }.fold(onSuccess = { response ->
                _getSuccessResponse.value = response
                initData(response.data)
            }, onFailure = { response -> _getErrorResponse.value = getErrorMessage(response) })
        }
    }

    private fun initData(data: ResponseGetStudyDto.StudyInfo) {
        studyStateText.value = data.studyStatus
        department.value = data.subject.college + " " + data.subject.department
        subject.value = data.subject.className
        content.value = data.content
        userName.value =
            if (data.curUserCount == 1L) data.userName[0] else data.userName[0] + " 외 " + (data.curUserCount - 1).toString() + "명"
        participants.value = data.curUserCount.toString() + "/" + data.userCount.toString()
        title.value = data.title
        openChatLink.value = data.roomLink
    }

    private val _endSuccessResponse: MutableLiveData<ResponseEndDto> = MutableLiveData()
    val endSuccessResponse: LiveData<ResponseEndDto> = _endSuccessResponse

    private val _endErrorResponse: MutableLiveData<String> = MutableLiveData()
    val endErrorResponse: LiveData<String> = _endErrorResponse

    fun endStudy() {
        viewModelScope.launch {
            kotlin.runCatching {
                endService.endStudy(studyId)
            }.fold(onSuccess = { response -> _endSuccessResponse.value = response },
                onFailure = { response -> _endErrorResponse.value = getErrorMessage(response) })
        }
    }

    private val _deleteSuccessResponse: MutableLiveData<ResponseDeleteDto> = MutableLiveData()
    val deleteSuccessResponse: LiveData<ResponseDeleteDto> = _deleteSuccessResponse

    private val _deleteErrorResponse: MutableLiveData<String> = MutableLiveData()
    val deleteErrorResponse: LiveData<String> = _deleteErrorResponse

    fun deleteStudy() {
        viewModelScope.launch {
            kotlin.runCatching {
                deleteService.deleteStudy(studyId)
            }.fold(onSuccess = { response -> _deleteSuccessResponse.value = response },
                onFailure = { response -> _deleteErrorResponse.value = getErrorMessage(response) })
        }
    }

    private val _joinSuccessResponse: MutableLiveData<ResponseJoinDto> = MutableLiveData()
    val joinSuccessResponse: LiveData<ResponseJoinDto> = _joinSuccessResponse

    private val _joinErrorResponse: MutableLiveData<String> = MutableLiveData()
    val joinErrorResponse: LiveData<String> = _joinErrorResponse

    fun joinStudy() {
        val userId: String = SharedPreferences.getString(PublicString.USER_ID) ?: return
        viewModelScope.launch {
            kotlin.runCatching {
                joinService.joinStudy(studyId, userId)
            }.fold(onSuccess = { response -> _joinSuccessResponse.value = response },
                onFailure = { response -> _joinErrorResponse.value = getErrorMessage(response) })
        }
    }

    private val _goOutSuccessResponse: MutableLiveData<ResponseGoOutDto> = MutableLiveData()
    val goOutSuccessResponse: LiveData<ResponseGoOutDto> = _goOutSuccessResponse

    private val _goOutErrorResponse: MutableLiveData<String> = MutableLiveData()
    val goOutErrorResponse: LiveData<String> = _goOutErrorResponse

    fun goOutStudy() {
        val userId: String = SharedPreferences.getString(PublicString.USER_ID) ?: return
        viewModelScope.launch {
            kotlin.runCatching {
                goOutService.goOutStudy(studyId, userId)
            }.fold(onSuccess = { response -> _goOutSuccessResponse.value = response },
                onFailure = { response -> _goOutErrorResponse.value = getErrorMessage(response) })
        }
    }
}