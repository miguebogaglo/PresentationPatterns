package com.practice.presentationpatterns.mvp

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.addTo
import io.reactivex.rxjava3.schedulers.Schedulers

class ProfilePresenter(
    private var profileView: ProfileView?,
    private var composite: CompositeDisposable?,
    private val profileInteractor: ProfileModel,
) {

    fun onCreate() {
        profileView?.isLoading(true)
        composite?.let {
            getUser()
        }

    }

    fun onDestroy() {
        profileView = null
        composite?.clear()
        composite = null
    }

    fun onLogoutPressed() {
        composite?.let { compositeDisposable ->
            profileInteractor.logout()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    profileView?.logout()
                }, {
                    profileView?.showMessage("Error")
                })
        }
    }

    fun onDeleteAccountPressed() {
        composite?.let { compositeDisposable ->
            profileInteractor.deleteAccount()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    profileView?.deleteAccount()
                },{
                    profileView?.showMessage("Error")
                })
        }
    }

    private fun getUser() {
        composite?.let { compositeDisposable ->
            profileInteractor.getUser()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    profileView?.run {
                        isLoading(false)
                        setUserName(it.display_name)
                    }
                    profileView?.isLoading(false)
                    profileView?.setUserName(it.display_name)
                }, {
                    profileView?.showMessage("Error")
                })
                .addTo(compositeDisposable)
        }
    }

}