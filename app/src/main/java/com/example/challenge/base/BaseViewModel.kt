package com.example.challenge.base

import android.app.Application
import androidx.lifecycle.ViewModel
import com.example.challenge.repository.AppRepository
import io.reactivex.disposables.CompositeDisposable

open class BaseViewModel(protected val application: Application) : ViewModel() {
    protected var compositeDisposable = CompositeDisposable()

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}