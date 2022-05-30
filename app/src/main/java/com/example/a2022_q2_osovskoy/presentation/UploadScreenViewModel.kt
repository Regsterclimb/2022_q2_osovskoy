package com.example.a2022_q2_osovskoy.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.a2022_q2_osovskoy.data.model.ResultState
import com.example.a2022_q2_osovskoy.domain.use_case.GetUploadProgress
import com.example.a2022_q2_osovskoy.domain.use_case.UploadFileUseCase
import com.example.a2022_q2_osovskoy.ui.MainActivityViewEvent
import kotlinx.coroutines.launch
import javax.inject.Inject

class UploadScreenViewModel @Inject constructor(
    private val uploadFileUseCase: UploadFileUseCase,
    private val getUploadProgress: GetUploadProgress,
) : ViewModel() {

    private val _flowUploadProgress = MutableLiveData<Int>()
    val flowUploadProgress = _flowUploadProgress

    private val _mainActivityState = MutableLiveData<MainActivityModelState>()
    val mainActivityState = _mainActivityState

    fun renderViewEvent(event: MainActivityViewEvent) {
        when (event) {
            MainActivityViewEvent.ClickOnUploadFileButton -> {
                uploadFile()
            }
        }
    }

    private fun uploadFile() {
        viewModelScope.launch {
            _mainActivityState.value = MainActivityModelState.FileLoading
            val observeProgress = launch {
                observeFileUploadingProgress()
            }
            _mainActivityState.value = when (val result = uploadFileUseCase()) {
                is ResultState.Success -> {
                    MainActivityModelState.FileLoadingSuccess(result.postInfo)
                }
                ResultState.MainError.IO -> {
                    observeProgress.cancel()
                    MainActivityModelState.FileLoadingErrorIO
                }
                ResultState.MainError.Http -> {
                    observeProgress.cancel()
                    MainActivityModelState.FileLoadingErrorHttp
                }
            }
        }
    }

    private suspend fun observeFileUploadingProgress() {
        getUploadProgress().collect {
            _flowUploadProgress.value = it
        }
    }
}