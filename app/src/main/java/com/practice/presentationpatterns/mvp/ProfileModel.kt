package com.practice.presentationpatterns.mvp

import com.practice.presentationpatterns.entity.User
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single

//Like a Interactor
class ProfileModel constructor(
    //UseCases here
){

    fun getUser(): Observable<User> = Observable.just(User("1", "miguel@miguel.com", "migue"))

    fun logout(): Single<Int> = Single.just(1)

    fun deleteAccount(): Observable<Int> = Observable.just(1)

}