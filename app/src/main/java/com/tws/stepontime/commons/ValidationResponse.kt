package com.tws.stepontime.commons

sealed class ValidationResponse {
    object Success : ValidationResponse()
    data class Error(val errMsg: String) : ValidationResponse()
}
