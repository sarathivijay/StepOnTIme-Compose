package com.tws.stepontime.domain.events

sealed class OtpEvents {
    data class isTimerRunning(val isTimerRunning: Boolean) : OtpEvents()
    data class onNewValueChanged(val newChangedValue: String) : OtpEvents()
    data class onTimerChange(val timerValue: Int) : OtpEvents()
    data class validateMobile(val mobileNumber: String) : OtpEvents()
}