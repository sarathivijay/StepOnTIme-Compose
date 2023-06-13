package com.tws.stepontime.domain.usecase

import com.tws.stepontime.commons.ValidationResponse

class MobileNumberValidationUseCase {

    operator fun invoke(mobileNumber: String): ValidationResponse {
        val regexPattern = "^[+]?[0-9]{10,13}$"
        return if (!mobileNumber.matches(regexPattern.toRegex()))
            ValidationResponse.Error(errMsg = "Please Enter the Valid Mobile Number")
        else
            ValidationResponse.Success
    }

}