package com.practice.presentationpatterns.mvi.intent

sealed class VerifyUserIntent {
    object SendVerification: VerifyUserIntent()
}