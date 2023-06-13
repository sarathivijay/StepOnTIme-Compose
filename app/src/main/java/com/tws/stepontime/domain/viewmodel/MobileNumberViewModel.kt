package com.tws.stepontime.domain.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tws.stepontime.domain.events.MobileNumberEvents
import com.tws.stepontime.domain.events.OtpEvents
import com.tws.stepontime.domain.states.MobileNumberPageState
import com.tws.stepontime.domain.states.OtpDigit
import com.tws.stepontime.domain.usecase.MobileNumberValidationUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

class MobileNumberViewModel @Inject constructor(
    private val mobileNumberValidationUseCase: MobileNumberValidationUseCase,
) : ViewModel() {

    private var _uiState = MutableStateFlow(MobileNumberPageState())
    val uiState: StateFlow<MobileNumberPageState> = _uiState.asStateFlow()

    private var _otpState = MutableStateFlow(OtpDigit())
    val otpState: StateFlow<OtpDigit> = _otpState.asStateFlow()

    fun onOtpEvent(otpEvents: OtpEvents) {
        when (otpEvents) {
            /*   is OtpEvents.isTimerRunning -> {
                   _otpState.value = _otpState.value.copy(isTimerRunning = otpEvents.isTimerRunning)
               }*/
            /*    is OtpEvents.onNewText -> {
                    _uiState.update { it.copy(text = otpEvents.newText) }
                }*/

            is OtpEvents.onNewValueChanged -> {
                _otpState.update { it.copy(value = otpEvents.newChangedValue) }
            }

            is OtpEvents.onTimerChange -> {
                _otpState.update { it.copy(timerSeconds = otpEvents.timerValue) }
            }

            is OtpEvents.validateMobile -> {
                val response = mobileNumberValidationUseCase.invoke(otpEvents.mobileNumber)
            }

            else -> {}
        }
    }

    fun onMobileScreenEvents(mobileNumberEvents: MobileNumberEvents) {
        when (mobileNumberEvents) {
            is MobileNumberEvents.onNewText -> {
                _uiState.update { it.copy(text = mobileNumberEvents.newText) }
            }
        }
    }

    fun validateOtp(otpText: String) {
        viewModelScope.launch {

        }
    }

}