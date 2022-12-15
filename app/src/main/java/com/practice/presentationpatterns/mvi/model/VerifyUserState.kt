package com.practice.presentationpatterns.mvi.model

sealed class VerifyUserState {
    object Idle: VerifyUserState()
    object Loading: VerifyUserState()
    data class SendVerification(val data: Any): VerifyUserState()
    data class Error(val error: Exception?): VerifyUserState()
}
