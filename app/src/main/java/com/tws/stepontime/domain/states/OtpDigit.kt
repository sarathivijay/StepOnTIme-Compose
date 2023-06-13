package com.tws.stepontime.domain.states

data class OtpDigit(
    val value: String = "",
    val isLastField: Boolean = false,
    val isTimerRunning: Boolean = true,
    val timerSeconds: Int = 2,
    val snackBar: SnackBar = SnackBar(),
)

data class SnackBar(
    val shouldShow: Boolean = false,
    val isError: Boolean = false,
    val message: String = "",
)