package com.tws.stepontime.domain.events

sealed class MobileNumberEvents {
    data class onNewText(val newText: String) : MobileNumberEvents()
}