package com.tws.stepontime.commons

import com.tws.stepontime.domain.usecase.MobileNumberValidationUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideMobileValidationUseCase() = MobileNumberValidationUseCase()
}