package com.example.a2022_q2_osovskoy.presentation.loanhistory

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.a2022_q2_osovskoy.domain.entity.AppConfig
import com.example.a2022_q2_osovskoy.domain.usecase.DeleteLoansUseCase
import com.example.a2022_q2_osovskoy.domain.usecase.GetLocalLoansUseCase
import com.example.a2022_q2_osovskoy.domain.usecase.GetRemoteLoansUseCase
import com.example.a2022_q2_osovskoy.domain.usecase.UpdateAppConfigUseCase
import com.example.a2022_q2_osovskoy.utils.exceptions.*
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import okio.IOException
import javax.inject.Inject

class LoansHistoryViewModel @Inject constructor(
    private val getRemoteLoansUseCase: GetRemoteLoansUseCase,
    private val updateAppConfigUseCase: UpdateAppConfigUseCase,
    private val getLocalLoansUseCase: GetLocalLoansUseCase,
    private val deleteLoansUseCase: DeleteLoansUseCase,
) : ViewModel() {

    private val _loansState = MutableLiveData<LoansState>()
    val loansState: LiveData<LoansState> = _loansState

    private val handler = CoroutineExceptionHandler { _, throwable ->
        handleErrors(throwable)
    }

    private fun handleErrors(exception: Throwable) {
        _loansState.value = when (exception) {
            is BadRequestException -> LoansState.Error.BadRequest
            is UnauthorizedException -> LoansState.Error.Unauthorized
            is ForbiddenException -> LoansState.Error.Forbidden
            is NotFoundException -> LoansState.Error.NotFound
            is ServerIsNotRespondingException -> LoansState.Error.ServerIsNotResponding
            is IOException -> LoansState.Error.NoInternetConnection
            else -> LoansState.Error.Unknown

        }
    }

    init {
        refreshLoans()
    }

    fun getLocalLoans() {
        viewModelScope.launch(handler) {
            setLoading()
            val list = getLocalLoansUseCase()
            _loansState.value = if (list.isNotEmpty()) {
                LoansState.Success.Local(list)
            } else {
                LoansState.Empty
            }
        }
    }

    fun refreshLoans() {
        viewModelScope.launch(handler) {
            setLoading()
            val list = getRemoteLoansUseCase()
            _loansState.value = if (list.isNotEmpty()) {
                LoansState.Success.Remote(list)
            } else {
                LoansState.Empty
            }
        }
    }

    fun updateAppConfig(appConfig: AppConfig) {
        updateAppConfigUseCase(appConfig)
        deleteAllLoans()
    }

    private fun setLoading() {
        _loansState.value = LoansState.Loading
    }

    private fun deleteAllLoans() {
        viewModelScope.launch {
            deleteLoansUseCase()
        }
    }
}