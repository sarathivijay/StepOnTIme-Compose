package com.tws.stepontime.commons

import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.tws.stepontime.domain.usecase.MobileNumberValidationUseCase
import com.tws.stepontime.domain.viewmodel.MobileNumberViewModel

@Composable
fun viewModelFactory(myUseCase: MobileNumberValidationUseCase): ViewModelProvider.Factory {
    return object : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return MobileNumberViewModel(myUseCase) as T
        }
    }
}