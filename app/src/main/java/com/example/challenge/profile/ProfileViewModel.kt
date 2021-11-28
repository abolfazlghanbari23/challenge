package com.example.challenge.profile

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.apollographql.apollo.api.Response
import com.example.challenge.UserQuery
import com.example.challenge.base.BaseViewModel
import com.example.challenge.repository.AppRepository
import com.example.challenge.response.User
import io.reactivex.Observer
import io.reactivex.disposables.Disposable

class ProfileViewModel : BaseViewModel() {
    private val appRepository = AppRepository(Application())
    val errorLiveData = MutableLiveData<Boolean>()

    fun getUser() {
        appRepository.getUserApi()
            .subscribe(object : Observer<Response<UserQuery.Data>> {
                override fun onSubscribe(d: Disposable) {
                }

                override fun onNext(t: Response<UserQuery.Data>) {
                }

                override fun onError(e: Throwable) {
                }

                override fun onComplete() {
                }

            })
    }

    fun getUserCache(): LiveData<List<User>> {
        return appRepository.getUsersCache()
    }

}