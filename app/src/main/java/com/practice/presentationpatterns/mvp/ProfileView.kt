package com.practice.presentationpatterns.mvp

interface ProfileView {
    fun setUserName(name: String)
    fun isLoading(isLoading: Boolean)
    fun showMessage(message: String)
    fun logout()
    fun deleteAccount()
}