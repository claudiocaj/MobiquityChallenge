package com.mobiquity.mobproducts.presentation.viewmodel

import androidx.annotation.MainThread
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.disposables.CompositeDisposable

abstract class BaseViewModel : ViewModel() {
    protected val compositeDisposable = CompositeDisposable()
    val isLoadingMutable = MutableLiveData<Boolean>()
    val test = "opa"

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }

    @MainThread
    protected fun setIsLoading(isLoading: Boolean) {
        isLoadingMutable.value = isLoading
    }
}