package com.example.challenge.profile

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.apollographql.apollo.api.Response
import com.example.challenge.UserQuery
import com.example.challenge.base.BaseViewModel
import com.example.challenge.repository.AppRepository
import com.example.challenge.response.User
import io.reactivex.Observer
import io.reactivex.disposables.Disposable

class ProfileViewModel(application: Application) : BaseViewModel(application) {
    private val appRepository = AppRepository(application)
    val errorLiveData = MutableLiveData<Boolean>()

    fun getUser() {
        appRepository.getUserApi()
            .subscribe(object : Observer<Response<UserQuery.Data>> {
                override fun onSubscribe(d: Disposable) {
                    Log.d("TAG", "onSubscribe: ")
                }

                override fun onNext(t: Response<UserQuery.Data>) {
                    Log.d("TAG", "onNext: ")

                }

                override fun onError(e: Throwable) {
                    Log.d("TAG", "onError: ")
                }

                override fun onComplete() {
                    Log.d("TAG", "onComplete: ")
                }

            })
    }

    fun getUserCache(): LiveData<List<User>> {
        return appRepository.getUsersCache()
    }

}