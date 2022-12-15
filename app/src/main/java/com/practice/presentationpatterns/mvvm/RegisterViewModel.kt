package com.practice.presentationpatterns.mvvm

import com.practice.presentationpatterns.base.BaseStateFlowViewModel
import com.social.photo.presentation.register.RegisterUiState
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers

class RegisterViewModel constructor(
    //Inject use cases...
): BaseStateFlowViewModel<RegisterUiState>(RegisterUiState()) {

    fun requestRegister(displayName: String, email: String, pwd: String): Disposable =
        Observable.just(RegisterUiState(true, false, false, null))
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .startWith(Observable.just(RegisterUiState(isLoading = true)))
            .subscribe({
                _uiState.value = it
            },{

            })

}