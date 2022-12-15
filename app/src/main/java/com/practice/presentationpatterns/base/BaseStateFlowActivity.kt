package com.practice.presentationpatterns.base

import android.os.Bundle
import android.view.WindowManager
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.viewbinding.ViewBinding
import io.reactivex.rxjava3.disposables.CompositeDisposable

abstract class BaseStateFlowActivity<VB: ViewBinding>: AppCompatActivity() {

    protected lateinit var binding: VB
    protected val disposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN)
        binding = DataBindingUtil.setContentView(this, getLayoutRes())
    }

    override fun onStop() {
        super.onStop()
        disposable.clear()
    }

    @LayoutRes
    abstract fun getLayoutRes(): Int

    abstract fun showLoading()

    abstract fun hideLoading()
}